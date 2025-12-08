package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.SpecCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpecModifyCmd;
import org.dromara.merchant.domain.commodity.model.Spec;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpecDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecConvertor {
    SpecDO toDo(Spec spec);

    Spec toEntity(SpecDO specDO);

    Spec toEntity(SpecCreateCmd cmd);

    Spec toEntity(SpecModifyCmd cmd);
}
