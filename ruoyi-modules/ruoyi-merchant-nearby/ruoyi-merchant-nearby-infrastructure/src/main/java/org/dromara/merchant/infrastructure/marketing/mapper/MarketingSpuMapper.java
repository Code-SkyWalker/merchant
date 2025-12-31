package org.dromara.merchant.infrastructure.marketing.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingSpuDO;

public interface MarketingSpuMapper extends BaseMapperPlus<MarketingSpuDO, MarketingSpuDO> {

    int deleteByMarketingId(Long marketingId);
}
