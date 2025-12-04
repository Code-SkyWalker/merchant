package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description 同城配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigLocal {

    private String preciseLocation;

    private BigDecimal deliveryRange;

    private LocalDeliveryFeeConfig config;


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
