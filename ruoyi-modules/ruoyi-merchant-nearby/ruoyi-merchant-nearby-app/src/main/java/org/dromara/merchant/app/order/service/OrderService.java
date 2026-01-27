package org.dromara.merchant.app.order.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.order.IOrderService;
import org.dromara.merchant.app.order.executor.OrderCreateExecutor;
import org.dromara.merchant.app.order.executor.OrderPayExecutor;
import org.dromara.merchant.app.order.executor.OrderRefundExecutor;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.domain.order.domainservice.IOrderDomainService;
import org.springframework.stereotype.Service;

/**
 * @Description 订单应用服务实现
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderPayExecutor orderPayExecutor;
    private final OrderRefundExecutor orderRefundExecutor;
    private final OrderCreateExecutor orderCreateExecutor;

    private final IOrderDomainService orderDomainService;

    /**
     * 创建订单
     *
     * @param cmd 订单创建命令
     * @return 订单ID
     */
    @Override
    public Long createOrder(OrderCreateCmd cmd) {
        return orderCreateExecutor.execute(cmd);
    }

    /**
     * 支付订单
     *
     * @param cmd 订单支付命令
     * @return 是否支付成功
     */
    @Override
    public boolean payOrder(OrderPayCmd cmd) {
        return orderPayExecutor.execute(cmd);
    }

    /**
     * 取消订单
     *
     * @param cmd 订单取消命令
     * @return 是否取消成功
     */
    @Override
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
    public boolean completeOrder(Long orderId) {
        return this.orderDomainService.completeOrder(orderId);
    }

}
