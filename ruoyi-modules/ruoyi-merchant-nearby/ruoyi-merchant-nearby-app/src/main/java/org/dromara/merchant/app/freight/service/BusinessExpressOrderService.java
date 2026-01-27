package org.dromara.merchant.app.freight.service;

import com.kuaidi100.sdk.request.BOrderOfficialQueryPriceReq;
import lombok.RequiredArgsConstructor;
import org.dromara.express.app.IConfigService;
import org.dromara.merchant.app.freight.IExpressOrderService;
import org.dromara.express.client.api.order.BusinessOrder;
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

/**
 * @Description 快递100订单服务
 * @Author Code Skywalker
 * @Date 2026/1/22 14:02
 */
@Component
@RequiredArgsConstructor
public class BusinessExpressOrderService implements IExpressOrderService {

    private final IConfigService configService;
    private final ISkuService skuService;

    private final BusinessOrder businessOrder;

    /**
     * 查询配送费
     */
    @Override
    public BigDecimal queryDeliveryFee(List<Product> products, Merchant merchant, @NotNull Address customAddress) {

        Config config = this.configService.queryConfigById(merchant.getMerchantId());
        if (config == null) return null;

        // 获取商品sku和数量的映射关系
        Map<Long, Integer> skuAndQuantityPair = products.stream().collect(Collectors.toMap(Product::getSkuId, Product::getQuantity));
        // 获取商品sku列表
        List<Sku> skus = this.skuService.queryBySkuIds(skuAndQuantityPair.keySet());
        // 计算商品总重量
        BigDecimal totalWeight = skus.stream().map(Sku::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 快递配送
        BOrderOfficialQueryPriceReq params = new BOrderOfficialQueryPriceReq();
        params.setWeight(totalWeight.toString());
        params.setSendManPrintAddr(merchant.getAddress());
        params.setRecManPrintAddr(customAddress.getAddress());

        // 快递设置成 ems TODO: 后续动态更改
        params.setKuaidiCom("ems");
        try {
            return businessOrder.queryPrice(params, config);
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
