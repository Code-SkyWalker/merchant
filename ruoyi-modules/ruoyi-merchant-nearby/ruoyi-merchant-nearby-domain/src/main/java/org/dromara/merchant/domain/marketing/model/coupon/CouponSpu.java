package org.dromara.merchant.domain.marketing.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 优惠券与商品关联实体
 * @Author Code Skywalker
 * @Date 2025/12/29 11:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
