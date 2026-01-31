package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.domain.commodity.model.Spec;
import org.dromara.merchant.domain.commodity.model.Template;
import org.springframework.stereotype.Service;

/**
 * @Description 商品模板协调服务
 * @Author Code Skywalker
 * @Date 2025/12/8 15:30
 */
@Service
@RequiredArgsConstructor
public class TemplateConformityService {

    private final ISpecGateway specGateway;
    private final IParaGateway paraGateway;
    private final ITemplateGateway templateGateway;

    /**
     * 在添加规格后更新模板的规格数量
     *
     * @param spec 新添加的规格
     */
    public void incrementSpecCount(Spec spec) {
        Template template = templateGateway.queryById(spec.getTemplateId());
        if (template != null) {
            template.incrementSpecNum();
            templateGateway.save(template);
        }
    }

    /**
     * 在删除规格前更新模板的规格数量
     *
     * @param specId 要删除的规格ID
     */
    public void decrementSpecCount(Integer specId) {
        Spec spec = specGateway.queryById(specId);
        if (spec != null) {
            Template template = templateGateway.queryById(spec.getTemplateId());
            if (template != null) {
                template.decrementSpecNum();
                templateGateway.save(template);
            }
        }
    }

    /**
     * 在添加参数后更新模板的参数数量
     *
     * @param para 新添加的参数
     */
    public void incrementParaCount(Para para) {
        Template template = templateGateway.queryById(para.getTemplateId());
        if (template != null) {
            template.incrementParaNum();
            templateGateway.save(template);
        }
    }

    /**
     * 在删除参数前更新模板的参数数量
     *
     * @param paraId 要删除的参数ID
     */
    public void decrementParaCount(Integer paraId) {
        Para para = paraGateway.queryById(paraId);
        if (para != null) {
            Template template = templateGateway.queryById(para.getTemplateId());
            if (template != null) {
                template.decrementParaNum();
                templateGateway.save(template);
            }
        }
    }
}
