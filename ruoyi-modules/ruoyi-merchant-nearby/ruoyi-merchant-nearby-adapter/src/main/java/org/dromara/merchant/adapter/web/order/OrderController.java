package org.dromara.merchant.adapter.web.order;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.app.order.executor.OrderCancelExecutor;
import org.dromara.merchant.app.order.executor.OrderCreateExecutor;
import org.dromara.merchant.app.order.executor.OrderPayExecutor;
import org.dromara.merchant.app.order.executor.OrderRefundExecutor;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderPageCO;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;
import org.dromara.merchant.infrastructure.order.mapper.OrderMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单
 * @Author Code Skywalker
 * @Date 2026-01-05
 */
@RestController
@RequestMapping("/merchant/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    private final OrderMapper orderMapper;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public R<Long> createOrder(@Validated @RequestBody OrderCreateCmd cmd) {
        return R.ok(orderService.createOrder(cmd));
    }

    /**
     * 支付订单
     */
    @PostMapping("/pay")
    public R<Map<String, Object>> payOrder(@Validated @RequestBody OrderPayCmd cmd) {
        return R.ok(orderService.payOrder(cmd));
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public R<Boolean> cancelOrder(@Validated @RequestBody OrderCancelCmd cmd) {
        return R.ok(orderService.cancelOrder(cmd));
    }

    /**
     * 申请退款
     */
    @PostMapping("/apply-refund")
    public R<Boolean> applyRefund(@RequestParam Long orderId, @RequestParam String refundReason) {
        return R.ok(orderService.applyRefund(orderId, refundReason));
    }

    /**
     * 同意退款
     */
    @PostMapping("/approve-refund")
    public R<Boolean> approveRefund(@RequestParam Long orderId, @RequestParam String refundOrderNo) {
        return R.ok(orderService.approveRefund(orderId, refundOrderNo));
    }

    /**
     * 拒绝退款
     */
    @PostMapping("/reject-refund")
    public R<Boolean> rejectRefund(@RequestParam Long orderId, @RequestParam String rejectReason) {
        return R.ok(orderService.rejectRefund(orderId, rejectReason));
    }

    /**
     * 完成订单
     */
    @PostMapping("/complete")
    public R<Boolean> completeOrder(@RequestParam Long orderId) {
        return R.ok(orderService.completeOrder(orderId));
    }

    /**
     * 分页查询订单
     */
    @GetMapping("/page")
    public TableDataInfo<OrderPageCO> queryOrder(@ModelAttribute OrderQry qry, @ModelAttribute PageQuery page) {
        return TableDataInfo.build(this.orderMapper.queryOrderPage(page.build(), qry));
    }

    /**
     * 根据ID查询订单
     */
    @GetMapping("/{orderId}")
    public R<OrderCO> queryOrderById(@PathVariable Long orderId) {
        return R.ok(this.orderMapper.queryOrderByOrderId(orderId));
    }

}
