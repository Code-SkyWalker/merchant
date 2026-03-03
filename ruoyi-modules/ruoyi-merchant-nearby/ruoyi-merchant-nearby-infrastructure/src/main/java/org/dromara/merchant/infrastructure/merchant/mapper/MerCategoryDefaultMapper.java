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

    /**
     * 复制默认分类数据到租户分类表（第一步：插入数据）
     * @param category 包含租户信息和创建人信息的对象
     * @return 插入的记录数
     */
    int writeDefault(@Param("category") CategoryDO category);

    /**
     * 更新 parent_id 为新的 ID（第二步：更新关系）
     * @param tenantId 租户 ID
     * @param createBy 创建人 ID
     * @return 更新的记录数
     */
    int updateParentIdsAfterInsert(@Param("tenantId") String tenantId, 
                                   @Param("createBy") Long createBy);
}
