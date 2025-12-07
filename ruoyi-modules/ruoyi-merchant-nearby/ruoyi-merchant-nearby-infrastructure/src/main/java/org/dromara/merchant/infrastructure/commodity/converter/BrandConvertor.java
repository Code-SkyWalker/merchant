package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.domain.commodity.model.Brand;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.BrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandConvertor {
    BrandDO toDo(Brand brand);

    Brand toEntity(BrandDO brandDO);
}
