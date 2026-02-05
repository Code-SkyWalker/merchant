package org.dromara.merchant.client.order.dto.data.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.merchant.client.marketing.dto.data.command.PriceCalculationCmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
    @NotNull
    private Long userId;

    /**
     * 商家id
     */
    @NotNull
    private Long merchantId;

    /**
     * 商品列表
     */
    @NotEmpty
    private List<PriceCalculationCmd.Product> products;

    /**
     * 优惠券id列表
     */
    private List<Long> coupons = new ArrayList<>();

    /**
     * 营销活动id列表
     */
    private List<Long> marketings = new ArrayList<>();

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount = BigDecimal.ZERO;

    /**
     * 佣金抵扣金额
     */
    private BigDecimal commDiscountAmount = BigDecimal.ZERO;

    /**
     * 订单类型
     * NORMAL: 普通订单
     * SECKILL: 秒杀订单
     * GROUP_BUY: 团购订单
     * PRE_SALE: 预售订单
     * VIP: 会员订单
     */
    private String orderType;

    /**
     * 用户地址id
     */
    @NotNull
    private Long addressId;

    /**
     * 订单来源
     * PC: PC端
     * APP: APP应用
     * WECHAT_MINI_PROGRAM: 微信小程序
     * ALIPAY_MINI_PROGRAM: 支付宝小程序
     * H5: H5页面
     */
    private String source;

    /**
     * 配送方式
     * EXPRESS_DELIVERY: 快递配送
     * LOCAL_DELIVERY: 本地配送
     * PICKUP_DELIVERY: 自提配送
     * NONE_DELIVERY: 无配送
     */
    @NotNull
    private String deliveryMethod;

    /**
     * 转换为价格计算命令
     * @return 价格计算命令
     */
    public PriceCalculationCmd toPriceCalculationCmd() {
        return new PriceCalculationCmd()
                .setMerchantId(merchantId)
                .setProducts(products)
                .setCoupons(coupons)
                .setMarketings(marketings)
                .setIntegralDiscountAmount(integralDiscountAmount)
                .setCommDiscountAmount(commDiscountAmount)
                .setAddressId(addressId)
                .setDeliveryMethod(deliveryMethod);
    }

}
