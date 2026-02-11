package org.dromara.merchant.infrastructure.cert.convertor;

import org.dromara.merchant.client.cert.co.*;
import org.dromara.merchant.domain.cert.entity.CertCartItem;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 购物车转换器
 * 处理DO到CO的转换
 *
 * @author Code Skywalker
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartConvertor {

    /**
     * 将购物车项列表转换为分组汇总CO
     *
     * @param cartItems    购物车项列表
     * @param skus         商品信息列表
     * @param merchantMap  商家信息映射
     * @param marketingMap 活动信息映射
     * @param couponMap    优惠券信息映射
     * @return 购物车分组汇总CO
     */
    default CertCartSummaryCO toCertCartSummaryCO(List<CertCartItem> cartItems,
                                                  List<Sku> skus,
                                                  Map<Long, Merchant> merchantMap,
                                                  Map<Long, List<Marketing>> marketingMap,
                                                  Map<Long, List<Coupon>> couponMap) {
        if (cartItems == null || cartItems.isEmpty() || skus == null || skus.isEmpty()) {
            return new CertCartSummaryCO();
        }

        // 按商家和活动分组
        Map<String, List<CertCartItem>> groupedItems = cartItems.stream()
            .collect(Collectors.groupingBy(item ->
                item.getMerchantId() + "_" + getMarketingKey(item)));

        // 转换为分组CO
        List<CertCartGroupCO> groups = groupedItems.values().stream()
            .map(certCartItems -> toCertCartGroupCO(certCartItems, skus, merchantMap, marketingMap, couponMap))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        // 创建汇总CO
        CertCartSummaryCO summary = new CertCartSummaryCO();
        summary.setGroups(groups);
        summary.setMerchantCount((int) groups.stream()
            .map(group -> group.getMerchantInfo().getMerchantId())
            .distinct()
            .count());

        return summary;
    }

    /**
     * 将购物车项列表转换为分组CO
     *
     * @param items        购物车项列表
     * @param skus         商品信息列表
     * @param merchantMap  商家信息映射
     * @param marketingMap 活动信息映射
     * @param couponMap    优惠券信息映射
     * @return 购物车分组CO
     */
    default CertCartGroupCO toCertCartGroupCO(List<CertCartItem> items,
                                              List<Sku> skus,
                                              Map<Long, Merchant> merchantMap,
                                              Map<Long, List<Marketing>> marketingMap,
                                              Map<Long, List<Coupon>> couponMap) {
        if (items == null || items.isEmpty() || skus == null || skus.isEmpty()) {
            return null;
        }

        // 创建Sku映射以便快速查找
        Map<Long, Sku> skuMap = skus.stream()
            .collect(Collectors.toMap(Sku::getId, sku -> sku));

        CertCartGroupCO group = new CertCartGroupCO();

        // 获取第一个项作为代表来确定商家和活动
        CertCartItem firstItem = items.get(0);

        // 设置商家信息
        Merchant merchant = merchantMap.get(firstItem.getMerchantId());
        if (merchant != null) {
            group.setMerchantInfo(toMerchantInfoCO(merchant));
        }

        // 设置商品列表
        List<ProductInfoCO> products = items.stream()
            .map(item -> {
                Sku sku = skuMap.get(item.getProductId());
                if (sku == null) return null;

                ProductInfoCO productInfoCO = toProductInfoCO(item, sku);
                if (productInfoCO != null) {
                    productInfoCO.setMarketings(toActivityInfoCOList(marketingMap.get(item.getProductId())));
                    productInfoCO.setCoupons(toCouponInfoCOList(couponMap.get(item.getProductId())));
                }
                return productInfoCO;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        group.setProducts(products);

        return group;
    }

    /**
     * 转换商家信息到CO
     */
    @Mapping(source = "merchantId", target = "merchantId")
    @Mapping(source = "merchantName", target = "merchantName")
    @Mapping(source = "logo", target = "logo")
    @Mapping(source = "description", target = "description")
    MerchantInfoCO toMerchantInfoCO(Merchant merchant);

    /**
     * 转换活动信息到CO
     */
    @Mapping(source = "id", target = "activityId")
    @Mapping(source = "name", target = "activityName")
    @Mapping(source = "type", target = "activityType")
    @Mapping(source = "receiveBegin", target = "startTime")
    @Mapping(source = "receiveEnd", target = "endTime")
    List<ActivityInfoCO> toActivityInfoCOList(List<Marketing> marketing);

    /**
     * 转换活动信息到CO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "couponType")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "serviceBegin", target = "startTime")
    @Mapping(source = "serviceEnd", target = "endTime")
    List<CouponInfoCO> toCouponInfoCOList(List<Coupon> coupons);

    /**
     * 转换商品信息到CO
     * 从数据库加载的商品信息中获取详细数据
     */
    default ProductInfoCO toProductInfoCO(CertCartItem item, Sku sku) {
        if (item == null || sku == null) {
            return null;
        }

        ProductInfoCO productInfoCO = new ProductInfoCO();
        productInfoCO.setSpuId(sku.getSpuId());
        productInfoCO.setProductId(item.getProductId());
        productInfoCO.setProductName(sku.getName());  // 从Sku获取商品名称
        productInfoCO.setMainImage(item.getMainImage());   // 从Sku获取主图
        productInfoCO.setSpecifications(sku.getSpec()); // 从Sku获取规格
        productInfoCO.setPrice(sku.getPrice());       // 从Sku获取价格
        productInfoCO.setQuantity(item.getQuantity()); // 保持购物车中的数量

        return productInfoCO;
    }

    /**
     * 获取活动键值用于分组
     */
    default String getMarketingKey(CertCartItem item) {
        if (item.getMarketings() == null || item.getMarketings().isEmpty()) {
            return "no_activity";
        }
        return item.getMarketings().stream()
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.joining(","));
    }
}
