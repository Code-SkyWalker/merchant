package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.dromara.merchant.domain.merchant.model.MerchantShippingTemplate;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 创建商户运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateCreateExecutor {

    private final IMerchantShippingTemplateGateway merchantShippingTemplateGateway;
    private final MerchantShippingTemplateConvertor convertor;

    public Long execute(MerchantShippingTemplateCreateCmd cmd) {
        MerchantShippingTemplate merchantShippingTemplate = this.convertor.toMerchantShippingTemplateEntity(cmd);
        merchantShippingTemplateGateway.save(merchantShippingTemplate);
        return merchantShippingTemplate.getTemplateId();
    }

}
