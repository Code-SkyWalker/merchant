package org.dromara.merchant.infrastructure.category.converter;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.client.category.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryModifyCmd;
import org.dromara.merchant.domain.category.model.Category;
import org.dromara.merchant.domain.category.model.CategoryDefault;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDefaultDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Description 默认类型转换器
 * @Author Code Skywalker
 * @Date 2025-10-23 15:27
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryConvertor {

    /* ================================ category default ===================================== */


    /**
     * 数据对象转换成实体对象
     *
     * @param dataObject 数据对象
     * @return 领域对象
     */
    CategoryDefault toCategoryDefaultEntity(CategoryDefaultDO dataObject);

    /**
     * 命令数据对象转换成领域对象
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    CategoryDefault toCategoryDefaultEntity(DefaultCategoryCreateCmd cmd);

    /**
     * 命令数据对象转换成领域对象
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    CategoryDefault toCategoryDefaultEntity(DefaultCategoryModifyCmd cmd);

    /**
     * 领域对象转换成数据对象
     *
     * @param domain 领域对象
     * @return 数据对象
     */
    CategoryDefaultDO toCategoryDefaultDO(CategoryDefault domain);

    /**
     * 数据对象列表转换成客户端对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 客户端对象列表
     */
    Page<CategoryDefaultCO> toCategoryDefaultCOList(Page<CategoryDefaultDO> categoryDOs);

    /**
     * 数据对象转换成客户端对象
     *
     * @param categoryDO 数据对象
     * @return 客户端对象
     */
    CategoryDefaultCO toCategoryDefaultCO(CategoryDefaultDO categoryDO);


    /* ================================ category ===================================== */
    /**
     * 数据对象转换成实体对象
     *
     * @param dataObject 数据对象
     * @return 领域对象
     */
    Category toCategoryEntity(CategoryDefaultDO dataObject);


    /**
     * 命令数据对象转换成实体对象
     *
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    Category toCategoryEntity(CategoryCreateCmd cmd);

    /**
     * 命令数据对象转换成实体对象
     *
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    Category toCategoryEntity(CategoryModifyCmd cmd);


    /**
     * 领域对象转换成数据对象
     *
     * @param domain 领域对象
     * @return 数据对象
     */
    CategoryDO toCategoryDO(Category domain);

    /**
     * 数据对象列表转换成实体对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 领域对象列表
     */
    List<Category> toCategoryEntityList(List<CategoryDO> categoryDOs);

    /**
     * 数据对象列表转换成客户端对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 客户端对象列表
     */
    Page<CategoryCO> toCategoryCOPage(Page<CategoryDO> categoryDOs);

    /**
     * 数据对象转换成客户端对象
     *
     * @param categoryDO 数据对象
     * @return 客户端对象
     */
    CategoryCO toCategoryCO(CategoryDO categoryDO);
}
