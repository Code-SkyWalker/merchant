package org.dromara.merchant.domain.marketing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.domain.marketing.discount.Activities;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Description 营销活动规则
 * @Author Code Skywalker
 * @Date 2025/12/26 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TieredPercentageDiscountActivity implements Activities {

    /**
     * 活动主键
     */
    private Long id = SnowflakeIdGenerator.generateId();

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 活动时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 优惠条件
     */
    private Requirement requirement;

    /**
     * 优惠内容
     */
    private Content content;

    /**
     * 优惠方式
     */
    private Way way;

    /**
     * 具体优惠信息（阶梯式/循环式）
     */
    private List<Condition> discounts;

    /**
     * 优惠内容枚举
     */
    @Getter
    private enum Content {
        AMOUNT("AMOUNT", "满元"),
        QUANTITY("QUANTITY", "满件"),
        ;

        private final String code;
        private final String info;

        Content(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Content getByCode(String code) {
            for (Content content : values()) {
                if (content.getCode().equals(code)) {
                    return content;
                }
            }
            return null;
        }
    }

    /**
     * 优惠条件枚举
     */
    @Getter
    private enum Requirement {
        PRICE("PRICE", "减钱"),
        DISCOUNT("DISCOUNT", "打折"),
        ;

        private final String code;
        private final String info;

        Requirement(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Requirement getByCode(String code) {
            for (Requirement requirement : values()) {
                if (requirement.getCode().equals(code)) {
                    return requirement;
                }
            }
            return null;
        }
    }

    /**
     * 优惠方式枚举
     */
    @Getter
    private enum Way {
        TIERED("TIERED", "阶梯优惠"),       // 满100减20 满200减30
        LOOP("LOOP", "循环优惠"),           // "每" 满100减10
        ;

        private final String code;
        private final String info;

        Way(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Way getByCode(String code) {
            for (Way way : values()) {
                if (way.getCode().equals(code)) {
                    return way;
                }
            }
            return null;
        }
    }

    /**
     * 阶梯式优惠信息
     */
    @Data
    private static class Condition {

        /**
         * 满金额/满件
         */
        private BigDecimal amount;

        /**
         * 优惠金额/折扣
         */
        private BigDecimal discount;

    }

    /**
     * 活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减 BULK:n元n件 SECONDKILL:秒杀
     */
    private MarketingType type;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 营销活动与商品关联 列表
     */
    private List<MarketingSpu> marketingSpus;

    /**
     * 判断活动是否适用于当前购物车
     *
     * @param products      商品列表
     * @param currentPrices 当前各商品的价格
     * @return 如果适用返回true，否则返回false
     */
    @Override
    public boolean isApplicable(List<Product> products, Map<Product, BigDecimal> currentPrices) {
        // 检查活动是否在有效时间内
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(receiveBegin) || now.isAfter(receiveEnd)) {
            return false;
        }

        // 检查是否有符合条件的商品
        if (products.stream().noneMatch(this::isProductEligible)) {
            return false;
        }

        // 计算符合条件商品的总金额
        BigDecimal eligibleTotal = calculateEligibleTotal(products, currentPrices);

        // 根据优惠方式判断是否满足条件
        if (Way.LOOP.equals(way)) {
            // 循环优惠：检查是否有满足任一条件的商品总金额
            return discounts.stream()
                .anyMatch(condition -> eligibleTotal.compareTo(condition.getAmount()) >= 0);
        } else {
            // 阶梯优惠：检查是否满足任一条件
            return discounts.stream()
                .anyMatch(condition -> eligibleTotal.compareTo(condition.getAmount()) >= 0);
        }
    }

    /**
     * 计算总优惠金额
     *
     * @param products      商品列表
     * @param currentPrices 当前各商品的价格
     * @return 总优惠金额
     */
    @Override
    public BigDecimal calculateTotalDiscount(List<Product> products, Map<Product, BigDecimal> currentPrices) {
        if (products == null || products.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // 计算符合条件商品的总金额
        BigDecimal eligibleTotal = calculateEligibleTotal(products, currentPrices);

        if (eligibleTotal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        // 根据优惠方式计算优惠金额
        if (Way.LOOP.equals(way)) {
            // 循环优惠："每"满amount减discount
            return calculateLoopDiscount(eligibleTotal);
        } else {
            // 阶梯优惠：按满足的最高条件计算
            return calculateTieredDiscount(eligibleTotal);
        }
    }

    /**
     * 计算循环优惠金额
     * @param eligibleTotal 符合条件商品的总金额
     * @return 循环优惠金额
     */
    private BigDecimal calculateLoopDiscount(BigDecimal eligibleTotal) {
        BigDecimal totalDiscount = BigDecimal.ZERO;

        // 按满足条件的最高档次计算
        Condition bestCondition = findBestCondition(eligibleTotal);
        // 计算可以享受多少次优惠
        BigDecimal times = eligibleTotal.divide(bestCondition.getAmount(), 0, BigDecimal.ROUND_DOWN);
        // 每次优惠金额
        BigDecimal discountPerTime = getDiscountAmount(bestCondition, eligibleTotal);
        totalDiscount = times.multiply(discountPerTime);

        return totalDiscount;
    }

    /**
     * 计算阶梯优惠金额
     * @param eligibleTotal 符合条件商品的总金额
     * @return 阶梯优惠金额
     */
    private BigDecimal calculateTieredDiscount(BigDecimal eligibleTotal) {
        // 找到满足条件的最高档次
        Condition bestCondition = findBestCondition(eligibleTotal);
        if (bestCondition != null) return BigDecimal.ZERO;

        return getDiscountAmount(bestCondition, eligibleTotal);
    }

    /**
     * 为循环优惠找到最佳条件
     * @param eligibleTotal 符合条件商品的总金额
     * @return 最佳条件
     */
    private Condition findBestCondition(BigDecimal eligibleTotal) {
        return discounts.stream()
            .filter(condition -> eligibleTotal.compareTo(condition.getAmount()) >= 0)
            .max(Comparator.comparing(Condition::getAmount))// 选择满足条件的最大金额条件
            .orElse(null);
    }

    /**
     * 根据条件和总金额计算实际优惠金额
     * @param condition 优惠条件
     * @param eligibleTotal 符合条件商品的总金额
     * @return 优惠金额
     */
    private BigDecimal getDiscountAmount(Condition condition, BigDecimal eligibleTotal) {
        // 根据优惠类型判断是减钱还是打折
        if (Requirement.PRICE.equals(requirement)) {
            // 减钱：直接返回优惠金额
            return condition.getDiscount();
        } else if (Requirement.DISCOUNT.equals(requirement)) {
            // 打折：计算折扣金额
            // 折扣值假设是0-1之间的数值，如0.8表示8折
            BigDecimal discountRate = condition.getDiscount();
            BigDecimal discountAmount = eligibleTotal.multiply(BigDecimal.ONE.subtract(discountRate));
            return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
        }

        return BigDecimal.ZERO;
    }

    /**
     * 判断商品是否符合活动条件
     *
     * @param product 待检查的商品
     * @return 如果符合条件返回true，否则返回false
     */
    @Override
    public boolean isProductEligible(Product product) {
        // 判断商品是否在活动的指定商品列表中
        if (this.marketingSpus == null || this.marketingSpus.isEmpty()) {
            // 如果没有指定商品列表，则所有商品都符合条件
            return false;
        }

        return this.marketingSpus.stream()
            .anyMatch(marketingSpu -> marketingSpu.getSpuId().equals(product.getSpuId()));
    }
}
