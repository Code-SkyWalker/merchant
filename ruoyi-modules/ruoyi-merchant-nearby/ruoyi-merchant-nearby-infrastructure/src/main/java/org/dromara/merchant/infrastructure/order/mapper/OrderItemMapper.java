package org.dromara.merchant.infrastructure.order.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderItemCO;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderItemDO;

import java.util.List;

/**
 * @Description 订单项Mapper接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface OrderItemMapper extends BaseMapperPlus<OrderItemDO, OrderItemDO> {

    /**
     * 根据订单ID查询订单项
     *
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItemCO> queryOrderItemCOByOrderId(@Param("orderId") Long orderId);

}
