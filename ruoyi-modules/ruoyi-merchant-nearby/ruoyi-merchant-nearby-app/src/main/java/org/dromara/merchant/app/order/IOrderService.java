package org.dromara.merchant.app.order;

import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;

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
    boolean payOrder(OrderPayCmd cmd);

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

}
