package org.dromara.merchant.infrastructure.merchant.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCategoryDO;

import java.util.List;

public interface MerchantCategoryMapper {
    int batchInsertOrUpdate(@Param("list") List<MerchantCategoryDO> list);

    int deleteByMerchantId(@Param("merchantId") Long merchantId);

}
