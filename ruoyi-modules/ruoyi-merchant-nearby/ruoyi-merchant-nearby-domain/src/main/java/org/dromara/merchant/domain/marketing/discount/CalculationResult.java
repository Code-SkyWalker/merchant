package org.dromara.merchant.domain.marketing.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.domain.order.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 优惠明细
 * @Author Code Skywalker
 * @Date 2026/1/7 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationResult {

    /**
     * 优惠券列表
     */
    private List<Coupon> coupons;

    /**
     * 优惠活动列表
     */
    private List<Marketing> marketings;

    /**
     * 原始总价
     */
    private BigDecimal originalTotal;

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
     * 折扣后小计 = 优惠券优惠金额 + 促销优惠金额 + 积分抵扣金额 + 佣金抵扣金额
     */
    private BigDecimal discountedSubtotal = BigDecimal.ZERO;

    /**
     * 运费
     */
    private BigDecimal shippingFee = BigDecimal.ZERO;

    /**
     * 应付总额= 折扣后小计 + 运费
     */
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /**
     * 订单信息包含原价数量和折后价
     */
    private Map<Product, BigDecimal> products;

    /**
     * 设置运费并更新应付总额
     *
     * @param shippingFee 运费
     * @return 当前对象
     */
    public CalculationResult setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
        this.totalAmount = this.totalAmount.add(shippingFee);
        return this;
    }

    public void updateOrder(Order order) {
        order.setGoodsAmount(this.originalTotal);
        order.setFreightAmount(this.shippingFee);
        order.setDiscountAmount(this.marketingDiscountAmount);
        order.setCouponAmount(this.couponDiscountAmount);
        order.setPointAmount(this.integralDiscountAmount);
        order.setCommAmount(this.commDiscountAmount);
        order.setPayableAmount(this.totalAmount.max(BigDecimal.ZERO));
    }

}
