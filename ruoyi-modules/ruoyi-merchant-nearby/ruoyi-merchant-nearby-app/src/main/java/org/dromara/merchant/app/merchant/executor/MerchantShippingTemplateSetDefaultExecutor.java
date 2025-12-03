package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 设置商户默认运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateSetDefaultExecutor {

    private final IMerchantShippingTemplateGateway merchantShippingTemplateGateway;

    public boolean execute(Long templateId, Long merchantId) {
        // 先取消该商户其他模板的默认状态
        merchantShippingTemplateGateway.cancelDefaultByMerchantId(merchantId);
        // 再设置指定模板为默认
        return merchantShippingTemplateGateway.setDefault(templateId, merchantId);
    }
}