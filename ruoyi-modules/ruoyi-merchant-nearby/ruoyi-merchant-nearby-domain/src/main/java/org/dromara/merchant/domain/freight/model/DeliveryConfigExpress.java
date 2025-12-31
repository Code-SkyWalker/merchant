package org.dromara.merchant.domain.freight.model;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description 快递配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigExpress implements DeliveryConfig {

    private final String type = "express";

    /**
     * 配送费规则 0: 叠加计算 1: 最高运费计算
     */
    private Integer deliveryFeeRule;

    /**
     * 是否包邮
     */
    private Boolean freeShipping;

    /**
     * 免运费金额
     */
    private BigDecimal freeShippingAmount;

    @Override
    public String toJson() {
        return new JSONObject( this).toString();
    }

    @Override
    public String type() {
        return this.type;
    }
}
