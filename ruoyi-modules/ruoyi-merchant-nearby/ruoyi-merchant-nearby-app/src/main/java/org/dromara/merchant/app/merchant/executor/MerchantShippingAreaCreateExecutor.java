package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingAreaGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingAreaConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 创建商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaCreateExecutor {

    private final IMerchantShippingAreaGateway merchantShippingAreaGateway;
    private final MerchantShippingAreaConvertor convertor;

    public boolean execute(MerchantShippingAreaCreateCmd cmd) {
        return merchantShippingAreaGateway.save(this.convertor.toMerchantShippingAreaEntity(cmd));
    }

    public boolean execute(List<MerchantShippingAreaCreateCmd> cmdList, Long templateId) {
        cmdList.forEach(cmd -> cmd.setTemplateId(templateId));
        return this.execute(cmdList);
    }

    public boolean execute(List<MerchantShippingAreaCreateCmd> cmdList) {
        return merchantShippingAreaGateway.batchSave(this.convertor.createCmdsToMerchantShippingAreaEntityList(cmdList));
    }

}
