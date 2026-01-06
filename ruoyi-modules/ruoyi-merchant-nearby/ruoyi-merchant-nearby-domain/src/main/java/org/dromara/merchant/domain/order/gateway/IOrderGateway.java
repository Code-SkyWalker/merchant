package org.dromara.merchant.domain.order.gateway;

import org.dromara.merchant.domain.order.model.Order;

/**
 * @Description 订单网关接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface IOrderGateway {

    /**
     * 保存订单
     * @param order 订单实体
     * @return 是否保存成功
     */
    boolean save(Order order);

    /**
     * 根据订单ID查询订单
     * @param orderId 订单ID
     * @return 订单实体
     */
    Order queryById(Long orderId);

    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单实体
     */
    Order queryByOrderNo(String orderNo);

    /**
     * 删除订单
     * @param orderId 订单ID
     * @return 是否删除成功
     */
    boolean delete(Long orderId);

    /**
     * 根据订单ID更新订单状态
     * @param orderId 订单ID
     * @param status 新订单状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long orderId, String status);
}
