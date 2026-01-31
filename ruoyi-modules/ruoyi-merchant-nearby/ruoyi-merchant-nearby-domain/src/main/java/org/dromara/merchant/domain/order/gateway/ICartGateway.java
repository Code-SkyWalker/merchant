package org.dromara.merchant.domain.order.gateway;

import org.dromara.merchant.domain.order.model.OrderItem;

import java.util.Set;

/**
 * @Description 购物车网关接口
 * @Author Code Skywalker
 * @Date 2026/1/6 10:35
 */
public interface ICartGateway {

    /**
     * 添加商品到购物车
     *
     * @param orderItem 订单商品
     * @return 是否添加成功
     */
    boolean addIntoCart(OrderItem orderItem);

    /**
     * 移除购物车指定商品
     *
     * @param orderItemIds 订单商品id
     * @return 是否移除成功
     */
    boolean removeFormCart(Set<Long> orderItemIds);

    /**
     * 添加商品数量
     *
     * @param orderItemId 订单商品id
     * @return 是否添加成功
     */
    OrderItem increaseQuantity(Long orderItemId);

    /**
     * 减少商品数量
     *
     * @param orderItemId 订单商品id
     * @return 是否减少成功
     */
    OrderItem decreaseQuantity(Long orderItemId);

    /**
     * 清空购物车
     *
     * @return 是否清空成功
     */
    boolean clearCart();

}
