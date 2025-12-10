package org.dromara.merchant.infrastructure.commodity.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.CategoryTreeCO;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;

import java.util.List;

public interface CategoryMapper extends BaseMapperPlus<CategoryDO, CategoryDO> {

    List<CategoryDO> selectByParentId(@Param("parentId")Integer parentId);


    List<CategoryTreeCO> selectAll(@Param("merchantId") Long merchantId);

}
