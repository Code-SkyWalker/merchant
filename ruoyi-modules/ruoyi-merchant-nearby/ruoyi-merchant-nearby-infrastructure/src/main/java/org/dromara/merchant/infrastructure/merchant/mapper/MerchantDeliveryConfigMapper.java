package org.dromara.merchant.infrastructure.merchant.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDeliveryConfigDO;

import java.util.List;

public interface MerchantDeliveryConfigMapper extends BaseMapperPlus<MerchantDeliveryConfigDO, MerchantDeliveryConfigDO> {

    /**
     * 根据主键删除
     * @param deliveryId 配置ID
     * @return 删除记录数
     */
    int deleteByPrimaryKey(Long deliveryId);

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
    List<MerchantDeliveryConfigDO> selectListByMerchantId(Long merchantId);
}
