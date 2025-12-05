package org.dromara.merchant.domain.freight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.hutool.json.JSONObject;

import java.math.BigDecimal;

/**
 * @Description 同城配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigLocal implements DeliveryConfig {

    private String preciseLocation;

    private BigDecimal deliveryRange;

    private LocalDeliveryFeeConfig config;

    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocalDeliveryFeeConfig {
        private BigDecimal startingAmount;
        private BigDecimal baseFee;
        private Boolean freeShipping;
        private BigDecimal freeShippingAmount;
    }
}
