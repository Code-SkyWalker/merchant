package org.dromara.merchant.infrastructure.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpecPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpecQry;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.SpecDO;

public interface SpecMapper extends BaseMapperPlus<SpecDO, SpecDO> {

    Page<SpecPageCO> selectPage(Page<SpecPageCO> page, @Param("qry") SpecQry qry);

}
