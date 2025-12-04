package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantShippingTemplate;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 获取商户默认运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateGetDefaultExecutor {

    private final IMerchantShippingTemplateGateway merchantShippingTemplateGateway;
    private final MerchantShippingTemplateConvertor convertor;

    public MerchantShippingTemplateCO execute(Long merchantId) {
        MerchantShippingTemplate defaultByMerchantId = merchantShippingTemplateGateway.getDefaultByMerchantId(merchantId);
        return convertor.toMerchantShippingTemplateCO(defaultByMerchantId);
    }
}
