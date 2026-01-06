package org.dromara.merchant.domain.marketing.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.Marketing;
import org.dromara.merchant.domain.marketing.model.Rule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * @Description 优惠叠加策略 - 多个活动的优惠可以叠加使用
 * @Author Code Skywalker
 * @Date 2026/1/4 17:32
 */
@Component
@RequiredArgsConstructor
public class MultiDiscountStrategy implements PriceCalculationStrategy {

    private final IMarketingGateway marketingGateway;

    /**
     * 计算最终价格
     *
     * @param sku           商品SKU
     * @param quantity      商品数量
     * @return 最终价格
     */
    @Override
    public BigDecimal calculateFinalPrice(Sku sku, Integer quantity) {
        // 获取商品原价
        BigDecimal originalPrice = sku.getPrice();

        // 计算原始总价
        BigDecimal totalPrice = originalPrice.multiply(new BigDecimal(quantity));

        // 查询可用的营销活动
        List<Marketing> marketingList = this.marketingGateway.queryAvailableMarketing(sku.getId());

        // 按照优惠力度排序，优先使用优惠力度大的活动
        marketingList.sort(Comparator.comparing(m -> calculateDiscountAmount(m.getRules(), originalPrice, quantity),
            Comparator.reverseOrder()));

        BigDecimal currentPrice = totalPrice;

        // 对每个活动计算优惠并叠加
        for (Marketing marketing : marketingList) {
            Rule rule = marketing.getRules();
            if (rule != null) {
                // 计算当前活动的优惠后价格
                BigDecimal discountedPrice = rule.calculate(originalPrice, quantity);

                // 计算当前活动的优惠金额
                BigDecimal discountAmount = currentPrice.subtract(discountedPrice.multiply(new BigDecimal(quantity)));

                // 确保优惠金额不为负数
                if (discountAmount.compareTo(BigDecimal.ZERO) > 0) {
                    currentPrice = currentPrice.subtract(discountAmount);
                }
            }
        }

        // 确保最终价格不为负数
        return currentPrice.compareTo(BigDecimal.ZERO) > 0 ? currentPrice : BigDecimal.ZERO;
    }

    /**
     * 计算优惠金额，用于排序
     *
     * @param rule          规则
     * @param originalPrice 商品原价
     * @param quantity      商品数量
     */
    private BigDecimal calculateDiscountAmount(Rule rule, BigDecimal originalPrice, Integer quantity) {
        if (rule == null) return BigDecimal.ZERO;

        BigDecimal originalTotal = originalPrice.multiply(new BigDecimal(quantity));
        BigDecimal discountedTotal = rule.calculate(originalPrice, quantity).multiply(new BigDecimal(quantity));

        return originalTotal.subtract(discountedTotal);
    }
}
