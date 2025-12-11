package org.dromara.merchant.infrastructure.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuDetailCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpuQry;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpuDO;


public interface SpuMapper extends BaseMapperPlus<SpuDO, SpuDO> {

    SpuDetailCO selectDetailById(@Param("id") Long id);

    Page<SpuPageCO> selectPage(Page<SpuPageCO> page, @Param("qry") SpuQry qry);
}
