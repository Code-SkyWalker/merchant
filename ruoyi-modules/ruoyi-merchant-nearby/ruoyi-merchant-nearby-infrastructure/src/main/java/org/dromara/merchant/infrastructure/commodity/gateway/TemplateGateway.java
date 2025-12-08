package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.dromara.merchant.domain.commodity.model.Template;
import org.dromara.merchant.infrastructure.commodity.converter.TemplateConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.TemplateMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.TemplateDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商品模板网关接口
 * @Author Code Skywalker
 * @Date 2025/12/8 14:25
 */
@Component
@RequiredArgsConstructor
public class TemplateGateway implements ITemplateGateway {

    private final TemplateMapper mapper;
    private final TemplateConvertor templateConvertor;

    @Override
    public boolean save(Template template) {
        return this.mapper.insertOrUpdate(templateConvertor.toDO(template));
    }

    @Override
    public boolean delete(Integer id) {
        return this.mapper.deleteById(id) > 0;
    }

    @Override
    public Template queryById(Integer id) {
        TemplateDO templateDO = this.mapper.selectById(id);
        return this.templateConvertor.toEntity(templateDO);
    }
}
