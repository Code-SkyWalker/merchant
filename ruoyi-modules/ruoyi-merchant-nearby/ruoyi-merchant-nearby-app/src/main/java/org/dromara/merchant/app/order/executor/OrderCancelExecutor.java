package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.springframework.stereotype.Component;

/**
 * @Description 订单取消执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderCancelExecutor {

    private final IOrderService orderService;

    public Boolean execute(OrderCancelCmd cmd) {
        return orderService.cancelOrder(cmd);
    }
}