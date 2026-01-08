package org.dromara.merchant.infrastructure.marketing.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponSpuDO;

import java.util.List;

public interface CouponSpuMapper extends BaseMapperPlus<CouponSpuDO, CouponSpuDO> {

    int deleteByCouponId(Long couponId);

    /**
     * 根据商品ID查询优惠券ID列表
     *
     * @param spuId 商品ID
     * @return 优惠券ID列表
     */
    List<Long> selectCouponIdsBySpuId(Long spuId);
}
