package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;
import org.dromara.merchant.client.Command;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单创建命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderCreateCmd {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 商品列表
     */
    private List<PriceCalculationCmd.Product> products;

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
     * 是否在配送范围内
     */
    private Boolean withinRange;


    public PriceCalculationCmd toPriceCalculationCmd() {
        return new PriceCalculationCmd()
                .setMerchantId(merchantId)
                .setProducts(products)
                .setCoupons(coupons)
                .setMarketings(marketings)
                .setIntegralDiscountAmount(integralDiscountAmount)
                .setCommDiscountAmount(commDiscountAmount)
                .setAddressId(addressId)
                .setWithinRange(withinRange);
    }

}
