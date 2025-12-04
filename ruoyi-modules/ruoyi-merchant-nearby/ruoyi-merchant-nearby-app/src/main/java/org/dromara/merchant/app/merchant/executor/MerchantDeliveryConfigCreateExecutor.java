package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantDeliveryConfigGateway;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantDeliveryConfig;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantDeliveryConfigConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:38
 */
@Component
@RequiredArgsConstructor
public class MerchantDeliveryConfigCreateExecutor {

    private final IMerchantDeliveryConfigGateway deliveryConfigGateway;
    private final MerchantDeliveryConfigConvertor configConvertor;

    public boolean execute(MerchantDeliveryConfigCreateCmd cmd) {
        MerchantDeliveryConfig merchantDeliveryConfig = configConvertor.toMerchantDeliveryConfig(cmd);
        return deliveryConfigGateway.save(merchantDeliveryConfig);
    }

}
