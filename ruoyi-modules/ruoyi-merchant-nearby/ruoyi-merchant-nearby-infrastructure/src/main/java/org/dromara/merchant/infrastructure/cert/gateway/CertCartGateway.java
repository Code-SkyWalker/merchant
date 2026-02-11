package org.dromara.merchant.infrastructure.cert.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.merchant.domain.cert.entity.CertCartItem;
import org.dromara.merchant.domain.cert.gateway.ICertCartGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证购物车网关Redis实现
 *
 * @author Lion Li
 */
@Component
@RequiredArgsConstructor
public class CertCartGateway implements ICertCartGateway {

    public static final String CERT_CART_REDIS_KEY = "cert:cart:";

    @Override
    public boolean addIntoCertCart(CertCartItem cartItem) {
        String key = CERT_CART_REDIS_KEY + LoginHelper.getUserId();
        String field = cartItem.getProductId().toString();

        // 存储购物车项
        RedisUtils.setCacheMapValue(key, field, cartItem);

        return true;
    }

    @Override
    public boolean removeFromCertCart(Long userId, Set<Long> productIds) {
        String key = CERT_CART_REDIS_KEY + userId;

        // 将Long类型的productId转换为String类型的field
        Set<String> fieldsToRemove = productIds.stream()
                .map(String::valueOf)
                .collect(java.util.stream.Collectors.toSet());

        // 批量删除购物车项
        RedisUtils.delMultiCacheMapValue(key, fieldsToRemove);

        return true;
    }

    @Override
    public List<CertCartItem> getCertCartItemsByUserId(Long userId) {
        String key = CERT_CART_REDIS_KEY + userId;
        return RedisUtils.getCacheMap(key).values().stream()
                .map(item -> (CertCartItem) item)
                .collect(Collectors.toList());
    }

    @Override
    public CertCartItem getCertCartItem(Long userId, Long productId) {
        String key = CERT_CART_REDIS_KEY + userId;
        String field = productId.toString();
        return RedisUtils.getCacheMapValue(key, field);
    }

    @Override
    public boolean increaseCertCartItemQuantity(Long userId, Long productId) {
        String key = CERT_CART_REDIS_KEY + userId;
        String field = productId.toString();

        CertCartItem cartItem = RedisUtils.getCacheMapValue(key, field);
        if (cartItem != null) {
            cartItem.increaseQuantity();
            RedisUtils.setCacheMapValue(key, field, cartItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean decreaseCertCartItemQuantity(Long userId, Long productId) {
        String key = CERT_CART_REDIS_KEY + userId;
        String field = productId.toString();

        CertCartItem cartItem = RedisUtils.getCacheMapValue(key, field);
        if (cartItem != null) {
            cartItem.decreaseQuantity();
            RedisUtils.setCacheMapValue(key, field, cartItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean clearCertCart(Long userId) {
        String key = CERT_CART_REDIS_KEY + userId;
        return RedisUtils.deleteObject(key);
    }

    @Override
    public int getCertCartItemCount(Long userId) {
        String key = CERT_CART_REDIS_KEY + userId;
        return RedisUtils.getCacheMap(key).size();
    }

}
