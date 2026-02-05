package org.dromara.merchant.infrastructure.order.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.infrastructure.order.converter.OrderConvertor;
import org.dromara.merchant.infrastructure.order.mapper.OrderMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 订单网关实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderGateway implements IOrderGateway {

    private final OrderMapper mapper;
    private final OrderConvertor convertor;

    @Override
    public boolean save(Order order) {
        return mapper.insertOrUpdate(this.convertor.toDo(order));
    }

    @Override
    public Order queryById(Long orderId) {
        return mapper.queryById(orderId);
    }

    @Override
    public boolean delete(Long orderId) {
        return mapper.deleteById(orderId) > 0;
    }

    @Override
    public Order queryOrderByPaymentOrderId(String paymentOrderId) {
        return mapper.queryByPaymentOrderId(paymentOrderId);
    }
}
