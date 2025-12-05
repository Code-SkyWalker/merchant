package org.dromara.merchant.app.freight.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.freight.IFreightConfigService;
import org.dromara.merchant.app.freight.executor.FreightConfigCreateExecutor;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:43
 */
@Component
@RequiredArgsConstructor
public class FreightConfigService implements IFreightConfigService {

    private final FreightConfigCreateExecutor createExecutor;

    @Override
    public boolean create(FreightConfigCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }
}
