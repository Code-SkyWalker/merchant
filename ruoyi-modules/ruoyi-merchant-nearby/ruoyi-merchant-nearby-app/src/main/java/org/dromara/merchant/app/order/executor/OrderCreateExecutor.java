package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.springframework.stereotype.Component;

/**
 * @Description 订单创建执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderCreateExecutor {

    private final IOrderService orderService;

    public Long execute(OrderCreateCmd cmd) {
        return orderService.createOrder(cmd);
    }
}