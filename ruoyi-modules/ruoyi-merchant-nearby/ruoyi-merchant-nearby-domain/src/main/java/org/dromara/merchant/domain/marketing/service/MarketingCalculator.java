package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.marketing.discount.CalculationContext;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 优惠活动价格计算器
 * @Author Code Skywalker
 * @Date 2026/1/12 17:39
 */
public class MarketingCalculator implements PriceCalculator {
    private PriceCalculator nextCalculator;  // 链中的下一个处理器


    /**
     * 执行价格计算
     *
     * @param products 商品列表
     * @param context  计算上下文
     * @return 计算结果
     */
    @Override
    public CalculationResult calculate(List<Product> products, CalculationContext context) {
        // 获取所有阶梯价活动
        List<Marketing> marketings = context.getMarketings();

        // 获取当前价格映射
        Map<Product, BigDecimal> currentPrices = context.getCurrentPrices();

        // 对每个阶梯价活动进行处理
        for (Marketing marketing : marketings) {

            // 计算总优惠金额
            BigDecimal totalDiscount = marketing.calculateTotalDiscount(products, currentPrices);
            // 分配优惠金额到各个商品
            Map<Product, BigDecimal> allocated = marketing.allocateByProportion(totalDiscount, products, currentPrices);

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
