package org.dromara.merchant.infrastructure.marketing.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingCO;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingPageCo;
import org.dromara.merchant.client.marketing.dto.data.command.query.MarketingPageQry;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;

import java.util.Collection;
import java.util.List;

public interface MarketingMapper extends BaseMapperPlus<MarketingDO, MarketingDO> {

    /**
     * 根据SpuId查询可用的营销活动
     *
     * @param spuId SpuId
     * @return 可用的营销活动
     */
    List<MarketingDO> selectAvailableMarketingsBySpuId(Long spuId);

    /**
     * 根据Id查询
     *
     * @param id 营销活动Id
     * @return 营销活动
     */
    MarketingCO queryById(Long id);

    /**
     * 分页查询
     *
     * @param qry  查询参数
     * @param page 分页参数
     * @return 分页结果
     */
    Page<MarketingPageCo> queryPages(@Param("qry") MarketingPageQry qry, Page<MarketingDO> page);

    /**
     * 根据Id列表查询
     *
     * @param marketingIds 营销活动Id列表
     * @return 营销活动
     */
    List<Marketing> selectByIdList(@Param("marketingIds") Collection<Long> marketingIds);
}
