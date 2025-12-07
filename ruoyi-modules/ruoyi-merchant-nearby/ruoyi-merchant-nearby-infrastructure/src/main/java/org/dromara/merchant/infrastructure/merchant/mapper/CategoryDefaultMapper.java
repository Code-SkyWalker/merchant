package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;

public interface CategoryDefaultMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insertSelective(CategoryDefaultDO record);

    CategoryDefaultDO selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(CategoryDefaultDO record);

    Page<CategoryDefaultDO> selectPages(@Param("qry") DefaultCategoryPageQry qry, @Param("page") Page<CategoryDefaultDO> page);

    int deleteByTenantId(@Param("tenantId") String tenantId);

    int writeDefault(@Param("category") CategoryDO category);
}
