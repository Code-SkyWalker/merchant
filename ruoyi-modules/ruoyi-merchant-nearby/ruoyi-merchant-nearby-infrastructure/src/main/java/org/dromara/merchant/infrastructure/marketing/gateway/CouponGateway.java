package org.dromara.merchant.infrastructure.marketing.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.model.activity.Coupon;
import org.dromara.merchant.infrastructure.marketing.converter.CouponConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.CouponMapper;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 优惠券网关
 * @Author Code Skywalker
 * @Date 2025/12/29 11:04
 */
@Component
@RequiredArgsConstructor
public class CouponGateway implements ICouponGateway {

    private final CouponMapper mapper;
    private final CouponConvertor convertor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(Coupon coupon) {
        this.mapper.insertOrUpdate(convertor.toDo(coupon));
        return coupon.getId();
    }

    @Override
    public boolean delete(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    @Override
    public Coupon queryById(Long id) {
        CouponDO couponDO = this.mapper.selectById(id);
        return convertor.toEntity(couponDO);
    }
}
