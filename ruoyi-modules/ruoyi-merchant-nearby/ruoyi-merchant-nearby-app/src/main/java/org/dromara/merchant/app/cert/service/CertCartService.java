package org.dromara.merchant.app.cert.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.cert.ICertCartService;
import org.dromara.merchant.app.marketing.IPricingCalculateService;
import org.dromara.merchant.client.cert.co.CertCartSummaryCO;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;
import org.dromara.merchant.domain.cert.entity.CertCartItem;
import org.dromara.merchant.domain.cert.gateway.ICertCartGateway;
import org.dromara.merchant.domain.commodity.gateway.ISkuGateway;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.dromara.merchant.infrastructure.cert.convertor.CartConvertor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 认证购物车服务
 *
 * @author Lion Li
 */
@Service
@RequiredArgsConstructor
public class CertCartService implements ICertCartService {

    private final ICertCartGateway certCartGateway;
    private final IPricingCalculateService pricingCalculateService;
    private final IMerchantGateway merchantGateway;

    private final CartConvertor cartConvertor;

    private final IMarketingGateway marketingGateway;
    private final ICouponGateway couponGateway;
    private final ISkuGateway skuGateway;

    /**
     * 添加商品到认证购物车
     *
     * @param cartItem 购物车项
     * @return 是否添加成功
     */
    public boolean addToCertCart(CertCartItem cartItem) {
        return certCartGateway.addIntoCertCart(cartItem);
    }

    /**
     * 从认证购物车移除商品
     *
     * @param userId     用户ID
     * @param productIds 商品ID集合
     * @return 是否移除成功
     */
    public boolean removeFromCertCart(Long userId, Set<Long> productIds) {
        return certCartGateway.removeFromCertCart(userId, productIds);
    }

    /**
     * 获取认证购物车所有商品
     *
     * @param userId 用户ID
     * @return 购物车项列表
     */
    public List<CertCartItem> getCertCartItems(Long userId) {
        return certCartGateway.getCertCartItemsByUserId(userId);
    }

    /**
     * 更新认证购物车项数量
     *
     * @param add       是否是添加商品数量，反之减少
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 是否更新成功
     */
    public boolean updateCertCartItemQuantity(boolean add, Long userId, Long productId) {
        if (add) {
            return certCartGateway.increaseCertCartItemQuantity(userId, productId);
        } else {
            return certCartGateway.decreaseCertCartItemQuantity(userId, productId);
        }
    }

    /**
     * 清空认证购物车
     *
     * @param userId 用户ID
     * @return 是否清空成功
     */
    public boolean clearCertCart(Long userId) {
        return certCartGateway.clearCertCart(userId);
    }

    /**
     * 获取认证购物车商品总数
     *
     * @param userId 用户ID
     * @return 商品总数
     */
    public int certCartItemCount(Long userId) {
        return certCartGateway.getCertCartItemCount(userId);
    }

    /**
     * 获取认证购物车总金额
     *
     * @return 总金额
     */
    public CalculationResult specifiedProductTotalAmount(PriceCalculationCmd cmd) {
        return this.pricingCalculateService.calculateFinalPrice(cmd);
    }

    /**
     * 获取认证购物车分组汇总信息
     *
     * @param userId 用户ID
     * @return 购物车分组汇总CO
     */
    @Override
    public CertCartSummaryCO getCertCartSummary(Long userId) {
        // 获取购物车项
        List<CertCartItem> cartItems = certCartGateway.getCertCartItemsByUserId(userId);
        if (cartItems == null || cartItems.isEmpty()) {
            return new CertCartSummaryCO();
        }

        // 获取相关的商家信息
        Set<Long> merchantIds = cartItems.stream()
            .map(CertCartItem::getMerchantId)
            .collect(Collectors.toSet());
        List<Merchant> merchants = merchantGateway.queryMerchantsByMerchantIds(merchantIds);
        Map<Long, Merchant> merchantMap = merchants.stream()
            .collect(Collectors.toMap(Merchant::getMerchantId, merchant -> merchant));

        // 获取商品信息
        Set<Long> productIds = cartItems.stream().map(CertCartItem::getProductId).collect(Collectors.toSet());
        List<Sku> products = this.skuGateway.queryBySkuIds(productIds);
        if (products == null || products.isEmpty()) return new CertCartSummaryCO();

        // 获取相关的活动信息
        Map<Long, List<Long>> map = cartItems.stream().collect(Collectors.toMap(CertCartItem::getProductId, CertCartItem::getMarketings));
        Set<Long> activityIds = map.values().stream().flatMap(List::stream).collect(Collectors.toSet());
        List<Marketing> marketings = this.marketingGateway.queryByIds(activityIds);
        Map<Long, List<Marketing>> marketingMap = map.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> marketings.stream().filter(m -> e.getValue().contains(m.getId())).toList()));

        // 获取相关的优惠券信息
        Map<Long, List<Long>> couponIdMap = cartItems.stream().collect(Collectors.toMap(CertCartItem::getProductId, CertCartItem::getCoupons));
        Set<Long> couponIds = couponIdMap.values().stream().flatMap(List::stream).collect(Collectors.toSet());
        List<Coupon> coupons = this.couponGateway.queryByIds(couponIds);
        Map<Long, List<Coupon>> couponMap = couponIdMap.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> coupons.stream().filter(m -> e.getValue().contains(m.getId())).toList()));

        // 转换为分组CO
        return this.cartConvertor.toCertCartSummaryCO(cartItems, products, merchantMap, marketingMap, couponMap);
    }
}
