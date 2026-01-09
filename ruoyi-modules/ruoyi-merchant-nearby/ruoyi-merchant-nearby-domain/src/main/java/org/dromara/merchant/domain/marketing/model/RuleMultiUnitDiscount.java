package org.dromara.merchant.domain.marketing.model;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

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
     * 具体优惠信息（循环式）
     */
    private Condition condition;

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
        if (originalUnitPrice == null || quantity == null || quantity <= 0) {
            return BigDecimal.ZERO;
        }

        // 获取原始总价
        BigDecimal originalTotalPrice = originalUnitPrice.multiply(new BigDecimal(quantity));

        // 根据优惠方式选择不同的计算逻辑
        if (Way.TIERED.equals(this.way)) {
            // 阶梯优惠计算
            return calculateTieredDiscount(originalTotalPrice, quantity);
        } else if (Way.LOOP.equals(this.way)) {
            // 循环优惠计算
            return calculateLoopDiscount(originalTotalPrice, quantity);
        }

        // 如果没有匹配的优惠方式，返回0（无优惠）
        return BigDecimal.ZERO;
    }

    /**
     * 计算阶梯优惠
     *
     * @param originalTotalPrice 原始总价
     * @param quantity           购买数量
     * @return 优惠的金额
     */
    private BigDecimal calculateTieredDiscount(BigDecimal originalTotalPrice, Integer quantity) {
        if (discounts == null || discounts.isEmpty()) return BigDecimal.ZERO;

        // 根据优惠内容确定比较基准
        BigDecimal comparisonValue = getComparisonValue(originalTotalPrice, quantity);

        // 找到满足条件的最大优惠
        Condition bestCondition = null;
        for (Condition condition : discounts) {
            if (condition != null && comparisonValue.compareTo(condition.getAmount()) >= 0) {
                if (bestCondition == null || condition.getAmount().compareTo(bestCondition.getAmount()) > 0) {
                    bestCondition = condition;
                }
            }
        }

        if (bestCondition == null) {
            return BigDecimal.ZERO; // 没有满足任何条件，无优惠
        }

        // 根据优惠条件计算优惠金额
        return calculateDiscountAmount(originalTotalPrice, bestCondition);
    }

    /**
     * 计算循环优惠
     *
     * @param originalTotalPrice 原始总价
     * @param quantity           购买数量
     * @return 优惠的金额
     */
    private BigDecimal calculateLoopDiscount(BigDecimal originalTotalPrice, Integer quantity) {
        if (condition == null) {
            return BigDecimal.ZERO;
        }

        // 根据优惠内容确定比较基准
        BigDecimal comparisonValue = getComparisonValue(originalTotalPrice, quantity);

        // 计算可以享受优惠的次数
        BigDecimal conditionAmount = condition.getAmount();
        if (conditionAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO; // 避免除零错误
        }

        // 计算满足条件的次数
        int eligibleTimes = comparisonValue.divideToIntegralValue(conditionAmount).intValue();

        if (eligibleTimes <= 0) {
            return BigDecimal.ZERO; // 不满足一次优惠条件，无优惠
        }

        // 根据优惠条件计算优惠
        return calculateLoopDiscountAmount(originalTotalPrice, eligibleTimes);
    }

    /**
     * 获取比较基准值（根据优惠内容决定是总价还是数量）
     *
     * @param originalTotalPrice 原始总价
     * @param quantity           购买数量
     * @return 比较基准值
     */
    private BigDecimal getComparisonValue(BigDecimal originalTotalPrice, Integer quantity) {
        if (Content.AMOUNT.equals(this.content)) {
            // 满元优惠，使用总价作为比较基准
            return originalTotalPrice;
        } else if (Content.QUANTITY.equals(this.content)) {
            // 满件优惠，使用数量作为比较基准
            return new BigDecimal(quantity);
        }
        // 默认使用总价
        return originalTotalPrice;
    }

    /**
     * 计算优惠金额（根据优惠条件）
     *
     * @param originalTotalPrice 原始总价
     * @param condition          优惠条件
     * @return 优惠的金额
     */
    private BigDecimal calculateDiscountAmount(BigDecimal originalTotalPrice, Condition condition) {
        if (Requirement.PRICE.equals(this.requirement)) {
            // 减钱优惠，直接返回优惠金额
            BigDecimal discountAmount = condition.getDiscount();
            // 确保优惠金额不超过原价
            return discountAmount.compareTo(originalTotalPrice) <= 0 ? discountAmount : originalTotalPrice;
        } else if (Requirement.DISCOUNT.equals(this.requirement)) {
            // 打折优惠，计算折扣金额
            BigDecimal discountRate = condition.getDiscount();
            // 将折扣率转换为0-1之间的数值（如果折扣率是0-10之间的值，需要除以10；如果是0-1之间的值则直接使用）
            BigDecimal rate = discountRate.compareTo(BigDecimal.TEN) <= 0 ?
                discountRate.divide(BigDecimal.TEN, 4, RoundingMode.HALF_UP) :
                discountRate;
            // 计算折扣后价格，然后得出优惠金额
            BigDecimal discountedPrice = originalTotalPrice.multiply(rate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal discountAmount = originalTotalPrice.subtract(discountedPrice);
            // 确保优惠金额不为负数
            return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
        }
        // 如果没有匹配的优惠条件，返回0
        return BigDecimal.ZERO;
    }

    /**
     * 计算循环优惠金额
     *
     * @param originalTotalPrice 原始总价
     * @param eligibleTimes      满足条件的次数
     * @return 优惠的金额
     */
    private BigDecimal calculateLoopDiscountAmount(BigDecimal originalTotalPrice, int eligibleTimes) {
        if (condition == null) {
            return BigDecimal.ZERO;
        }

        if (Requirement.PRICE.equals(this.requirement)) {
            // 循环减钱优惠
            BigDecimal discountPerLoop = condition.getDiscount();
            BigDecimal totalDiscount = discountPerLoop.multiply(new BigDecimal(eligibleTimes));
            // 确保优惠金额不超过原价
            return totalDiscount.compareTo(originalTotalPrice) <= 0 ? totalDiscount : originalTotalPrice;
        } else if (Requirement.DISCOUNT.equals(this.requirement)) {
            // 循环打折优惠
            BigDecimal discountRate = condition.getDiscount();
            // 将折扣率转换为0-1之间的数值
            BigDecimal rate = discountRate.compareTo(BigDecimal.TEN) <= 0 ?
                discountRate.divide(BigDecimal.TEN, 4, RoundingMode.HALF_UP) :
                discountRate;
            // 计算折扣后价格，然后得出优惠金额
            BigDecimal discountedPrice = originalTotalPrice.multiply(rate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal discountAmount = originalTotalPrice.subtract(discountedPrice);
            // 确保优惠金额不为负数
            return discountAmount.compareTo(BigDecimal.ZERO) > 0 ? discountAmount : BigDecimal.ZERO;
        }
        // 如果没有匹配的优惠条件，返回0
        return BigDecimal.ZERO;
    }

}
