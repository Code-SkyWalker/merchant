package org.dromara.merchant.infrastructure.commodity.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SkuTempCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.TemplatePageCO;
import org.dromara.merchant.client.commodity.dto.data.command.query.TemplateQry;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.TemplateDO;

import java.util.List;

public interface TemplateMapper extends BaseMapperPlus<TemplateDO, TemplateDO> {

    Page<TemplatePageCO> selectPage(Page<TemplatePageCO> page, @Param("qry") TemplateQry qry);

    List<SkuTempCO> selectSpecAndParaById(@Param("id") Integer id);


}
