package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 14:00
 */
@Data
@Accessors(chain = true)
public class PriceCalculationCmd {

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 商品列表
     */
    private List<Product> products;

    /**
     * 优惠券id列表
     */
    private List<Long> coupons;

    /**
     * 营销活动id列表
     */
    private List<Long> marketings;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount;

    /**
     * 佣金抵扣金额
     */
    private BigDecimal commDiscountAmount;

    /**
     * 用户地址id
     */
    private Long addressId;

    /**
     * 配送方式
     */
    private String deliveryMethod;


    @Data
    public static class Product {
        private Long skuId;
        private int quantity;
    }

}
