package org.dromara.merchant.domain.marketing.model.activity;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.domain.marketing.discount.Activity;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 营销活动规则
 * @Author Code Skywalker
 * @Date 2025/12/26 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marketing implements Activity {

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
     * 活动规则
     */
    private Rule rules;

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
        LocalDateTime now = LocalDateTime.now();
        return receiveBegin.isBefore(now) && receiveEnd.isAfter(now);
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
        // 如果未达到门槛，返回0
        if (!isApplicable(products, currentPrices)) return BigDecimal.ZERO;

        // 如果是多件多折模式，则需要计算多件多折的总优惠金额
        if (MarketingType.MULTIUNIT.equals(type)) {
            RuleMultiUnitDiscount multiUnitRule = new JSONObject(rules).toBean(RuleMultiUnitDiscount.class);

            BigDecimal priceTotal = calculateEligibleTotal(products, currentPrices);
            int quantityTotal = calculateEligibleTotal(products);

            return multiUnitRule.calculateTotalDiscount(priceTotal, quantityTotal);
        }

        return products.stream().filter(this::isProductEligible)
            .map(product -> rules.calculate(product.getOriginalPrice(), product.getQuantity()))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);

    }

    /**
     * 判断商品是否符合活动条件
     *
     * @param product 待检查的商品
     * @return 如果符合条件返回true，否则返回false
     */
    @Override
    public boolean isProductEligible(Product product) {
        // 判断商品是否在优惠券的指定商品列表中
        return this.marketingSpus.stream()
            .anyMatch(marketingSpu -> marketingSpu.getSpuId().equals(product.getSpuId()));
    }

    /**
     * 按比例分配优惠金额
     * @param totalDiscount 总优惠金额
     * @param products 商品列表
     * @param currentPrices 当前各商品的价格
     * @return 每个商品对应的优惠金额
     */
    public Map<Product, BigDecimal> allocateByProportion(BigDecimal totalDiscount, List<Product> products, Map<Product, BigDecimal> currentPrices) {
        // 计算符合条件商品的总价值
        BigDecimal totalEligible = calculateEligibleTotal(products, currentPrices);
        // 如果没有符合条件的商品，返回空映射
        if (totalEligible.compareTo(BigDecimal.ZERO) == 0) {
            return Collections.emptyMap();
        }

        Map<Product, BigDecimal> result = new HashMap<>();
        BigDecimal allocated = BigDecimal.ZERO;

        // 获取符合条件的商品列表
        List<Product> eligibleProducts = products.stream()
            .filter(this::isProductEligible)
            .toList();

        // 按比例分配优惠金额
        for (int i = 0; i < eligibleProducts.size(); i++) {
            Product product = eligibleProducts.get(i);
            // 使用当前价格，如果不存在则使用商品原始价格
            BigDecimal productPrice = currentPrices.getOrDefault(product, product.getTotalPrice());
            // 计算该商品价格占符合条件商品总价的比例
            BigDecimal ratio = productPrice.divide(totalEligible, 4, RoundingMode.HALF_UP);
            // 按比例计算该商品应分配的优惠金额
            BigDecimal discount = totalDiscount.multiply(ratio).setScale(2, RoundingMode.HALF_UP);

            result.put(product, discount);
            allocated = allocated.add(discount);
        }

        // 处理因四舍五入导致的小数点差异，将差额加到最后一个符合条件的商品上
        if (allocated.compareTo(totalDiscount) != 0 && !eligibleProducts.isEmpty()) {
            Product lastProduct = eligibleProducts.get(eligibleProducts.size() - 1);
            BigDecimal diff = totalDiscount.subtract(allocated);
            result.put(lastProduct, result.getOrDefault(lastProduct, BigDecimal.ZERO).add(diff));
        }

        return result;
    }
}
