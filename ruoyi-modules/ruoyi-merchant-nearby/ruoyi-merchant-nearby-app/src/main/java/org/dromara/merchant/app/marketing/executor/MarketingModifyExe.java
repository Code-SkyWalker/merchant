package org.dromara.merchant.app.marketing.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 修改营销活动执行器
 * @Author Code Skywalker
 * @Date 2025/12/26 15:10
 */
@Component
@RequiredArgsConstructor
public class MarketingModifyExe implements Executor<MarketingModifyCmd, Boolean> {

    private final IMarketingGateway marketingGateway;
    private final MarketingConvertor marketingConvertor;

    @Override
    public Boolean execute(MarketingModifyCmd command) {
        return this.marketingGateway.save(marketingConvertor.toEntity(command));
    }
}
