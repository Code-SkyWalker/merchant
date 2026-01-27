package org.dromara.merchant.app.freight.service;

import com.kuaidi100.sdk.request.bsamecity.BsamecityOrderReq;
import com.kuaidi100.sdk.request.bsamecity.Goods;
import lombok.RequiredArgsConstructor;
import org.dromara.express.app.IConfigService;
import org.dromara.merchant.app.freight.IExpressOrderService;
import org.dromara.express.client.api.order.CityDeliveryOrder;
import org.dromara.express.domain.model.Config;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.domain.address.model.Address;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.text.StrPool.COMMA;

/**
 * @Description 快递100订单服务
 * @Author Code Skywalker
 * @Date 2026/1/22 14:02
 */
@Component
@RequiredArgsConstructor
public class CityDeliveryExpressOrderService implements IExpressOrderService {

    private final IConfigService configService;
    private final ISkuService skuService;

    private final CityDeliveryOrder cityDeliveryOrder;

    /**
     * 查询配送费
     */
    @Override
    public BigDecimal queryDeliveryFee(List<Product> products, Merchant merchant, @NotNull Address customAddress) {


        Config config = this.configService.queryConfigById(merchant.getMerchantId());
        if (config == null) return null;

        // 获取商品sku和数量的映射关系
        Map<Long, Integer> skuAndQuantityPair = products.stream().collect(Collectors.toMap(Product::getSkuId, Product::getQuantity));

        List<Sku> skus = this.skuService.queryBySkuIds(skuAndQuantityPair.keySet());
        // 计算商品总重量
        BigDecimal totalWeight = skus.stream().map(Sku::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算商品总价值
        BigDecimal totalPrice = skus.stream()
            .map(sku -> sku.getPrice().multiply(BigDecimal.valueOf(skuAndQuantityPair.get(sku.getId()))))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 同城配送
        BsamecityOrderReq params = new BsamecityOrderReq();

        // 发件人信息
        params.setSendManName(merchant.getLegalPerson());
        params.setSendManMobile(merchant.getContactPhone());
        params.setSendManProvince(customAddress.getProvince());
        params.setSendManCity(customAddress.getCity());
        params.setSendManDistrict(customAddress.getDistrict());
        params.setSendManAddr(customAddress.getAddress() + merchant.getAddress());
        params.setSendManLat(merchant.getPreciseLocation().split(COMMA)[0]);
        params.setSendManLng(merchant.getPreciseLocation().split(COMMA)[1]);

        // 收件人信息
        params.setRecManName(customAddress.getReceiverName());
        params.setRecManMobile(customAddress.getReceiverPhone());
        params.setRecManProvince(customAddress.getProvince());
        params.setRecManCity(customAddress.getCity());
        params.setRecManDistrict(customAddress.getDistrict());
        params.setRecManAddr(customAddress.getDetailAddress());
        params.setRecManLat(customAddress.getLatitude());
        params.setRecManLng(customAddress.getLongitude());

        // 商品信息
        params.setWeight(totalWeight.toString());
        params.setPrice(totalPrice.toString());

        // 同城设置成 闪送同城 TODO: 后续动态更改
        params.setKuaidicom("shansongtongcheng");

        List<Goods> goods = skus.stream()
            .map(sku -> new Goods(sku.getName(), "食品", skuAndQuantityPair.get(sku.getId())))
            .toList();

        params.setGoods(goods);

        try {
            return cityDeliveryOrder.queryPrice(params, config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 创建订单
     */
    @Override
    public void createOrder() {

    }

    /**
     * 查询订单
     */
    @Override
    public void queryOrder() {

    }

    /**
     * 更新订单
     */
    @Override
    public void updateOrder() {

    }
}
