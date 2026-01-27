package org.dromara.merchant.app.marketing.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.gateway.IMarketingSpuGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 删除营销活动执行器
 * @Author Code Skywalker
 * @Date 2025/12/26 15:10
 */
@Component
@RequiredArgsConstructor
public class MarketingDeleteExe implements Executor<Long, Boolean> {

    private final IMarketingGateway marketingGateway;
    private final IMarketingSpuGateway marketingSpuGateway;

    @Override
    public Boolean execute(Long id) {
        // 删除营销活动关联商品
        boolean marketingSpuDeleted = this.marketingSpuGateway.deleteByMarketingId(id);

        // 删除营销活动
        boolean marketingDeleted = this.marketingGateway.delete(id);
        return marketingDeleted && marketingSpuDeleted;
    }
}
