package org.dromara.merchant.infrastructure.marketing.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingSpuDO;

import java.util.List;

public interface MarketingSpuMapper extends BaseMapperPlus<MarketingSpuDO, MarketingSpuDO> {

    int deleteByMarketingId(Long marketingId);

    /**
     * 根据商品ID查询营销ID列表
     *
     * @param spuId 商品ID
     * @return 营销ID列表
     */
    List<Long> selectMarketingIdsBySpuId(Long spuId);
}
