package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 删除商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantDeleteExecutor {

    private final IMerchantGateway merchantGateway;

    public boolean execute(Long merchantId) {
        return merchantGateway.deleteById(merchantId);
    }

}