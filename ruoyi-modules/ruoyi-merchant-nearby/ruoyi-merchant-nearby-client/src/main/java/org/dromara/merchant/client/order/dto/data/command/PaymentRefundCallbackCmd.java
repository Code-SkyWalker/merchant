package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;

/**
 * @Description 订单支付成功回调命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class PaymentRefundCallbackCmd {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付订单号
     */
    private String refundOrderNo;
}
