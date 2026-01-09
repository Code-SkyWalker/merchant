package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.marketing.model.activity.CouponSpu;

import java.util.List;

/**
 * @Description 优惠券与商品关联网关
 * @Author Code Skywalker
 * @Date 2025/12/29 16:22
 */
public interface ICouponSpuGateway {

    /**
     * 保存
     *
     * @param couponSpus 优惠券与商品关联实体列表
     * @return 是否成功
     */
    boolean save(List<CouponSpu> couponSpus);

    /**
     * 删除
     *
     * @param couponId 优惠券主键
     * @return 是否成功
     */
    boolean deleteByCouponId(Long couponId);

    /**
     * 根据商品ID查询优惠券ID列表
     *
     * @param spuId 商品ID
     * @return 优惠券ID列表
     */
    List<Long> queryCouponIdsBySpuId(Long spuId);
}
