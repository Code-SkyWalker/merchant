package org.dromara.merchant.infrastructure.merchant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;

import java.util.List;


public interface MerCategoryMapper {
    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Long categoryId);

    /**
     * 插入数据
     */
    int insertSelective(CategoryDO record);

    /**
     * 根据主键查询
     */
    CategoryDO selectByPrimaryKey(Long categoryId);

    /**
     * 根据主键更新数据
     */
    int updateByPrimaryKeySelective(CategoryDO record);

    /**
     * 分页查询
     */
    Page<CategoryDO> selectPages(@Param("qry") MerCategoryPageQry qry, @Param("page") Page<CategoryDO> page);

    /**
     * 根据父级ID查询
     */
    List<MerCategoryCO> selectByParentId(Long parentId);


}
