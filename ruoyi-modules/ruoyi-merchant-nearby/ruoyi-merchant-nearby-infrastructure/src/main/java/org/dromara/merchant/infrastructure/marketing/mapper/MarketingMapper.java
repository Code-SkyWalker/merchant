package org.dromara.merchant.infrastructure.marketing.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingCO;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;

public interface MarketingMapper extends BaseMapperPlus<MarketingDO, MarketingDO> {

    MarketingCO queryById(Long id);

}
