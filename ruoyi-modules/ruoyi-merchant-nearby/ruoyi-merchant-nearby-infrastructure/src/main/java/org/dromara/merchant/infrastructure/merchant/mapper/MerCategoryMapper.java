package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;


public interface MerCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insertSelective(CategoryDO record);

    CategoryDO selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(CategoryDO record);

    Page<CategoryDO> selectPages(@Param("qry") MerCategoryPageQry qry, @Param("page") Page<CategoryDO> page);

}
