package org.dromara.merchant.app.marketing.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.marketing.ICouponService;
import org.dromara.merchant.app.marketing.executor.CouponCreateExe;
import org.dromara.merchant.app.marketing.executor.CouponDeleteExe;
import org.dromara.merchant.app.marketing.executor.CouponModifyExe;
import org.dromara.merchant.client.marketing.dto.data.command.CouponCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.CouponModifyCmd;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/30 10:09
 */
@Component
@RequiredArgsConstructor
public class CouponService implements ICouponService {

    private final CouponCreateExe couponCreateExe;
    private final CouponDeleteExe couponDeleteExe;
    private final CouponModifyExe couponModifyExe;

    private final ICouponGateway couponGateway;

    @Override
    public boolean create(CouponCreateCmd cmd) {
        return this.couponCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(CouponModifyCmd cmd) {
        return this.couponModifyExe.execute(cmd);
    }

    @Override
    public boolean delete(Long id) {
        return this.couponDeleteExe.execute(id);
    }

    @Override
    public Coupon queryById(Long id) {
        return couponGateway.queryById(id);
    }

    /**
     * 根据优惠券ID列表查询优惠券
     *
     * @param couponIds 优惠券ID列表
     * @return 优惠券列表
     */
    @Override
    public List<Coupon> queryByIds(List<Long> couponIds) {
        return couponGateway.queryByIds(couponIds);
    }
}
