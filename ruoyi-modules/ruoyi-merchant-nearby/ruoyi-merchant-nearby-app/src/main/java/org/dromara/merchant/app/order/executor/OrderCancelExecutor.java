package org.dromara.merchant.app.order.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.springframework.stereotype.Component;

/**
 * @Description 订单取消执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderCancelExecutor {

    private final IOrderDomainService orderDomainService;

    public Boolean execute(OrderCancelCmd cmd) {
        return orderDomainService.cancelOrder(cmd.getOrderId(), cmd.getCancelReason());
    }
}
