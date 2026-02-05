package org.dromara.merchant.infrastructure.order.converter;

import org.dromara.merchant.domain.order.model.OrderItem;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/26 10:25
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemConverter {

    List<OrderItemDO> toListDO(List<OrderItem> orderItems);
}
