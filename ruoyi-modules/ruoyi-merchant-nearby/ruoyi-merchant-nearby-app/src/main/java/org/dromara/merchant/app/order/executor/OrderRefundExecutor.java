package org.dromara.merchant.app.order.executor;

import cn.hutool.json.JSONObject;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.dromara.merchant.domain.order.statemachine.OrderEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.dromara.merchant.domain.order.statemachine.OrderStateMachineConfig.ORDER_STATE_MACHINE_ID;

/**
 * @Description 订单退款执行器
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
@Component
@RequiredArgsConstructor
public class OrderRefundExecutor {

    private final IOrderGateway orderGateway;
    private StateMachine<OrderStatus, OrderEvent, Order> stateMachine;

    private StateMachine<OrderStatus, OrderEvent, Order> getStateMachine() {
        if (stateMachine == null) {
            stateMachine = StateMachineFactory.get(ORDER_STATE_MACHINE_ID);
        }
        return stateMachine;
    }


    /**
     * 申请退款
     * @param orderId 订单ID
     * @param refundReason 退款原因
     * @return 是否申请成功
     */
    public Boolean applyRefund(Long orderId, String refundReason) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) {
            return false;
        }


        OrderStatus currentState = order.getStatus();
        OrderStatus result = getStateMachine().fireEvent(currentState, OrderEvent.APPLY_REFUND, order);

        if (result == null) {
            return false;
        }

        order.setRefundTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        // 添加退款原因到扩展信息
        JSONObject extInfo;
        if (order.getExtInfo() == null) {
            extInfo = new JSONObject();
        } else {
            extInfo = new JSONObject(order.getExtInfo());
        }
        extInfo.set("refundReason", refundReason);
        order.setExtInfo(extInfo.toString());

        return orderGateway.save(order);
    }

    /**
     * 同意退款
     * @param orderId 订单ID
     * @param refundOrderNo 退款订单号
     * @return 是否同意成功
     */
    public Boolean approveRefund(Long orderId, String refundOrderNo) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) {
            return false;
        }

        OrderStatus currentStatus = order.getStatus();
        OrderStatus result = getStateMachine().fireEvent(currentStatus, OrderEvent.APPROVE_REFUND, order);

        if (result == null) {
            return false;
        }

        order.setRefundOrderNo(refundOrderNo);
        order.setRefundTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        return orderGateway.save(order);
    }

    /**
     * 拒绝退款
     * @param orderId 订单ID
     * @param rejectReason 拒绝原因
     * @return 是否拒绝成功
     */
    public Boolean rejectRefund(Long orderId, String rejectReason) {
        Order order = orderGateway.queryById(orderId);
        if (order == null) {
            return false;
        }

        OrderStatus currentState = order.getStatus();
        OrderStatus result = getStateMachine().fireEvent(currentState, OrderEvent.REJECT_REFUND, order);

        if (result == null) {
            return false;
        }

        order.setUpdateTime(LocalDateTime.now());
        // 添加拒绝原因到扩展信息
        JSONObject extInfo;
        if (order.getExtInfo() == null) {
            extInfo = new JSONObject();
        } else {
            extInfo = new JSONObject(order.getExtInfo());
        }
        extInfo.set("rejectReason", rejectReason);
        order.setExtInfo(extInfo.toString());

        return orderGateway.save(order);
    }
}
