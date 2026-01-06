package org.dromara.merchant.domain.marketing.service;

import org.dromara.merchant.domain.commodity.model.Sku;

import java.math.BigDecimal;

/**
 * @Description 价格计算策略接口
 * @Author Code Skywalker
 * @Date 2026/1/4 14:35
 */
public interface PriceCalculationStrategy {

    /**
     * 计算商品最终价格
     *
     * @param sku           商品SKU
     * @param quantity      购买数量
     * @return 最终价格
     */
    BigDecimal calculateFinalPrice(Sku sku, Integer quantity);

}
