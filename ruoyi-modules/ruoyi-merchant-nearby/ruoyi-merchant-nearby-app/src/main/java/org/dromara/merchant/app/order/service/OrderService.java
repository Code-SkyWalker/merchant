package org.dromara.merchant.app.order.service;

import cn.hutool.json.JSONObject;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import lombok.RequiredArgsConstructor;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.huifu.app.config.service.IHuifuConfigService;
import org.dromara.huifu.app.payment.executor.HuiFuPaymentRequest;
import org.dromara.huifu.app.payment.service.HuiFuPaymentService;
import org.dromara.huifu.app.payment.service.IHuifuCallbackHandler;
import org.dromara.huifu.client.api.pay.AggregatePayment;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.app.order.executor.OrderCreateExecutor;
import org.dromara.merchant.app.order.executor.OrderPayExecutor;
import org.dromara.merchant.app.order.executor.OrderRefundExecutor;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.client.order.dto.data.command.PaymentSucceedCallbackCmd;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.dromara.merchant.domain.order.gateway.IOrderGateway;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

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

    private final IHuifuConfigService huifuConfigService;

    private final AggregatePayment aggregatePayment;

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

        Map<String, Object> paymentInfo = huiFuPaymentService.createOrder(new HuiFuPaymentRequest(LoginHelper.getTenantId(), order.getMerchantId(), cmd.getPaymentMethod(), order.getPayableAmount().toString(), order.getOrderId().toString(), cmd.getSub_appid(), cmd.getSub_openid(), order.getOrderItems().stream().map(OrderItem::toCommodityDetail).toList()));

        if (!cmd.getPaymentMethod().equals(order.getPaymentMethod())) {
            order.setPaymentMethod(cmd.getPaymentMethod());
        }

        order.setPaymentOrderNo((String) paymentInfo.get("req_seq_id")); // 保存交易流水号，数据库字段是payment_order_no
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
        String payOrderId = (String) paymentCallback.get("req_seq_id");
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
    public boolean cancelOrder(OrderCancelCmd cmd) throws BasePayException, IllegalAccessException {
        Order order = this.orderGateway.queryById(cmd.getOrderId());
        if (order == null) return false;

        OrderStatus currentOrderStatus = order.getStatus();

        boolean refundSucceed = orderDomainService.cancelOrder(order, cmd.getCancelReason());

        // 退款失败，返回失败
        if (!refundSucceed) return false;

        // 待支付订单取消，直接返回成功
        if (currentOrderStatus.equals(OrderStatus.PENDING_PAYMENT)) return true;

        // 待发货退款，直接调用聚合支付的退款接口
        HuifuConfig huifuConfig = this.huifuConfigService.queryByMerchantId(order.getMerchantId());
        if (huifuConfig == null) throw new BasePayException("商户支付未配置");

        // 调用聚合支付的延迟确认接口
        Map<String, Object> confirmResult = this.aggregatePayment.refund(
            huifuConfig.getHuifuId(), order.getPaymentOrderNo(), order.getPaymentTime(), order.getPayableAmount());

        if (confirmResult == null) return false;

        // 判断确认结果
        if (confirmResult.get("trans_stat").equals("S")) {
            order.setRefundOrderNo((String) confirmResult.get("req_seq_id"));
        } else {
            throw new BasePayException((String) confirmResult.get("resp_desc"));
        }
        return true;
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
    public boolean confirmReceipt(Long orderId) throws BasePayException, IllegalAccessException {
        Order order = this.orderGateway.queryById(orderId);
        if (order == null) return false;

        boolean complete = this.orderDomainService.confirmReceipt(order);

        if (!complete) return false;

        HuifuConfig huifuConfig = this.huifuConfigService.queryByMerchantId(order.getMerchantId());
        if (huifuConfig == null) return false;

        // 调用聚合支付的延迟确认接口
        Map<String, Object> confirmResult = this.aggregatePayment.delayTransConfirm(
            huifuConfig.getHuifuId(), order.getPaymentOrderNo(), order.getPaymentTime());

        if (confirmResult == null) return false;

        // 判断确认结果
        if (confirmResult.get("trans_stat").equals("S")) {
            order.setSplitConfirmNo((String) confirmResult.get("req_seq_id"));
            order.setSplitTime(LocalDateTime.now());
        } else {
            throw new BasePayException((String) confirmResult.get("resp_desc"));
        }

        return complete;
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
     * @param orderId 订单ID
     * @return 是否审批退款成功
     */
    @Override
    @Transactional
    public boolean approveRefund(Long orderId) throws BasePayException, IllegalAccessException {
        Order order = this.orderGateway.queryById(orderId);
        if (order == null) return false;


        HuifuConfig huifuConfig = this.huifuConfigService.queryByMerchantId(order.getMerchantId());
        if (huifuConfig == null) return false;

        if (order.getSplitConfirmNo() != null) {
            // 调用聚合支付的延迟分账退款确认接口
            Map<String, Object> confirmResult = this.aggregatePayment.delayTransConfirmRefund(
                huifuConfig.getHuifuId(), order.getSplitConfirmNo(), order.getSplitTime());
            if (confirmResult == null) return false;

            if (confirmResult.get("trans_stat").equals("F")) {
                throw new BasePayException((String) confirmResult.get("resp_desc"));
            }
        }

        // 调用聚合支付的退款接口
        Map<String, Object> confirmResult = this.aggregatePayment.refund(
            huifuConfig.getHuifuId(), order.getPaymentOrderNo(), order.getPaymentTime(), order.getPayableAmount());

        if (confirmResult == null) return false;

        // 判断确认结果
        if (confirmResult.get("trans_stat").equals("S")) {
            order.setRefundOrderNo((String) confirmResult.get("org_hf_seq_id"));
            order.setRefundTime(LocalDateTime.now());
            order.setRefundAmount(new BigDecimal((String) confirmResult.get("ord_amt")));
        } else {
            throw new BasePayException((String) confirmResult.get("resp_desc"));
        }

        return this.orderRefundExecutor.approveRefund(orderId);
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
