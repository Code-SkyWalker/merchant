package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.Data;

/**
 * @Description 优惠券与商品关联命令
 * @Author Code Skywalker
 * @Date 2025/12/30 09:31
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
