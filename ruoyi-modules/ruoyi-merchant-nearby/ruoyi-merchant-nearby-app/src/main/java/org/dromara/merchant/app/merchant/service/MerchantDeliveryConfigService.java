package org.dromara.merchant.app.merchant.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.merchant.IMerchantDeliveryConfigService;
import org.dromara.merchant.app.merchant.executor.MerchantDeliveryConfigCreateExecutor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:43
 */
@Component
@RequiredArgsConstructor
public class MerchantDeliveryConfigService implements IMerchantDeliveryConfigService {

    private final MerchantDeliveryConfigCreateExecutor createExecutor;

    @Override
    public boolean create(MerchantDeliveryConfigCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }
}
