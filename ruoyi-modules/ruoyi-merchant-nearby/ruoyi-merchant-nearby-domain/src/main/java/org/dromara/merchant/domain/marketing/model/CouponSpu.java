package org.dromara.merchant.domain.marketing.model;

import lombok.Data;

/**
 * @Description 优惠券与商品关联实体
 * @Author Code Skywalker
 * @Date 2025/12/29 11:29
 */
@Data
public class CouponSpu {

    /**
     * 优惠券与商品关联ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 删除标识
     */
    private Boolean deleted = false;

}
