package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 删除商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCreateExecutor {

    private final IMerchantGateway merchantGateway;
    private final MerchantConvertor convertor;

    public boolean execute(MerchantCreateCmd cmd) {
        return merchantGateway.save(this.convertor.toMerchantEntity(cmd));
    }

}
