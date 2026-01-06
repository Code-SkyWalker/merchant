package org.dromara.merchant.app.order;

import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderPayCmd;
import org.dromara.merchant.client.order.dto.data.command.query.OrderQry;

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
     * 根据ID查询订单
     * @param orderId 订单ID
     * @return 订单客户端对象
     */
    OrderCO queryById(Long orderId);

    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单客户端对象
     */
    OrderCO queryByOrderNo(String orderNo);

    /**
     * 查询订单
     * @param qry 订单查询命令
     * @return 订单客户端对象
     */
    OrderCO queryOrder(OrderQry qry);
}