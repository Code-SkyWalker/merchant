package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;
import org.dromara.merchant.client.Command;

import java.math.BigDecimal;

/**
 * @Description 订单支付命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderPayCmd implements Command {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 支付订单号
     */
    private String paymentOrderNo;

    /**
     * 第三方支付订单号
     */
    private String thirdPartyOrderNo;

    /**
     * 扩展信息
     */
    private String extInfo;
}