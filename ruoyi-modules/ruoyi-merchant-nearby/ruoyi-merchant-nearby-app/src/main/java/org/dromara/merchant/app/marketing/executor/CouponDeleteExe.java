package org.dromara.merchant.app.marketing.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.gateway.ICouponSpuGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/30 09:53
 */
@Component
@RequiredArgsConstructor
public class CouponDeleteExe implements Executor<Long, Boolean> {

    private final ICouponGateway couponGateway;
    private final ICouponSpuGateway couponSpuGateway;

    @Override
    public Boolean execute(Long couponId) {

        // 删除优惠券与商品关联
        boolean couponSpusDeleted = this.couponSpuGateway.deleteByCouponId(couponId);

        // 删除优惠券
        boolean couponDeleted = this.couponGateway.delete(couponId);
        return couponDeleted && couponSpusDeleted;
    }
}
