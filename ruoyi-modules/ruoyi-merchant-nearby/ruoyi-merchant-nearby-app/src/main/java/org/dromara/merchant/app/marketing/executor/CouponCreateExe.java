package org.dromara.merchant.app.marketing.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.marketing.dto.data.command.CouponCreateCmd;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.gateway.ICouponSpuGateway;
import org.dromara.merchant.infrastructure.marketing.converter.CouponConvertor;
import org.dromara.merchant.infrastructure.marketing.converter.CouponSpuConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 创建优惠券执行器
 * @Author Code Skywalker
 * @Date 2025/12/30 09:53
 */
@Component
@RequiredArgsConstructor
public class CouponCreateExe implements Executor<CouponCreateCmd, Boolean> {

    private final ICouponGateway couponGateway;
    private final CouponConvertor couponConvertor;

    private final ICouponSpuGateway couponSpuGateway;
    private final CouponSpuConvertor couponSpuConvertor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean execute(CouponCreateCmd cmd) {

        // 创建优惠券
        Long couponId = couponGateway.save(couponConvertor.toEntity(cmd));

        // 创建优惠券与商品关系
        cmd.getCouponSpusCmds().forEach(couponSpu -> couponSpu.setCouponId(couponId));

        return this.couponSpuGateway.save(couponSpuConvertor.cmdToEntity(cmd.getCouponSpusCmds()));
    }
}
