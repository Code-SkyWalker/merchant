package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateCreateCmd;
import org.dromara.merchant.domain.freight.gateway.IExpressTemplateGateway;
import org.dromara.merchant.domain.freight.model.ExpressTemplate;
import org.dromara.merchant.infrastructure.freight.converter.ExpressTemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 创建商户运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplateCreateExecutor {

    private final IExpressTemplateGateway merchantShippingTemplateGateway;
    private final ExpressTemplateConvertor convertor;

    public Long execute(ExpressTemplateCreateCmd cmd) {
        ExpressTemplate expressTemplate = this.convertor.toMerchantShippingTemplateEntity(cmd);
        merchantShippingTemplateGateway.save(expressTemplate);
        return expressTemplate.getTemplateId();
    }

}
