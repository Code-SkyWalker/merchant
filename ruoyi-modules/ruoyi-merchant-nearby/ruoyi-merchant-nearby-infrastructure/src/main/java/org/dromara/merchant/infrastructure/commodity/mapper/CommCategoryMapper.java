package org.dromara.merchant.infrastructure.commodity.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;

public interface CommCategoryMapper extends BaseMapperPlus<CategoryDO, CategoryDO> {

    List<CategoryDO> selectByParentId(@Param("parentId")Integer parentId);

}
