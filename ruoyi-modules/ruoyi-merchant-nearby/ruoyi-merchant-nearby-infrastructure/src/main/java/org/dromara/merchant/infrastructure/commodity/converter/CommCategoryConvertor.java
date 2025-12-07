package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.domain.commodity.model.CommCategory;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommCategoryConvertor {
    CategoryDO toDO(CommCategory commCategory);

    CommCategory toEntity(CategoryDO categoryDO);

    List<CategoryDO> toDOList(List<CommCategory> commCategory);

    List<CommCategory> toEntityList(List<CategoryDO> categoryDO);
}
