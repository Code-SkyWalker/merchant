package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 修改商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyDeleteExecutor {

    private final IMerchantCertifyGateway gateway;

    public boolean execute(Long approvalId) {
           return this.gateway.deleteByPrimaryKey(approvalId);
    }

}
