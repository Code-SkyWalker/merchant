package org.dromara.merchant.infrastructure.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.BrandPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.query.BrandQry;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.BrandDO;

public interface BrandMapper extends BaseMapperPlus<BrandDO, BrandDO> {

    Page<BrandPageCO> selectPage(Page<BrandDO> page, @Param("qry") BrandQry qry);

}
