package org.dromara.merchant.client.cert.co;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/2/11 13:28
 */
@Data
public class CouponInfoCO {

    /**
     * 优惠券ID
     */
    private Long id;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 优惠券生效时间
     */
    private LocalDateTime serviceBegin;
    /**
     * 优惠券失效时间
     */
    private LocalDateTime serviceEnd;
    /**
     * 优惠券适用范围
     */
    private Integer discountType;
    /**
     * 优惠券折扣
     */
    private BigDecimal discount;
    /**
     * 优惠券适用商品范围
     */
    private String goodsRange;
}
