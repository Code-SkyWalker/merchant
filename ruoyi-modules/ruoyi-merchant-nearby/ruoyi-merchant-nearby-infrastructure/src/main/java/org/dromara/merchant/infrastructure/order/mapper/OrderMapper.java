package org.dromara.merchant.infrastructure.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderPageCO;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderDO;

/**
 * @Description 订单Mapper接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface OrderMapper extends BaseMapperPlus<OrderDO, OrderDO> {

    /**
     * 查询订单分页列表
     */
    Page<OrderPageCO> queryOrderPage(Page<OrderPageCO> page, @Param("qry") OrderQry qry);

    /**
     * 根据订单ID查询订单CO
     */
    OrderCO queryOrderByOrderId(@Param("orderId") Long orderId);

}
