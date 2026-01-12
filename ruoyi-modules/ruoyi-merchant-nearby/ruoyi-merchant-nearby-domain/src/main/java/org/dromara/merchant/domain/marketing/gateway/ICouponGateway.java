package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

/**
 * @Description 优惠券网关
 * @Author Code Skywalker
 * @Date 2025/12/29 11:02
 */
public interface ICouponGateway {

    /**
     * 保存
     *
     * @param coupon 优惠券
     * @return 是否成功
     */
    Long save(Coupon coupon);

    /**
     * 删除
     *
     * @param id 优惠券id
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 查询
     *
     * @param id 优惠券Id
     * @return 优惠券
     */
    Coupon queryById(Long id);

}
