package org.dromara.merchant.domain.order.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Description 订单状态机配置
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
@Component
public class OrderStateMachineConfig {

    public static final String ORDER_STATE_MACHINE_ID = "OrderStateMachine";

    @Bean
    public StateMachine<OrderStatus, OrderEvent, Order> buildMachine() {
        StateMachineBuilder<OrderStatus, OrderEvent, Order> builder = StateMachineBuilderFactory.create();

        // 待付款 -> 待发货 (支付事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_PAYMENT)
                .to(OrderStatus.PENDING_DELIVERY)
                .on(OrderEvent.PAY)
                .when(order -> order.getStatus() == OrderStatus.PENDING_PAYMENT)
                .perform(doAction());

        // 待付款 -> 已取消 (取消事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_PAYMENT)
                .to(OrderStatus.CANCELLED)
                .on(OrderEvent.CANCEL)
                .when(order -> order.getStatus() == OrderStatus.PENDING_PAYMENT)
                .perform(doAction());

        // 待发货 -> 待收货 (发货事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_DELIVERY)
                .to(OrderStatus.PENDING_RECEIPT)
                .on(OrderEvent.DELIVER)
                .when(order -> order.getStatus() == OrderStatus.PENDING_DELIVERY)
                .perform(doAction());

        // 待发货 -> 已取消 (取消事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_DELIVERY)
                .to(OrderStatus.CANCELLED)
                .on(OrderEvent.CANCEL)
                .when(order -> order.getStatus() == OrderStatus.PENDING_DELIVERY)
                .perform(doAction());

        // 待发货 -> 退款中 (申请退款事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_DELIVERY)
                .to(OrderStatus.REFUNDING)
                .on(OrderEvent.APPLY_REFUND)
                .when(order -> order.getStatus() == OrderStatus.PENDING_DELIVERY)
                .perform(doAction());

        // 待收货 -> 已完成 (确认收货事件)
        builder.externalTransition()
                .from(OrderStatus.PENDING_RECEIPT)
                .to(OrderStatus.COMPLETED)
                .on(OrderEvent.CONFIRM_RECEIPT)
                .when(order -> order.getStatus() == OrderStatus.PENDING_RECEIPT)
                .perform(doAction());

        // 已完成 -> 退款中 (申请退款事件)
        builder.externalTransition()
                .from(OrderStatus.COMPLETED)
                .to(OrderStatus.REFUNDING)
                .on(OrderEvent.APPLY_REFUND)
                .when(order -> order.getStatus() == OrderStatus.COMPLETED)
                .perform(doAction());

        // 退款中 -> 已退款 (同意退款事件)
        builder.externalTransition()
                .from(OrderStatus.REFUNDING)
                .to(OrderStatus.REFUNDED)
                .on(OrderEvent.APPROVE_REFUND)
                .when(order -> order.getStatus() == OrderStatus.REFUNDING)
                .perform(doAction());

        return builder.build(ORDER_STATE_MACHINE_ID);
    }

    private Action<OrderStatus, OrderEvent, Order> doAction() {
        return (from, to, event, order) -> {
            // 在状态转换时执行的业务逻辑
            // 更新订单状态
            order.setStatus(to);
        };
    }
}
