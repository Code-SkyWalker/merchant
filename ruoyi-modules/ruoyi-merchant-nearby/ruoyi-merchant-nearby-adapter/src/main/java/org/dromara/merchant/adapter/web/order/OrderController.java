package org.dromara.merchant.adapter.web.order;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.executor.OrderCancelExecutor;
import org.dromara.merchant.app.order.executor.OrderCreateExecutor;
import org.dromara.merchant.app.order.executor.OrderPayExecutor;
import org.dromara.merchant.app.order.executor.OrderRefundExecutor;
import org.dromara.merchant.app.order.executor.query.OrderQueryExecutor;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 订单控制器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@RestController
@RequestMapping("/merchant/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderCreateExecutor orderCreateExecutor;
    private final OrderPayExecutor orderPayExecutor;
    private final OrderCancelExecutor orderCancelExecutor;
    private final OrderQueryExecutor orderQueryExecutor;
    private final OrderRefundExecutor orderRefundExecutor;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public Long createOrder(@Validated @RequestBody OrderCreateCmd cmd) {
        return orderCreateExecutor.execute(cmd);
    }

    /**
     * 支付订单
     */
    @PostMapping("/pay")
    public Boolean payOrder(@Validated @RequestBody OrderPayCmd cmd) {
        return orderPayExecutor.execute(cmd);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public Boolean cancelOrder(@Validated @RequestBody OrderCancelCmd cmd) {
        return orderCancelExecutor.execute(cmd);
    }

    /**
     * 申请退款
     */
    @PostMapping("/apply-refund")
    public Boolean applyRefund(@RequestParam Long orderId, @RequestParam String refundReason) {
        return orderRefundExecutor.applyRefund(orderId, refundReason);
    }

    /**
     * 同意退款
     */
    @PostMapping("/approve-refund")
    public Boolean approveRefund(@RequestParam Long orderId, @RequestParam String refundOrderNo) {
        return orderRefundExecutor.approveRefund(orderId, refundOrderNo);
    }

    /**
     * 拒绝退款
     */
    @PostMapping("/reject-refund")
    public Boolean rejectRefund(@RequestParam Long orderId, @RequestParam String rejectReason) {
        return orderRefundExecutor.rejectRefund(orderId, rejectReason);
    }

    /**
     * 查询订单
     */
    @GetMapping("/query")
    public OrderCO queryOrder(OrderQry qry) {
        return orderQueryExecutor.execute(qry);
    }

    /**
     * 根据ID查询订单
     */
    @GetMapping("/{orderId}")
    public OrderCO queryOrderById(@PathVariable Long orderId) {
        OrderQry qry = new OrderQry();
        qry.setOrderId(orderId);
        return orderQueryExecutor.execute(qry);
    }

    /**
     * 根据订单编号查询订单
     */
    @GetMapping("/no/{orderNo}")
    public OrderCO queryOrderByNo(@PathVariable String orderNo) {
        OrderQry qry = new OrderQry();
        qry.setOrderNo(orderNo);
        return orderQueryExecutor.execute(qry);
    }
}
