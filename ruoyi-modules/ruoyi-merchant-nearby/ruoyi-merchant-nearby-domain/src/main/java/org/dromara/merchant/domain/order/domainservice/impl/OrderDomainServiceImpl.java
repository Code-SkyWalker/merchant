package org.dromara.merchant.domain.order.domainservice.impl;

import com.alibaba.cola.statemachine.StateMachine;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;

import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.dromara.merchant.domain.order.statemachine.OrderEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @Description 订单领域服务实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Service
@RequiredArgsConstructor
public class OrderDomainServiceImpl implements IOrderDomainService {

    private final IOrderGateway orderGateway;
    private final StateMachine<OrderStatus, OrderEvent, Order> stateMachine;


    @Override
    public Long createOrder(Order order) {
        // 计算订单金额
        order = calculateOrderAmount(order);
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

    @Override
    public Order calculateOrderAmount(Order order) {
        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            return order;
        }

        BigDecimal goodsAmount = BigDecimal.ZERO; // 商品总金额
        BigDecimal discountAmount = BigDecimal.ZERO; // 优惠总金额
        BigDecimal totalFinalAmount = BigDecimal.ZERO; // 订单项优惠后总金额

        for (org.dromara.merchant.domain.order.model.OrderItem item : order.getOrderItems()) {
            // 计算小计金额：单价 * 数量
            BigDecimal subtotal = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
            item.setSubtotal(subtotal);

            // 计算优惠金额
            if (item.getDiscountAmount() != null) {
                discountAmount = discountAmount.add(item.getDiscountAmount());
            }

            // 计算优惠后金额
            BigDecimal finalAmount = subtotal.subtract(item.getDiscountAmount() != null ? item.getDiscountAmount() : BigDecimal.ZERO);
            item.setFinalAmount(finalAmount);
            totalFinalAmount = totalFinalAmount.add(finalAmount);
        }

        // 设置商品总金额
        order.setGoodsAmount(totalFinalAmount.add(discountAmount));

        // 实付金额默认等于应付金额（实际支付可能通过支付回调更新）
        order.setPaidAmount(order.getPayableAmount());

        return order;
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
