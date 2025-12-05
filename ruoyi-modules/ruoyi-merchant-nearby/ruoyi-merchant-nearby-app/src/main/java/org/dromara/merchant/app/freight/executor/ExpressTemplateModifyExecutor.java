package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateModifyCmd;
import org.dromara.merchant.domain.freight.gateway.IExpressTemplateGateway;
import org.dromara.merchant.infrastructure.freight.converter.ExpressTemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 修改商户运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplateModifyExecutor {

    private final IExpressTemplateGateway merchantShippingTemplateGateway;
    private final ExpressTemplateConvertor convertor;

    public boolean execute(ExpressTemplateModifyCmd cmd) {
        return merchantShippingTemplateGateway.save(this.convertor.toMerchantShippingTemplateEntity(cmd));
    }

}
