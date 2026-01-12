package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.marketing.discount.CalculationContext;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/12 15:36
 */
public interface PriceCalculator {

    /**
     * 执行价格计算
     * @param products 商品列表
     * @param context 计算上下文
     * @return 计算结果
     */
    CalculationResult calculate(List<Product> products, CalculationContext context);

    /**
     * 设置下一个处理器
     * @param nextCalculator 下一个处理器
     * @return 下一个处理器
     */
    PriceCalculator setNext(PriceCalculator nextCalculator);

    /**
     * 创建最终计算结果的默认实现
     * @param products 商品列表
     * @param context 计算上下文
     * @return 计算结果
     */
    default CalculationResult createFinalResult(List<Product> products, CalculationContext context) {
        // 计算最终小计金额
        BigDecimal finalSubtotal = context.getCurrentPrices().values().stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算每个商品的折扣金额
        Map<Product, BigDecimal> totalDiscounts = new HashMap<>();
        // 记录每个商品的最终价格
        Map<Product, BigDecimal> finalPrices = new HashMap<>();

        // 遍历所有商品，计算折扣和最终价格
        for (Product product : products) {
            BigDecimal original = product.getTotalPrice();  // 原始价格
            BigDecimal current = context.getCurrentPrices().get(product).max(BigDecimal.ZERO);  // 当前价格，不能为负数
            finalPrices.put(product, current);  // 设置最终价格
            totalDiscounts.put(product, original.subtract(current));  // 计算折扣金额
        }

        // 计算总金额（折扣后小计+运费）
        BigDecimal total = finalSubtotal.add(context.getShippingFee());

        // 创建并返回计算结果对象
        return new CalculationResult(
            context.getOriginalTotal(),             // 原始总价
            context.getCouponDiscountAmount(),      // 优惠券优惠金额
            context.getMarketingDiscountAmount(),   // 活动优惠金额
            context.getIntegralDiscountAmount(),    // 积分抵扣金额
            context.getCommDiscountAmount(),        // 佣金抵扣金额
            finalSubtotal,                          // 折扣后小计
            context.getShippingFee(),               // 运费
            total                                   // 应付总额= 折扣后小计 + 运费
        );
    }

}
