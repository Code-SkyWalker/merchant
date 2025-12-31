package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.gateway.IFreightConfigGateway;
import org.dromara.merchant.domain.freight.model.FreightConfig;
import org.dromara.merchant.infrastructure.freight.converter.FreightConfigConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:38
 */
@Component
@RequiredArgsConstructor
public class FreightConfigCreateExecutor {

    private final IFreightConfigGateway deliveryConfigGateway;
    private final FreightConfigConvertor configConvertor;

    public Long execute(FreightConfigCreateCmd cmd) {
        FreightConfig freightConfig = configConvertor.toMerchantDeliveryConfig(cmd);
        boolean save = deliveryConfigGateway.save(freightConfig);
        return save ? freightConfig.getDeliveryId() : null;
    }

}
