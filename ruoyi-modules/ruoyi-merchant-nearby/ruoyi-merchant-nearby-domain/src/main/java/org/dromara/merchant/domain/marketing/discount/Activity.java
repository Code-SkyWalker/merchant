package org.dromara.merchant.domain.marketing.discount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 商城活动接口
 * @Author Code Skywalker
 * @Date 2026/1/8 16:35
 */
public interface Activity {

    /**
     * 判断活动是否适用于当前购物车
     * @param products 商品列表
     * @param currentPrices 当前各商品的价格
     * @return 如果适用返回true，否则返回false
     */
    boolean isApplicable(List<Product> products, Map<Product, BigDecimal> currentPrices);

    /**
     * 计算总优惠金额
     * @param products 商品列表
     * @param currentPrices 当前各商品的价格
     * @return 总优惠金额
     */
    BigDecimal calculateTotalDiscount(List<Product> products, Map<Product, BigDecimal> currentPrices);

    /**
     * 判断商品是否符合活动条件
     * @param product 待检查的商品
     * @return 如果符合条件返回true，否则返回false
     */
    boolean isProductEligible(Product product);

    /**
     * 计算符合条件商品的总价值
     * @param products 商品列表
     * @param currentPrices 当前各商品的价格
     * @return 符合条件商品的总价值
     */
    default BigDecimal calculateEligibleTotal(List<Product> products, Map<Product, BigDecimal> currentPrices) {
        return products.stream()
            .filter(this::isProductEligible)  // 过滤符合条件的商品
            .map(Product::getTotalPrice)  // 映射为价格
            .reduce(BigDecimal.ZERO, BigDecimal::add);  // 求和
    }

    /**
     * 计算符合条件商品的总数量
     * @param products 商品列表
     * @return 符合条件商品的总价值
     */
    default int calculateEligibleTotal(List<Product> products) {
        return products.stream()
            .filter(this::isProductEligible)
            .map(Product::getQuantity)
            .reduce(0, Integer::sum);
    }

}
