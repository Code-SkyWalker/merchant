package org.dromara.merchant.app.marketing.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.client.marketing.dto.data.command.MarketingModifyCmd;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.gateway.IMarketingSpuGateway;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingConvertor;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingSpuConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    private final IMarketingSpuGateway marketingSpuGateway;
    private final MarketingSpuConvertor marketingSpuConvertor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean execute(MarketingModifyCmd command) {

        // 营销活动保存
        Long marketingId = this.marketingGateway.save(marketingConvertor.toEntity(command));

        // 营销活动与商品关联保存
        command.getMarketingSpus().forEach(marketingSpu -> marketingSpu.setMarketingId(marketingId));

        return this.marketingSpuGateway.save(marketingSpuConvertor.cmdToEntity(command.getMarketingSpus()));
    }
}
