package org.dromara.huifu.infrastructure.convertor;

import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateCreateCmd;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateModifyCmd;
import org.dromara.huifu.domain.model.HuifuFeeRate;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuFeeRateDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:09
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HuifuFeeRateConvertor {

    HuifuFeeRateDO toDO(HuifuFeeRate feeRate);

    List<HuifuFeeRateDO> toDOList(List<HuifuFeeRate> rates);

    HuifuFeeRate toEntity(HuifuFeeRateDO huifuConfigDO);

    HuifuFeeRate toEntity(HuifuFeeRateCreateCmd cmd);

    HuifuFeeRate toEntity(HuifuFeeRateModifyCmd cmd);

    List<HuifuFeeRate> toEntityList(List<HuifuFeeRateDO> huifuFeeRateDOS);
}
