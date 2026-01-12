package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.marketing.discount.CalculationContext;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 优惠券计算器
 * @Author Code Skywalker
 * @Date 2026/1/12 15:42
 */
public class CouponCalculator implements PriceCalculator {

    private PriceCalculator nextCalculator;

    /**
     * 执行价格计算
     *
     * @param products 商品列表
     * @param context  计算上下文
     * @return 计算结果
     */
    @Override
    public CalculationResult calculate(List<Product> products, CalculationContext context) {
        // 获取所有全局优惠券（品类券/全局券）
        List<Coupon> coupons = context.getCoupons();

        // 获取当前价格映射
        Map<Product, BigDecimal> currentPrices = context.getCurrentPrices();

        // 对每个全局优惠券进行处理
        for (Coupon coupon : coupons) {
            BigDecimal totalDiscount = coupon.calculateTotalDiscount(products, currentPrices);

            // 分配优惠金额到各个商品
            Map<Product, BigDecimal> allocated = coupon.allocateByProportion(totalDiscount, products, currentPrices);

            // 更新商品价格
            for (Product product : products) {
                BigDecimal current = currentPrices.get(product);
                BigDecimal discount = allocated.getOrDefault(product, BigDecimal.ZERO);
                currentPrices.put(product, current.subtract(discount));
            }
        }

        // 如果还有下一个处理器，继续传递请求
        if (nextCalculator != null) {
            return nextCalculator.calculate(products, context);
        }

        // 如果这是最后一个处理器，创建并返回结果
        return createFinalResult(products, context);
    }

    /**
     * 设置下一个处理器
     *
     * @param nextCalculator 下一个处理器
     * @return 下一个处理器
     */
    @Override
    public PriceCalculator setNext(PriceCalculator nextCalculator) {
        this.nextCalculator = nextCalculator;
        return nextCalculator;
    }

}
