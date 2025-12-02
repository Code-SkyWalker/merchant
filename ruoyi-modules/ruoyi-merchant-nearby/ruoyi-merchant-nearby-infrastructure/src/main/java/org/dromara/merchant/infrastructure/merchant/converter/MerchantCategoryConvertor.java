package org.dromara.merchant.infrastructure.merchant.converter;

import org.dromara.merchant.domain.merchant.model.MerchantCategory;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Description 商家与分类实体转换器
 * @Author Code Skywalker
 * @Date 2025/12/2 15:22
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantCategoryConvertor {

    List<MerchantCategoryDO> toMerchantCategoryDO(List<MerchantCategory> merchantCategories);

}
