package org.dromara.merchant.infrastructure.order.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/26 10:25
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemConverter {


}
