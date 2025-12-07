package org.dromara.merchant.app.freight.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.infrastructure.freight.converter.ExpressTemplateConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressTemplateDO;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressTemplateMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplateDetailQryExecutor {

    private final ExpressTemplateMapper mapper;
    private final ExpressTemplateConvertor convertor;

    public ExpressTemplateCO execute(Long templateId) {
        ExpressTemplateDO template = mapper.selectByPrimaryKey(templateId);
        return this.convertor.toMerchantShippingTemplateCO(template);
    }

}
