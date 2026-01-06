package org.dromara.merchant.app.order.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;
import org.springframework.stereotype.Component;

/**
 * @Description 订单查询执行器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderQueryExecutor {

    private final IOrderService orderService;

    public OrderCO execute(OrderQry qry) {
        return orderService.queryOrder(qry);
    }
}