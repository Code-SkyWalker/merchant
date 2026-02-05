package org.dromara.merchant.app.marketing.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.address.IAddressService;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.freight.IFreightConfigService;
import org.dromara.merchant.app.freight.service.BusinessExpressOrderService;
import org.dromara.merchant.app.freight.service.CityDeliveryExpressOrderService;
import org.dromara.merchant.app.marketing.ICouponService;
import org.dromara.merchant.app.marketing.IMarketingService;
import org.dromara.merchant.app.marketing.IPricingCalculateService;
import org.dromara.merchant.app.merchant.IMerchantService;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;
import org.dromara.merchant.domain.address.model.Address;
import org.dromara.merchant.domain.freight.model.DeliveryConfigExpress;
import org.dromara.merchant.domain.freight.model.DeliveryConfigLocal;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.domain.freight.model.FreightConfig;
import org.dromara.merchant.domain.marketing.discount.CalculationResult;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.domain.marketing.service.PricingEngine;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 价格计算服务实现类，使用PricingEngine进行价格计算
 * @Author Code Skywalker
 * @Date 2026/1/16 13:35
 */
@Component
@RequiredArgsConstructor
public class PricingCalculateService implements IPricingCalculateService {


    // 优惠券服务
    private final ICouponService couponService;
    // 营销服务
    private final IMarketingService marketingService;
    // 商品服务
    private final ISkuService skuService;
    // 地址服务
    private final IAddressService addressService;
    // 商家服务
    private final IMerchantService merchantService;
    // 配送费配置服务
    private final IFreightConfigService freightConfigService;
    // 快递配送服务 计算价格
    private final BusinessExpressOrderService expressOrderService;
    // 同城配送服务 计算价格
    private final CityDeliveryExpressOrderService cityDeliveryExpressOrderService;


    /**
     * 计算配送费
     *
     * @param products       商品列表
     * @param merchantId     商家id
     * @param addressId      地址id
     * @param subtotal       支付价小计, 用于判断是否包邮
     * @param deliveryMethod 配送方式
     * @return 配送费
     */
    @Override
    public BigDecimal calculateExpressFee(List<Product> products, Long merchantId, Long addressId, BigDecimal subtotal, String deliveryMethod) {
        if (addressId == null) return BigDecimal.ZERO;
        Address address = this.addressService.queryById(addressId);

        if (merchantId == null) return BigDecimal.ZERO;
        Merchant merchant = this.merchantService.queryById(merchantId);

        FreightConfig config = freightConfigService.queryByMerchantIdAndDeliveryMethod(merchantId, DeliveryMethod.valueOf(deliveryMethod));
        if (config == null) return BigDecimal.ZERO;

        // 使用同城配送服务计算价格
        if (config.getDeliveryConfig() instanceof DeliveryConfigLocal configLocal) {
            DeliveryConfigLocal.LocalDeliveryFeeConfig configDetails = configLocal.getConfig();
            if (configDetails.getFreeShipping() && configDetails.getFreeShippingAmount().compareTo(subtotal) <= 0) {
                return BigDecimal.ZERO;
            }
            return cityDeliveryExpressOrderService.queryDeliveryFee(products, merchant, address);
        }
        // 使用快递配送服务计算价格
        else if (config.getDeliveryConfig() instanceof DeliveryConfigExpress configExpress) {
            if (configExpress.getFreeShipping() && configExpress.getFreeShippingAmount().compareTo(subtotal) <= 0) {
                return BigDecimal.ZERO;
            }
            return expressOrderService.queryDeliveryFee(products, merchant, address);
        }

        // 不是同城配送且不是快递配送的（自提或无需配送），则返回0
        return BigDecimal.ZERO;
    }

    /**
     * 计算商品最终价格
     *
     * @param cmd 计算价格命令
     * @return 计算结果
     */
    @Override
    public CalculationResult calculateFinalPrice(PriceCalculationCmd cmd) {

        PricingEngine pricingEngine = new PricingEngine();

        // 设置优惠券、营销活动、社区优惠、积分优惠、商品优惠
        pricingEngine.setCoupons(queryCoupons(cmd.getCoupons()));
        List<Marketing> marketings = queryMarketing(cmd.getMarketings());
        pricingEngine.setMarketings(marketings);
        pricingEngine.setCommDiscountAmount(cmd.getCommDiscountAmount());
        pricingEngine.setIntegralDiscountAmount(cmd.getIntegralDiscountAmount());

        List<Product> products = queryProducts(cmd.getProducts());

        CalculationResult result = pricingEngine.calculate(products);

        BigDecimal shippingFee = calculateExpressFee(products, cmd.getMerchantId(), cmd.getAddressId(), result.getDiscountedSubtotal(), cmd.getDeliveryMethod());

        return result.setShippingFee(shippingFee);
    }

    /**
     * 查询优惠券
     *
     * @param couponIds 优惠券ID列表
     * @return 优惠券列表
     */
    private List<Coupon> queryCoupons(List<Long> couponIds) {
        return this.couponService.queryByIds(couponIds);
    }

    /**
     * 查询营销活动
     *
     * @param marketingIds 营销活动ID列表
     * @return 营销活动列表
     */
    private List<Marketing> queryMarketing(List<Long> marketingIds) {
        return this.marketingService.queryByIds(marketingIds);
    }

    /**
     * 查询商品
     *
     * @param productCmd 商品列表
     * @return 商品列表
     */
    private List<Product> queryProducts(List<PriceCalculationCmd.Product> productCmd) {
        Map<Long, Integer> cmdMap = productCmd.stream().collect(Collectors.toMap(PriceCalculationCmd.Product::getSkuId, PriceCalculationCmd.Product::getQuantity));
        return skuService.queryBySkuIds(cmdMap.keySet()).stream()
            .map(sku -> new Product(sku.getSpuId(), sku.getId(), sku.getPrice(), cmdMap.get(sku.getId())))
            .toList();
    }

}
