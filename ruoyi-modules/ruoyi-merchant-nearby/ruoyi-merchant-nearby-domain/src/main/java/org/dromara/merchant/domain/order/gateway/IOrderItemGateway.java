package org.dromara.merchant.domain.order.gateway;

import org.dromara.merchant.domain.order.model.OrderItem;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/2/3 14:48
 */
public interface IOrderItemGateway {

    boolean save(List<OrderItem> orderItems);

}
