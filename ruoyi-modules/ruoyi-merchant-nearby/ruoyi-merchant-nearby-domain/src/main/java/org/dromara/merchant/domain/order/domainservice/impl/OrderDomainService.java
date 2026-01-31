package org.dromara.merchant.domain.order.domainservice.impl;

import com.alibaba.cola.statemachine.StateMachine;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.dromara.merchant.domain.order.statemachine.OrderEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @Description 订单领域服务实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Service
@RequiredArgsConstructor
public class OrderDomainService implements IOrderDomainService {

    private final IOrderGateway orderGateway;
    private final StateMachine<OrderStatus, OrderEvent, Order> stateMachine;

    /**
     * 创建订单
     * @param order 订单实体
     * @return 创建的订单ID
     */
    @Override
    public Long createOrder(Order order) {
        // 设置订单状态为待付款
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        // 设置订单编号
        if (order.getOrderNo() == null) {
            order.setOrderNo(generateOrderNo());
        }
        // 设置创建时间
        order.setCreateTime(LocalDateTime.now());
        // 保存订单
        boolean result = orderGateway.save(order);
        return result ? order.getOrderId() : null;
    }

    /**
     * 支付订单
     * @param orderId 订单ID
     * @param paymentMethod 支付方式
     * @param paymentOrderNo 支付订单号
     * @return 是否支付成功
     */
    @Override
    public boolean payOrder(Long orderId, String paymentMethod, String paymentOrderNo) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) return false;

        OrderStatus result = stateMachine.fireEvent(OrderStatus.PENDING_PAYMENT, OrderEvent.PAY, order);

        if (result == null) return false;

        order.setPaymentMethod(paymentMethod);
        order.setPaymentTime(LocalDateTime.now());
        order.setPaymentOrderNo(paymentOrderNo);
        order.setUpdateTime(LocalDateTime.now());

        return orderGateway.save(order);
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param cancelReason 取消原因
     * @return 是否取消成功
     */
    @Override
    public boolean cancelOrder(Long orderId, String cancelReason) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) return false;

        OrderStatus currentState = order.getStatus();
        OrderStatus result = stateMachine.fireEvent(currentState, OrderEvent.CANCEL, order);

        if (result == null) return false;

        order.setUpdateTime(LocalDateTime.now());
        // 添加取消原因到扩展信息
        if (order.getExtInfo() == null) {
            order.setExtInfo("{\"cancelReason\":\"" + cancelReason + "\"}");
        } else {
            order.setExtInfo(order.getExtInfo() + ",\"cancelReason\":\"" + cancelReason + "\"}");
        }
        return orderGateway.save(order);
    }

    /**
     * 发货订单
     * @param orderId 订单ID
     * @param expressCompany 快递公司
     * @param expressNo 快递单号
     * @return 是否发货成功
     */
    @Override
    public boolean deliverOrder(Long orderId, String expressCompany, String expressNo) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) return false;

        OrderStatus currentState = order.getStatus();
        OrderStatus result = stateMachine.fireEvent(currentState, OrderEvent.DELIVER, order);

        if (result == null) return false;

        order.setUpdateTime(LocalDateTime.now());
        // 添加物流信息到扩展信息
        order.setExtInfo("{\"expressCompany\":\"" + expressCompany + "\",\"expressNo\":\"" + expressNo + "\"}");
        return orderGateway.save(order);
    }

    /**
     * 确认收货订单
     * @param orderId 订单ID
     * @return 是否确认收货成功
     */
    @Override
    public boolean confirmReceipt(Long orderId) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) {
            return false;
        }

        OrderStatus currentState = order.getStatus();
        OrderStatus result = stateMachine.fireEvent(currentState, OrderEvent.CONFIRM_RECEIPT, order);

        if (result == null) {
            return false;
        }

        order.setUpdateTime(LocalDateTime.now());
        return orderGateway.save(order);
    }

    /**
     * 完成订单
     * @param orderId 订单ID
     * @return 是否完成订单成功
     */
    @Override
    public boolean completeOrder(Long orderId) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) {
            return false;
        }

        OrderStatus currentState = order.getStatus();
        OrderStatus result = stateMachine.fireEvent(currentState, OrderEvent.COMPLETE, order);

        if (result == null) {
            return false;
        }

        order.setUpdateTime(LocalDateTime.now());
        return orderGateway.save(order);
    }

    /**
     * 生成订单编号
     * @return 订单编号
     */
    private String generateOrderNo() {
        // 生成订单编号：时间戳 + 随机数
        return "ORD" + System.currentTimeMillis() + (int)(Math.random() * 10000);
    }
}
