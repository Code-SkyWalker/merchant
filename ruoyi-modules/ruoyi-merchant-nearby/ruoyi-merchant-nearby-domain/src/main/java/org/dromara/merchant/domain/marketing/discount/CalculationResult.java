package org.dromara.merchant.domain.marketing.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
     * 原始总价
     */
    private BigDecimal originalTotal;

    /**
     * 优惠券优惠金额
     */
    private BigDecimal couponDiscountAmount;

    /**
     * 活动优惠金额
     */
    private BigDecimal marketingDiscountAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralDiscountAmount;

    /**
     * 佣金抵扣金额
     */
    private BigDecimal commDiscountAmount;

    /**
     * 折扣后小计 = 优惠券优惠金额 + 促销优惠金额 + 积分抵扣金额 + 佣金抵扣金额
     */
    private BigDecimal discountedSubtotal;

    /**
     * 运费
     */
    private BigDecimal shippingFee;

    /**
     * 应付总额= 折扣后小计 + 运费
     */
    private BigDecimal totalAmount;

}
