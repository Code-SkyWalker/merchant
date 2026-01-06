package org.dromara.merchant.infrastructure.order.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderDO;

/**
 * @Description 订单Mapper接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface OrderMapper extends BaseMapperPlus<OrderDO, OrderDO> {

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo 订单编号
     * @return 订单
     */
    OrderDO selectByOrderNo(String orderNo);
}
