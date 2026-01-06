package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;
import org.dromara.merchant.client.Command;

/**
 * @Description 订单取消命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderCancelCmd implements Command {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 扩展信息
     */
    private String extInfo;
}