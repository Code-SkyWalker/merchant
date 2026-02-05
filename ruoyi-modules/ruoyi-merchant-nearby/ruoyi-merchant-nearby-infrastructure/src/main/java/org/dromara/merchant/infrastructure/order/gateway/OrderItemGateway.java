package org.dromara.merchant.infrastructure.order.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.gateway.IOrderItemGateway;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.dromara.merchant.infrastructure.order.converter.OrderItemConverter;
import org.dromara.merchant.infrastructure.order.mapper.OrderItemMapper;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderItemDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/2/3 14:48
 */
@Component
@RequiredArgsConstructor
public class OrderItemGateway implements IOrderItemGateway {

    private final OrderItemMapper orderItemMapper;
    private final OrderItemConverter orderItemConverter;

    @Override
    public boolean save(List<OrderItem> orderItems) {
        List<OrderItemDO> dos = this.orderItemConverter.toListDO(orderItems);
        return this.orderItemMapper.insertOrUpdateBatch(dos);
    }
}
