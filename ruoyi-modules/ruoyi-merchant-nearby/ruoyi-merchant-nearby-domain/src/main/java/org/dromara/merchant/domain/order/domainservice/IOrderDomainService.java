package org.dromara.merchant.domain.order.domainservice;

import org.dromara.merchant.domain.order.model.Order;

/**
 * @Description 订单领域服务接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface IOrderDomainService {

    /**
     * 创建订单
     *
     * @param order 订单实体
     * @return 创建的订单ID
     */
    boolean createOrder(Order order);

    /**
     * 支付订单
     *
     * @param payAmount      支付金额
     * @param paymentOrderNo 支付订单号
     * @return 是否支付成功
     */
    boolean payOrder(String payAmount, String paymentOrderNo);

    /**
     * 取消订单
     *
     * @param order        订单实体
     * @param cancelReason 取消原因
     * @return 是否取消成功
     */
    boolean cancelOrder(Order order, String cancelReason);

    /**
     * 发货订单
     *
     * @param orderId        订单ID
     * @param expressCompany 快递公司
     * @param expressNo      快递单号
     * @return 是否发货成功
     */
    boolean deliverOrder(Long orderId, String expressCompany, String expressNo);

    /**
     * 确认收货订单
     *
     * @param order 订单实体
     * @return 是否确认收货成功
     */
    boolean confirmReceipt(Order order);

}
