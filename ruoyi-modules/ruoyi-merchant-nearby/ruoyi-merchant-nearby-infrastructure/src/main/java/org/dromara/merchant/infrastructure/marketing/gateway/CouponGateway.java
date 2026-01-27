package org.dromara.merchant.infrastructure.marketing.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.marketing.gateway.ICouponGateway;
import org.dromara.merchant.domain.marketing.model.coupon.Coupon;
import org.dromara.merchant.infrastructure.marketing.converter.CouponConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.CouponMapper;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 根据优惠券Id列表查询优惠券
     *
     * @param couponIds 优惠券Id列表
     * @return 优惠券列表
     */
    @Override
    public List<Coupon> queryByIds(List<Long> couponIds) {
        if (couponIds == null || couponIds.isEmpty()) return List.of();
        List<CouponDO> couponDOS = this.mapper.selectByIdList(couponIds);
        return convertor.toEntityList(couponDOS);
    }
}
