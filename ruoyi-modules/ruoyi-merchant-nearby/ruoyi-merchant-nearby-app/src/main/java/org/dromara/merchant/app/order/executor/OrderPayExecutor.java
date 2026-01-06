package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.springframework.stereotype.Component;

/**
 * @Description 订单支付执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderPayExecutor {

    private final IOrderService orderService;

    public Boolean execute(OrderPayCmd cmd) {
        return orderService.payOrder(cmd);
    }
}