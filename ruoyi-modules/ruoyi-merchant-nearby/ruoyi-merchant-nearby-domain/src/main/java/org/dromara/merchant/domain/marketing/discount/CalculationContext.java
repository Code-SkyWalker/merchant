package org.dromara.merchant.domain.marketing.discount;

import lombok.Data;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/12 15:34
 */
@Data
public class CalculationContext {

    /**
     * 当前价格映射，记录每个商品的当前价格（经过优惠计算后的价格）
     */
    private Map<Product, BigDecimal> currentPrices;

    /**
     * 优惠券优惠金额
     */
    private BigDecimal couponDiscountAmount = BigDecimal.ZERO;

    /**
     * 活动优惠金额
     */
    private BigDecimal marketingDiscountAmount = BigDecimal.ZERO;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount = BigDecimal.ZERO;

    /**
     * 佣金抵扣金额
     */
    private BigDecimal commDiscountAmount = BigDecimal.ZERO;

    /**
     * 原始总价，所有商品的原始价格总和
     */
    private BigDecimal originalTotal = BigDecimal.ZERO;

    /**
     * 运费金额
     */
    private BigDecimal shippingFee = BigDecimal.ZERO;

    /**
     * 可用的优惠活动列表
     */
    private List<Marketing> marketings;

    /**
     * 可用的优惠券列表
     */
    private List<Coupon> coupons;

    /**
     * 构造函数，初始化计算上下文
     *
     * @param products 商品列表
     * @param marketings 优惠活动列表
     * @param coupons 优惠券列表
     * @param shippingFee 运费
     */
    public CalculationContext(List<Product> products, List<Marketing> marketings, List<Coupon> coupons, BigDecimal shippingFee,
                              BigDecimal integralDiscountAmount, BigDecimal commDiscountAmount) {
        this.currentPrices = new HashMap<>();
        // 初始化当前价格为商品原始价格

        for (Product product : products) {
            BigDecimal totalPrice = product.getTotalPrice();
            this.currentPrices.put(product, totalPrice);
            this.originalTotal = this.originalTotal.add(totalPrice);
        }
        this.marketings = marketings;
        this.coupons = coupons;
        this.shippingFee = shippingFee;
        this.integralDiscountAmount = integralDiscountAmount;
        this.commDiscountAmount = commDiscountAmount;
    }

}
