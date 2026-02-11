package org.dromara.merchant.app.cert;

import org.dromara.merchant.client.cert.co.CertCartSummaryCO;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;
import org.dromara.merchant.domain.cert.entity.CertCartItem;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;

import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/2/11 09:17
 */
public interface ICertCartService {

    /**
     * 添加商品到认证购物车
     *
     * @param cartItem 购物车项
     * @return 是否添加成功
     */
    boolean addToCertCart(CertCartItem cartItem);

    /**
     * 从认证购物车移除商品
     *
     * @param userId     用户ID
     * @param productIds 商品ID集合
     * @return 是否移除成功
     */
    boolean removeFromCertCart(Long userId, Set<Long> productIds);

    /**
     * 获取认证购物车所有商品
     *
     * @param userId 用户ID
     * @return 购物车项列表
     */
    List<CertCartItem> getCertCartItems(Long userId);

    /**
     * 获取认证购物车分组汇总信息
     *
     * @param userId 用户ID
     * @return 购物车分组汇总CO
     */
    CertCartSummaryCO getCertCartSummary(Long userId);

    /**
     * 更新认证购物车项数量
     *
     * @param add       是否是添加商品数量，反之减少
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 是否更新成功
     */
    boolean updateCertCartItemQuantity(boolean add, Long userId, Long productId);

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
    int certCartItemCount(Long userId);

    /**
     * 获取认证购物车指定商品总金额
     *
     * @param cmd 认证购物车商品金额计算命令
     * @return 总金额
     */
    CalculationResult specifiedProductTotalAmount(PriceCalculationCmd cmd);
}
