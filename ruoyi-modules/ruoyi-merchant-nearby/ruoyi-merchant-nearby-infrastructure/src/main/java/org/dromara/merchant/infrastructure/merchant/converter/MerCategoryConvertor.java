package org.dromara.merchant.infrastructure.merchant.converter;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultCreateCmd;
import org.dromara.merchant.domain.merchant.model.MerCategory;
import org.dromara.merchant.domain.merchant.model.MerCategoryDefault;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Description 默认类型转换器
 * @Author Code Skywalker
 * @Date 2025-10-23 15:27
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerCategoryConvertor {

    /* ================================ category default ===================================== */


    /**
     * 数据对象转换成实体对象
     *
     * @param dataObject 数据对象
     * @return 领域对象
     */
    MerCategoryDefault toCategoryDefaultEntity(CategoryDefaultDO dataObject);

    /**
     * 命令数据对象转换成领域对象
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    MerCategoryDefault toCategoryDefaultEntity(MerCategoryDefaultCreateCmd cmd);

    /**
     * 命令数据对象转换成领域对象
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    MerCategoryDefault toCategoryDefaultEntity(MerCategoryDefaultModifyCmd cmd);

    /**
     * 领域对象转换成数据对象
     *
     * @param domain 领域对象
     * @return 数据对象
     */
    CategoryDefaultDO toCategoryDefaultDO(MerCategoryDefault domain);

    /**
     * 数据对象列表转换成客户端对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 客户端对象列表
     */
    Page<MerCategoryDefaultCO> toCategoryDefaultCOList(Page<CategoryDefaultDO> categoryDOs);

    /**
     * 数据对象转换成客户端对象
     *
     * @param categoryDO 数据对象
     * @return 客户端对象
     */
    MerCategoryDefaultCO toCategoryDefaultCO(CategoryDefaultDO categoryDO);


    /* ================================ category ===================================== */
    /**
     * 数据对象转换成实体对象
     *
     * @param dataObject 数据对象
     * @return 领域对象
     */
    MerCategory toCategoryEntity(CategoryDefaultDO dataObject);


    /**
     * 命令数据对象转换成实体对象
     *
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    MerCategory toCategoryEntity(MerCategoryCreateCmd cmd);

    /**
     * 命令数据对象转换成实体对象
     *
     * @param cmd 命令数据对象
     * @return 领域对象
     */
    MerCategory toCategoryEntity(MerCategoryModifyCmd cmd);


    /**
     * 领域对象转换成数据对象
     *
     * @param domain 领域对象
     * @return 数据对象
     */
    CategoryDO toCategoryDO(MerCategory domain);

    /**
     * 数据对象列表转换成实体对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 领域对象列表
     */
    List<MerCategory> toCategoryEntityList(List<CategoryDO> categoryDOs);

    /**
     * 数据对象列表转换成客户端对象列表
     *
     * @param categoryDOs 数据对象列表
     * @return 客户端对象列表
     */
    Page<MerCategoryCO> toCategoryCOPage(Page<CategoryDO> categoryDOs);

    /**
     * 数据对象转换成客户端对象
     *
     * @param categoryDO 数据对象
     * @return 客户端对象
     */
    MerCategoryCO toCategoryCO(CategoryDO categoryDO);
}
