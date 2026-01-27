package org.dromara.merchant.domain.freight.model;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 同城配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigLocal implements DeliveryConfig {

    private final String type = "local";

    /**
     * 精确位置
     */
    private String preciseLocation;

    /**
     * 配送范围, 单位公里
     */
    private BigDecimal deliveryRange;

    private LocalDateTime deliveryTime;

    /**
     * 配送费规则
     */
    private LocalDeliveryFeeConfig config;

    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Override
    public String type() {
        return this.type;
    }

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
