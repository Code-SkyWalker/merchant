package org.dromara.huifu.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuFeeRateDO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface HuifuFeeRateMapper extends BaseMapperPlus<HuifuFeeRateDO, HuifuFeeRateDO> {


    List<HuifuFeeRateDO> queryByMerchantId(@Param("merchantId") Long merchantId);

    int deleteByMerchantId(Long merchantId);

}
