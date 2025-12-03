package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 删除商户运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateDeleteExecutor {

    private final IMerchantShippingTemplateGateway merchantShippingTemplateGateway;

    public boolean execute(Long templateId) {
        return merchantShippingTemplateGateway.deleteById(templateId);
    }

}