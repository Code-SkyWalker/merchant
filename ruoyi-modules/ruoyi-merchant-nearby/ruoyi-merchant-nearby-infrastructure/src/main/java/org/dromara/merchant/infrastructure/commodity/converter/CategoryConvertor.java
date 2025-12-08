package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.domain.commodity.model.Category;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryConvertor {
    CategoryDO toDO(Category category);

    Category toEntity(CategoryDO categoryDO);

    List<CategoryDO> toDOList(List<Category> category);

    List<Category> toEntityList(List<CategoryDO> categoryDO);

    Category toEntity(CategoryCreateCmd cmd);

    Category toEntity(CategoryModifyCmd cmd);
}
