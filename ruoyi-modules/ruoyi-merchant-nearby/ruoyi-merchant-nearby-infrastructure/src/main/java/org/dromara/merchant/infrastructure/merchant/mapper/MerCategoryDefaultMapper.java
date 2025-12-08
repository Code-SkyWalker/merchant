package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryDefaultPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;

public interface MerCategoryDefaultMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insertSelective(CategoryDefaultDO record);

    CategoryDefaultDO selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(CategoryDefaultDO record);

    Page<CategoryDefaultDO> selectPages(@Param("qry") MerCategoryDefaultPageQry qry, @Param("page") Page<CategoryDefaultDO> page);

    int deleteByTenantId(@Param("tenantId") String tenantId);

    int writeDefault(@Param("category") CategoryDO category);
}
