package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.model.Marketing;
import org.dromara.merchant.domain.marketing.model.Rule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 优惠互斥策略 - 多个活动之间互斥，只能使用一个
 * @Author Code Skywalker
 * @Date 2026/1/4 17:31
 */
@Component
public class ExclusiveDiscountStrategy implements PriceCalculationStrategy {

    @Override
    public BigDecimal calculateFinalPrice(Sku sku, List<Marketing> marketingList, Integer quantity) {
        // 获取商品原价
        BigDecimal originalPrice = sku.getPrice();

        // 计算原始总价
        BigDecimal originalTotalPrice = originalPrice.multiply(new BigDecimal(quantity));

        // 如果没有活动，返回原价
        if (marketingList == null || marketingList.isEmpty()) {
            return originalTotalPrice;
        }

        // 按照活动优先级排序，选择优先级最高的活动
        Marketing bestMarketing = marketingList.stream()
            .max((m1, m2) -> {
                // 这里可以实现优先级逻辑，例如：按活动类型、优惠力度等排序
                return compareMarketingPriority(m1, m2, originalPrice, quantity);
            })
            .orElse(null);

        if (bestMarketing != null && bestMarketing.getRules() != null) {
            Rule rule = bestMarketing.getRules();
            BigDecimal discountedUnitPrice = rule.calculate(originalPrice, quantity);
            BigDecimal finalPrice = discountedUnitPrice.multiply(new BigDecimal(quantity));

            // 确保最终价格不为负数
            return finalPrice.compareTo(BigDecimal.ZERO) > 0 ? finalPrice : BigDecimal.ZERO;
        }

        return originalTotalPrice;
    }

    /**
     * 比较营销活动优先级
     */
    private int compareMarketingPriority(Marketing m1, Marketing m2, BigDecimal originalPrice, Integer quantity) {
        // 计算两个活动的优惠金额
        BigDecimal discount1 = calculateDiscountAmount(m1.getRules(), originalPrice, quantity);
        BigDecimal discount2 = calculateDiscountAmount(m2.getRules(), originalPrice, quantity);

        // 优先选择优惠金额大的
        return discount1.compareTo(discount2);
    }

    /**
     * 计算优惠金额
     */
    private BigDecimal calculateDiscountAmount(Rule rule, BigDecimal originalPrice, Integer quantity) {
        if (rule == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal originalTotal = originalPrice.multiply(new BigDecimal(quantity));
        BigDecimal discountedTotal = rule.calculate(originalPrice, quantity).multiply(new BigDecimal(quantity));

        return originalTotal.subtract(discountedTotal);
    }
}
