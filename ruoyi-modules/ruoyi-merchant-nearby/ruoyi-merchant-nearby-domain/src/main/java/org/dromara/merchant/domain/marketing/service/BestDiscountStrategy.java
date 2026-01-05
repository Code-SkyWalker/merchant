package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.model.Marketing;
import org.dromara.merchant.domain.marketing.model.Rule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 最优优惠策略 - 从多个活动中选择最优的一个
 * @Author Code Skywalker
 * @Date 2026/1/4 17:30
 */
@Component
public class BestDiscountStrategy implements PriceCalculationStrategy {

    /**
     * 计算最终价格
     *
     * @param sku           商品SKU
     * @param marketingList 活动列表
     * @param quantity      购买数量
     * @return 最终价格
     */
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

        BigDecimal bestPrice = originalTotalPrice;

        // 遍历所有活动，找出最优价格
        for (Marketing marketing : marketingList) {
            Rule rule = marketing.getRules();
            if (rule != null) {
                // 计算单个商品的优惠价格
                BigDecimal discountedUnitPrice = rule.calculate(originalPrice, quantity);

                // 计算总价
                BigDecimal totalPrice = discountedUnitPrice.multiply(new BigDecimal(quantity));

                // 选择最优价格
                if (totalPrice.compareTo(bestPrice) < 0) {
                    bestPrice = totalPrice;
                }
            }
        }

        // 确保最终价格不为负数
        return bestPrice.compareTo(BigDecimal.ZERO) > 0 ? bestPrice : BigDecimal.ZERO;
    }
}
