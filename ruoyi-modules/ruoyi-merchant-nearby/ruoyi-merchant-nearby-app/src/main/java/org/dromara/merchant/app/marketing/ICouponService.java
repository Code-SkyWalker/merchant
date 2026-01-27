package org.dromara.merchant.app.marketing;

import org.dromara.merchant.client.marketing.dto.data.command.CouponCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.CouponModifyCmd;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;

import java.util.List;

/**
 * @Description 优惠券服务接口
 * @Author Code Skywalker
 * @Date 2025/12/30 09:52
 */
public interface ICouponService {

    /**
     * 创建优惠券
     *
     * @param cmd 优惠券创建命令
     * @return 创建结果
     */
    boolean create(CouponCreateCmd cmd);

    /**
     * 修改优惠券
     *
     * @param cmd 优惠券修改命令
     * @return 修改结果
     */
    boolean modify(CouponModifyCmd cmd);

    /**
     * 删除优惠券
     *
     * @param id 优惠券ID
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 根据优惠券ID查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    Coupon queryById(Long id);

    /**
     * 根据优惠券ID列表查询优惠券
     *
     * @param couponIds 优惠券ID列表
     * @return 优惠券列表
     */
    List<Coupon> queryByIds(List<Long> couponIds);
}
