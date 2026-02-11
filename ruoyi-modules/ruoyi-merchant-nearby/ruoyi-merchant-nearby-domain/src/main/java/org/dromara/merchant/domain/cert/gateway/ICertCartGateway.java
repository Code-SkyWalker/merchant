package org.dromara.merchant.domain.cert.gateway;

import org.dromara.merchant.domain.cert.entity.CertCartItem;

import java.util.List;
import java.util.Set;

/**
 * 认证购物车网关接口
 *
 * @author Lion Li
 */
public interface ICertCartGateway {

    /**
     * 添加商品到认证购物车
     *
     * @param cartItem 购物车项
     * @return 是否添加成功
     */
    boolean addIntoCertCart(CertCartItem cartItem);

    /**
     * 移除认证购物车指定商品
     *
     * @param userId      用户ID
     * @param productIds  商品ID集合
     * @return 是否移除成功
     */
    boolean removeFromCertCart(Long userId, Set<Long> productIds);

    /**
     * 根据用户ID获取认证购物车所有商品
     *
     * @param userId 用户ID
     * @return 购物车项列表
     */
    List<CertCartItem> getCertCartItemsByUserId(Long userId);

    /**
     * 根据用户ID和商品ID获取认证购物车项
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 购物车项
     */
    CertCartItem getCertCartItem(Long userId, Long productId);

    /**
     * 增加认证购物车项数量
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 是否更新成功
     */
    boolean increaseCertCartItemQuantity(Long userId, Long productId);

    /**
     * 减少认证购物车项数量
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 是否更新成功
     */
    boolean decreaseCertCartItemQuantity(Long userId, Long productId);

    /**
     * 清空认证购物车
     *
     * @param userId 用户ID
     * @return 是否清空成功
     */
    boolean clearCertCart(Long userId);

    /**
     * 获取认证购物车商品总数
     *
     * @param userId 用户ID
     * @return 商品总数
     */
    int getCertCartItemCount(Long userId);

}
