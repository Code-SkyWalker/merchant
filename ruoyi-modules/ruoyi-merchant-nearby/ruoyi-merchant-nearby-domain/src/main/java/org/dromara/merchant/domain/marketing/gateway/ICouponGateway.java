package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

import java.util.Collection;
import java.util.List;

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

    /**
     * 根据优惠券Id列表查询优惠券
     *
     * @param couponIds 优惠券Id列表
     * @return 优惠券列表
     */
    List<Coupon> queryByIds(Collection<Long> couponIds);
}
