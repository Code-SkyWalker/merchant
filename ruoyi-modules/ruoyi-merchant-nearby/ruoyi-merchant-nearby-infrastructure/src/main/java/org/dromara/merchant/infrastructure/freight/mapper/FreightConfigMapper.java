package org.dromara.merchant.infrastructure.freight.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.freight.dto.data.clientobject.FreightConfigCO;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.FreightConfigDO;

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

    /**
     * 根据ID查询配置
     *
     * @param merchantId 配置ID
     * @return 配置
     */
    FreightConfigCO selectCOByMerchantId(@Param("merchantId") Long merchantId, @Param("deliveryMethod") String deliveryMethod);

}
