package org.dromara.merchant.infrastructure.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.ParaPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.query.ParaQry;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.ParaDO;

public interface ParaMapper extends BaseMapperPlus<ParaDO, ParaDO> {

    Page<ParaPageCO> selectPage(Page<ParaPageCO> page, @Param("qry") ParaQry qry);

}
