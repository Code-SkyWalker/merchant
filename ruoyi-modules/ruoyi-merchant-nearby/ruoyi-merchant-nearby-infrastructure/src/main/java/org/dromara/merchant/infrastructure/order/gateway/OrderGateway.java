package org.dromara.merchant.infrastructure.order.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.infrastructure.order.converter.OrderConvertor;
import org.dromara.merchant.infrastructure.order.mapper.OrderMapper;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderDO;
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
        OrderDO orderDO = mapper.selectById(orderId);
        return this.convertor.toEntity(orderDO);
    }

    @Override
    public Order queryByOrderNo(String orderNo) {
        // 这里需要根据实际的查询条件来实现
        // 可以通过自定义查询或使用Wrapper
        OrderDO orderDO = mapper.selectByOrderNo(orderNo);
        return this.convertor.toEntity(orderDO);
    }

    @Override
    public boolean delete(Long orderId) {
        return mapper.deleteById(orderId) > 0;
    }

    @Override
    public boolean updateStatus(Long orderId, String status) {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderId(orderId);
        orderDO.setStatus(status);
        return mapper.updateById(orderDO) > 0;
    }


}
