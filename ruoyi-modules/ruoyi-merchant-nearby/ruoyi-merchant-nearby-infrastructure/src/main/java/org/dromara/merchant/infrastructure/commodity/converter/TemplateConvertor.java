package org.dromara.merchant.infrastructure.commodity.converter;

import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.domain.commodity.model.Template;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.TemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 14:26
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TemplateConvertor {

    TemplateDO toDO(Template template);

    Template toEntity(TemplateDO templateDO);

    Template toEntity(TemplateCreateCmd cmd);

    Template toEntity(TemplateModifyCmd cmd);
}
