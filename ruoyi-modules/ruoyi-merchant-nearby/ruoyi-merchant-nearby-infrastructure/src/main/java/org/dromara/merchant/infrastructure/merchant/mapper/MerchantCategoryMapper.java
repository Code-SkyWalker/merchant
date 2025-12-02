package org.dromara.merchant.infrastructure.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCategoryDO;

public interface MerchantCategoryMapper {
    int batchInsertOrUpdate(@Param("list") List<MerchantCategoryDO> list);

    int deleteByMerchantId(@Param("merchantId") Long merchantId);
}
