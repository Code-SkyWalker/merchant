package org.dromara.merchant.client.freight.dto.data.command;

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
public class ConfigLocal implements DeliveryConfig {

    /**
     * 精确位置
     */
    private String preciseLocation;

    /**
     * 配送范围
     */
    private BigDecimal deliveryRange;

    /**
     * 配送费规则
     */
    private LocalDeliveryFeeConfig config;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocalDeliveryFeeConfig {
        /**
         * 起步价
         */
        private BigDecimal startingAmount;

        /**
         * 基础运费
         */
        private BigDecimal baseFee;

        /**
         * 是否包邮
         */
        private Boolean freeShipping;

        /**
         * 免运费金额
         */
        private BigDecimal freeShippingAmount;
    }
}
