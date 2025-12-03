package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingTemplateGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingTemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 修改商户运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingTemplateModifyExecutor {

    private final IMerchantShippingTemplateGateway merchantShippingTemplateGateway;
    private final MerchantShippingTemplateConvertor convertor;

    public boolean execute(MerchantShippingTemplateModifyCmd cmd) {
        return merchantShippingTemplateGateway.save(this.convertor.toMerchantShippingTemplateEntity(cmd));
    }

}