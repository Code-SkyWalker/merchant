package org.dromara.merchant.domain.marketing.model.activity;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

/**
 * @Description 满折满减模式
 * @Author Code Skywalker
 * @Date 2025/12/26 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleMultiUnitDiscount implements Rule {

    private final String type = "MULTIUNIT";

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
     * 具体优惠信息（阶梯式）
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


    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public BigDecimal calculate(BigDecimal originalUnitPrice, Integer quantity) {
        return BigDecimal.ZERO;
    }

    /**
     * 计算总优惠金额
     *
     * @param totalPrice 符合条件商品的总金额
     * @param quantity   符合条件商品的总数量
     * @return 总优惠金额
     */
    public BigDecimal calculateTotalDiscount(BigDecimal totalPrice, Integer quantity) {

        BigDecimal eligibleTotal = Content.AMOUNT.equals(this.content) ? totalPrice : BigDecimal.valueOf(quantity);

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
     *
     * @param eligibleTotal 符合条件商品的总金额
     * @return 循环优惠金额
     */
    private BigDecimal calculateLoopDiscount(BigDecimal eligibleTotal) {


        // 按满足条件的最高档次计算
        Condition bestCondition = findBestCondition(eligibleTotal);
        if (bestCondition == null) return BigDecimal.ZERO;

        if (Requirement.PRICE.equals(this.requirement)) {
            BigDecimal totalDiscount;
            // 计算可以享受多少次优惠
            BigDecimal times = eligibleTotal.divide(bestCondition.getAmount(), 0, RoundingMode.DOWN);
            // 每次优惠金额
            BigDecimal discountPerTime = getDiscountAmount(bestCondition, eligibleTotal);
            totalDiscount = times.multiply(discountPerTime);

            return totalDiscount;
        }

        return getDiscountAmount(bestCondition, eligibleTotal);
    }

    /**
     * 计算阶梯优惠金额
     *
     * @param eligibleTotal 符合条件商品的总金额
     * @return 阶梯优惠金额
     */
    private BigDecimal calculateTieredDiscount(BigDecimal eligibleTotal) {
        // 找到满足条件的最高档次
        Condition bestCondition = findBestCondition(eligibleTotal);
        if (bestCondition == null) return BigDecimal.ZERO;
        return getDiscountAmount(bestCondition, eligibleTotal);
    }

    /**
     * 为循环优惠找到最佳条件
     *
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
     *
     * @param condition     优惠条件
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
            // 折扣值假设是0-10之间的数值，如8表示8折, 需除以10
            BigDecimal discountRate = condition.getDiscount().compareTo(BigDecimal.TEN) > 0 ? condition.getDiscount().divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP) : condition.getDiscount();
            BigDecimal discountAmount = eligibleTotal.multiply(BigDecimal.ONE.subtract(discountRate));
            return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
        }

        return BigDecimal.ZERO;
    }


}
