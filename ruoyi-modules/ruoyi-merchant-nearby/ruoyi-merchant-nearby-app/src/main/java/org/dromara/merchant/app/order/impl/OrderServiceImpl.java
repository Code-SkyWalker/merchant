package org.dromara.merchant.app.order.impl;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.infrastructure.order.converter.OrderConvertor;
import org.springframework.stereotype.Service;

/**
 * @Description 订单应用服务实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderDomainService orderDomainService;
    private final IOrderGateway orderGateway;
    private final OrderConvertor orderConvertor;


    @Override
    public Long createOrder(OrderCreateCmd cmd) {
        // 将命令对象转换为领域实体
        Order order = orderConvertor.toEntity(cmd);
        // 调用领域服务创建订单
        return orderDomainService.createOrder(order);
    }

    @Override
    public boolean payOrder(OrderPayCmd cmd) {
        // 调用领域服务支付订单
        return orderDomainService.payOrder(cmd.getOrderId(), cmd.getPaymentMethod(), cmd.getPaymentOrderNo());
    }

    @Override
    public boolean cancelOrder(OrderCancelCmd cmd) {
        // 调用领域服务取消订单
        return orderDomainService.cancelOrder(cmd.getOrderId(), cmd.getCancelReason());
    }

    @Override
    public OrderCO queryById(Long orderId) {
        // 从网关查询订单
        Order order = orderGateway.queryById(orderId);
        // 将领域实体转换为客户端对象
        return orderConvertor.toOrderCO(order);
    }

    @Override
    public OrderCO queryByOrderNo(String orderNo) {
        // 从网关查询订单
        Order order = orderGateway.queryByOrderNo(orderNo);
        // 将领域实体转换为客户端对象
        return orderConvertor.toOrderCO(order);
    }

    @Override
    public OrderCO queryOrder(OrderQry qry) {
        // 根据查询条件查询订单
        Order order = null;
        if (qry.getOrderId() != null) {
            order = orderGateway.queryById(qry.getOrderId());
        } else if (qry.getOrderNo() != null) {
            order = orderGateway.queryByOrderNo(qry.getOrderNo());
        }
        // 将领域实体转换为客户端对象
        return orderConvertor.toOrderCO(order);
    }
}
