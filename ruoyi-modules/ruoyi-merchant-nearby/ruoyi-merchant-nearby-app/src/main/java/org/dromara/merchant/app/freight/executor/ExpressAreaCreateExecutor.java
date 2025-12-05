package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.command.ExpressAreaCreateCmd;
import org.dromara.merchant.domain.freight.gateway.IExpressAreaGateway;
import org.dromara.merchant.infrastructure.freight.converter.ExpressAreaConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 创建商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaCreateExecutor {

    private final IExpressAreaGateway merchantShippingAreaGateway;
    private final ExpressAreaConvertor convertor;

    public boolean execute(ExpressAreaCreateCmd cmd) {
        return merchantShippingAreaGateway.save(this.convertor.toMerchantShippingAreaEntity(cmd));
    }

    public boolean execute(List<ExpressAreaCreateCmd> cmdList, Long templateId) {
        cmdList.forEach(cmd -> cmd.setTemplateId(templateId));
        return this.execute(cmdList);
    }

    public boolean execute(List<ExpressAreaCreateCmd> cmdList) {
        return merchantShippingAreaGateway.batchSave(this.convertor.createCmdsToMerchantShippingAreaEntityList(cmdList));
    }

}
