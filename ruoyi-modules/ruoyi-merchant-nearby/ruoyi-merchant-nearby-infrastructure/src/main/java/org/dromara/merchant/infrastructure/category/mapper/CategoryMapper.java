package org.dromara.merchant.infrastructure.category.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.category.dto.data.command.CategoryPageQry;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDO;


public interface CategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insertSelective(CategoryDO record);

    CategoryDO selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(CategoryDO record);

    Page<CategoryDO> selectPages(@Param("qry") CategoryPageQry qry, @Param("page") Page<CategoryDO> page);

}
