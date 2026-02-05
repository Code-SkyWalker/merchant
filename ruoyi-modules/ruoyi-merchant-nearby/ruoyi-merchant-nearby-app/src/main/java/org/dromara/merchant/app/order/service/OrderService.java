package org.dromara.merchant.app.order.service;

import cn.hutool.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.huifu.app.payment.executor.CommodityDetail;
import org.dromara.huifu.app.payment.executor.HuiFuPaymentRequest;
import org.dromara.huifu.app.payment.service.HuiFuPaymentService;
import org.dromara.huifu.app.payment.service.IHuifuCallbackHandler;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.app.order.executor.OrderCreateExecutor;
import org.dromara.merchant.app.order.executor.OrderPayExecutor;
import org.dromara.merchant.app.order.executor.OrderRefundExecutor;
import org.dromara.merchant.client.order.dto.data.command.*;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.dromara.common.core.enums.FormatsType.YYYYMMDDHHMMSS;

/**
 * @Description 订单应用服务实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService, IHuifuCallbackHandler {

    private final OrderPayExecutor orderPayExecutor;
    private final OrderRefundExecutor orderRefundExecutor;
    private final OrderCreateExecutor orderCreateExecutor;

    private final IOrderDomainService orderDomainService;

    private final HuiFuPaymentService huiFuPaymentService;

    private final IOrderGateway orderGateway;

    /**
     * 创建订单
     *
     * @param cmd 订单创建命令
     * @return 订单ID
     */
    @Override
    @Transactional
    public Long createOrder(OrderCreateCmd cmd) {
        Order order = orderCreateExecutor.execute(cmd);
        return order.getOrderId();
    }

    /**
     * 支付订单
     *
     * @param cmd 订单支付命令
     * @return 支付参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> payOrder(OrderPayCmd cmd) {

        Order order = this.orderGateway.queryById(cmd.getOrderId());

        Map<String, Object> paymentInfo = huiFuPaymentService.createOrder(
            new HuiFuPaymentRequest(
                LoginHelper.getTenantId(),
                order.getMerchantId(),
                cmd.getPaymentMethod(),
                order.getPayableAmount().toString(),
                order.getOrderId().toString(),
                cmd.getSub_appid(),
                cmd.getSub_openid(),
                order.getOrderItems().stream().map(OrderItem::toCommodityDetail).toList()
            )
        );

        if (!cmd.getPaymentMethod().equals(order.getPaymentMethod())) {
            order.setPaymentMethod(cmd.getPaymentMethod());
        }

        order.setPaymentOrderNo((String) paymentInfo.get("party_order_id"));
        this.orderGateway.save(order);

        return paymentInfo;
    }

    /**
     * 处理支付成功
     *
     * @param paymentCallback 支付成功回调处理
     */
    @Override
    public void paymentCallBack(JSONObject paymentCallback) {
        String payOrderId = (String) paymentCallback.get("party_order_id");
        String transStat = (String) paymentCallback.get("trans_stat");
        String transAmt = (String) paymentCallback.get("trans_amt");

        if (transStat.equalsIgnoreCase("S")) {
            this.orderPayExecutor.execute(new PaymentSucceedCallbackCmd(payOrderId, transAmt));
        }
    }

    /**
     * 取消订单
     *
     * @param cmd 订单取消命令
     * @return 是否取消成功
     */
    @Override
    @Transactional
    public boolean cancelOrder(OrderCancelCmd cmd) {
        return orderDomainService.cancelOrder(cmd.getOrderId(), cmd.getCancelReason());
    }

    /**
     * 订单发货
     *
     * @param orderId        订单ID
     * @param expressCompany 快递公司
     * @param expressNo      快递单号
     * @return 是否发货成功
     */
    @Override
    @Transactional
    public boolean deliverOrder(Long orderId, String expressCompany, String expressNo) {
        return this.orderDomainService.deliverOrder(orderId, expressCompany, expressNo);
    }

    /**
     * 订单确认收货
     *
     * @param orderId 订单ID
     * @return 是否确认收货成功
     */
    @Override
    @Transactional
    public boolean confirmReceipt(Long orderId) {
        return this.orderDomainService.confirmReceipt(orderId);
    }

    /**
     * 订单完成
     *
     * @param orderId 订单ID
     * @return 是否完成订单成功
     */
    @Override
    @Transactional
    public boolean completeOrder(Long orderId) {
        return this.orderDomainService.completeOrder(orderId);
    }

    /**
     * 申请订单退款
     *
     * @param orderId      订单ID
     * @param refundReason 退款原因
     * @return 是否申请退款成功
     */
    @Override
    @Transactional
    public boolean applyRefund(Long orderId, String refundReason) {
        return this.orderRefundExecutor.applyRefund(orderId, refundReason);
    }

    /**
     * 审批订单退款
     *
     * @param orderId       订单ID
     * @param refundOrderNo 退款订单号
     * @return 是否审批退款成功
     */
    @Override
    @Transactional
    public boolean approveRefund(Long orderId, String refundOrderNo) {
        return this.orderRefundExecutor.approveRefund(orderId, refundOrderNo);
    }

    /**
     * 拒绝订单退款
     *
     * @param orderId      订单ID
     * @param rejectReason 拒绝原因
     * @return 是否拒绝退款成功
     */
    @Override
    @Transactional
    public boolean rejectRefund(Long orderId, String rejectReason) {
        return this.orderRefundExecutor.rejectRefund(orderId, rejectReason);
    }
}
