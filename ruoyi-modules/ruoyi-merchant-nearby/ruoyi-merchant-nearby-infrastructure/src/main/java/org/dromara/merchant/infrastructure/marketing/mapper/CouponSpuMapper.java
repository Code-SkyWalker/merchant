package org.dromara.merchant.infrastructure.marketing.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.CouponSpuDO;

public interface CouponSpuMapper extends BaseMapperPlus<CouponSpuDO, CouponSpuDO> {

    int deleteByCouponId(Long couponId);
}
