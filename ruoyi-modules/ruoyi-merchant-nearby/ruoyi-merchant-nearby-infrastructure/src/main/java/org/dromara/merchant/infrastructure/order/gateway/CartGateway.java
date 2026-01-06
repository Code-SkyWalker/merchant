package org.dromara.merchant.infrastructure.order.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.config.RedisConfig;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.merchant.domain.order.gateway.ICartGateway;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 购物车网关实现
 * @Author Code Skywalker
 * @Date 2026/1/6 10:41
 */
@Component
@RequiredArgsConstructor
public class CartGateway implements ICartGateway {

    private final String REDIS_CART_KEY = "cart:";


    @Override
    public boolean addIntoCart(OrderItem orderItem) {
        RedisUtils.setCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItem.getOrderItemId().toString(), orderItem);
        return true;
    }

    @Override
    public boolean removeFormCart(Set<Long> orderItemIds) {
        Set<String> orderItemIdsString = orderItemIds.stream().map(Object::toString).collect(Collectors.toSet());
        RedisUtils.delMultiCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItemIdsString);
        return true;
    }

    @Override
    public OrderItem increaseQuantity(Long orderItemId) {
        OrderItem orderItem = RedisUtils.getCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItemId.toString());
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        RedisUtils.setCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItemId.toString(), orderItem);
        return orderItem;
    }

    @Override
    public OrderItem decreaseQuantity(Long orderItemId) {
        OrderItem orderItem = RedisUtils.getCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItemId.toString());
        orderItem.setQuantity(orderItem.getQuantity() - 1);
        RedisUtils.setCacheMapValue(REDIS_CART_KEY + LoginHelper.getUserId(), orderItemId.toString(), orderItem);
        return orderItem;
    }

    @Override
    public boolean clearCart() {
        return RedisUtils.deleteObject(REDIS_CART_KEY + LoginHelper.getUserId());
    }
}
