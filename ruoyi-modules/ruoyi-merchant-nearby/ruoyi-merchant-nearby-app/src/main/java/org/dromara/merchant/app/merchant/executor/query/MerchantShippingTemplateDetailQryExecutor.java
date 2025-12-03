package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingTemplateMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingTemplateDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户运费模板详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateDetailQryExecutor {

    private final MerchantShippingTemplateMapper mapper;
    private final MerchantShippingTemplateConvertor convertor;

    public MerchantShippingTemplateCO execute(Long templateId) {
        MerchantShippingTemplateDO template = mapper.selectByPrimaryKey(templateId);
        return this.convertor.toMerchantShippingTemplateCO(template);
    }

}