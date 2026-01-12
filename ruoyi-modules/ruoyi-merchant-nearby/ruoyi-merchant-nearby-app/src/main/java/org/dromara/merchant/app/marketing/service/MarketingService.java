package org.dromara.merchant.app.marketing.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.marketing.IMarketingService;
import org.dromara.merchant.app.marketing.executor.MarketingCreateExe;
import org.dromara.merchant.app.marketing.executor.MarketingDeleteExe;
import org.dromara.merchant.app.marketing.executor.MarketingModifyExe;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/26 15:15
 */
@Component
@RequiredArgsConstructor
public class MarketingService implements IMarketingService {

    private final MarketingCreateExe marketingCreateExe;
    private final MarketingModifyExe marketingModifyExe;
    private final MarketingDeleteExe marketingDeleteExe;

    private final IMarketingGateway marketingGateway;


    @Override
    public boolean create(MarketingCreateCmd cmd) {
        return this.marketingCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(MarketingModifyCmd cmd) {
        return this.marketingModifyExe.execute(cmd);
    }

    @Override
    public boolean delete(Long id) {
        return this.marketingDeleteExe.execute(id);
    }

    @Override
    public Marketing queryByMarketingId(Long id) {
        return this.marketingGateway.queryById(id);
    }

}
