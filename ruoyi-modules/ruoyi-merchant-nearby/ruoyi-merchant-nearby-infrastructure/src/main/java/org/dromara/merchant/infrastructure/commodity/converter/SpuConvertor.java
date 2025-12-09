package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.dromara.merchant.domain.commodity.model.Spu;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/9 14:32
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpuConvertor {

    Spu toEntity(SpuDO spuDO);

    SpuDO toDo(Spu spu);

    Spu toEntity(SpuCreateCmd cmd);

    Spu toEntity(SpuModifyCmd cmd);
}
