package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.ParaDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParaConvertor {
    ParaDO toDo(Para para);

    Para toEntity(ParaDO paraDO);

    Para toEntity(ParaCreateCmd cmd);

    Para toEntity(ParaModifyCmd cmd);
}
