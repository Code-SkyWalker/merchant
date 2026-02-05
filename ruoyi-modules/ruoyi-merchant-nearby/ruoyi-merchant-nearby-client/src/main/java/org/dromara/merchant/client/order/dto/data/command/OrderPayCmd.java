package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 订单支付命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderPayCmd {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 支付方式：
     * T_JSAPI: 微信公众号（pc、h5）
     * T_MINIAPP: 微信小程序 (微信小程序、app）
     * A_JSAPI: 支付宝JS（pc、支付宝小程序）
     * A_NATIVE: 支付宝正扫（app，h5）
     * U_NATIVE: 银联正扫
     * U_JSAPI: 银联JS
     */
    private String paymentMethod;

    /**
     * 子商户应用ID（微信支付必填）
     */
    private String sub_appid;

    /**
     * 子商户用户标识（微信支付必填）
     */
    private String sub_openid;
}
