package org.dromara.merchant.domain.marketing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 优惠明细
 * @Author Code Skywalker
 * @Date 2026/1/7 15:15
 */
@Data
public class DiscountDetailsSubtotal {

    /**
     * 小计金额
     */
    private BigDecimal subtotal;

    /**
     * 优惠券优惠金额
     */
    private BigDecimal couponDiscountAmount;

    /**
     * 促销优惠金额
     */
    private BigDecimal marketingDiscountAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount;

    /**
     * 佣金优惠金额
     */
    private BigDecimal commDiscountAmount;

    /**
     * 优惠后金额
     */
    private BigDecimal finalAmount;

}
