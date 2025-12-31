package org.dromara.merchant.infrastructure.marketing.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingCO;
import org.dromara.merchant.client.marketing.dto.data.client.MarketingPageCo;
import org.dromara.merchant.client.marketing.dto.data.command.query.MarketingPageQry;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;

public interface MarketingMapper extends BaseMapperPlus<MarketingDO, MarketingDO> {

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

}
