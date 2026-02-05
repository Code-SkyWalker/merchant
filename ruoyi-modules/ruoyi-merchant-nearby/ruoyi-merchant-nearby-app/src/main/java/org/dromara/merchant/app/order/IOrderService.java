package org.dromara.merchant.app.order;

import org.dromara.merchant.client.order.dto.data.command.*;

import java.util.Map;

/**
 * @Description 订单应用服务接口
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param cmd 订单创建命令
     * @return 订单ID
     */
    Long createOrder(OrderCreateCmd cmd);

    /**
     * 支付订单
     * @param cmd 订单支付命令
     * @return 是否支付成功
     */
    Map<String, Object> payOrder(OrderPayCmd cmd);

    /**
     * 取消订单
     * @param cmd 订单取消命令
     * @return 是否取消成功
     */
    boolean cancelOrder(OrderCancelCmd cmd);

    /**
     * 发货订单
     * @param orderId 订单ID
     * @param expressCompany 快递公司
     * @param expressNo 快递单号
     * @return 是否发货成功
     */
    boolean deliverOrder(Long orderId, String expressCompany, String expressNo);

    /**
     * 确认收货订单
     * @param orderId 订单ID
     * @return 是否确认收货成功
     */
    boolean confirmReceipt(Long orderId);

    /**
     * 完成订单
     * @param orderId 订单ID
     * @return 是否完成订单成功
     */
    boolean completeOrder(Long orderId);

    /**
     * 申请订单退款
     * @param orderId 订单ID
     * @param refundReason 退款原因
     * @return 是否申请退款成功
     */
    boolean applyRefund(Long orderId, String refundReason);

    /**
     * 审批订单退款
     * @param orderId 订单ID
     * @param refundOrderNo 退款订单号
     * @return 是否审批退款成功
     */
    boolean approveRefund(Long orderId, String refundOrderNo);

    /**
     * 拒绝订单退款
     * @param orderId 订单ID
     * @param rejectReason 拒绝原因
     * @return 是否拒绝退款成功
     */
    boolean rejectRefund(Long orderId, String rejectReason);
}
