package org.dromara.merchant.client.order.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 订单支付成功回调命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSucceedCallbackCmd {

    /**
     * 支付订单号
     */
    private String paymentOrderNo;


    /**
     * 支付金额
     */
    private String payAmount;
}
