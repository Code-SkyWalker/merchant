package org.dromara.merchant.domain.marketing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.domain.marketing.discount.Activities;
import org.dromara.merchant.domain.marketing.discount.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description 营销活动规则
 * @Author Code Skywalker
 * @Date 2025/12/26 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marketing implements Activities {

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
        if (!MarketingType.MULTIUNIT.equals(type)) {
            return products.stream().filter(this::isProductEligible)
                .map(product -> rules.calculate(currentPrices.get(product), product.getQuantity()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        }

        return products.stream().filter(this::isProductEligible)
            .map(product -> rules.calculate(currentPrices.get(product), product.getQuantity()))
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
            .anyMatch(couponSpu -> couponSpu.getSpuId().equals(product.getSpuId()));
    }
}
