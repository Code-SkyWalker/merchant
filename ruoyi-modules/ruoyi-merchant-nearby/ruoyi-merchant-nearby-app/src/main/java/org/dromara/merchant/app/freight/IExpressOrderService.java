package org.dromara.merchant.app.freight;

import org.dromara.merchant.domain.address.model.Address;
import org.dromara.merchant.domain.marketing.discount.Product;
import org.dromara.merchant.domain.merchant.model.Merchant;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 13:35
 */
public interface IExpressOrderService {

    /**
     * 同城配送
     */
    int CITY_DELIVERY = 0;

    /**
     * 快递配送
     */
    int NORMAL_DELIVERY = 1;

    /**
     * 查询配送费
     *
     * @param merchant      商家
     * @param customAddress 收货地址
     */
    BigDecimal queryDeliveryFee(List<Product> products, @NotNull Merchant merchant, @NotNull Address customAddress);


    /**
     * 创建订单
     */
    void createOrder();


    /**
     * 查询订单
     */
    void queryOrder();


    /**
     * 更新订单
     */
    void updateOrder();

}
