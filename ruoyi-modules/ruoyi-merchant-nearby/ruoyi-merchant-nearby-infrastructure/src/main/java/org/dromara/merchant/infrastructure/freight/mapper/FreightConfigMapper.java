package org.dromara.merchant.infrastructure.freight.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.FreightConfigDO;

import java.util.List;

public interface FreightConfigMapper extends BaseMapperPlus<FreightConfigDO, FreightConfigDO> {

    /**
     * 根据商户ID删除所有配置
     * @param merchantId 商户ID
     * @return 删除记录数
     */
    int deleteByMerchantId(Long merchantId);

    /**
     * 根据商户ID查询所有配置
     * @param merchantId 商户ID
     * @return 配置列表
     */
    List<FreightConfigDO> selectListByMerchantId(Long merchantId);
}
