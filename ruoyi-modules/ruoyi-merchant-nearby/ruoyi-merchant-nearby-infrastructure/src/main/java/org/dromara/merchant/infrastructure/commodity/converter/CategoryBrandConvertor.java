package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.domain.commodity.model.CategoryBrand;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryBrandConvertor {
    CategoryBrand toEntity(CategoryBrandDO categoryBrandDO);

    CategoryBrandDO toDO(CategoryBrand categoryBrand);

    CategoryBrand toEntity(BrandDeleteCmd cmd);
}
