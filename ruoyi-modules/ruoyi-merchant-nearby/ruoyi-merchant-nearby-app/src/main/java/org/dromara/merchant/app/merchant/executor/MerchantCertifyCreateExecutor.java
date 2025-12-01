package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 创建商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyCreateExecutor {

    private final IMerchantCertifyGateway gateway;
    private final MerchantCertifyConvertor convertor;

    public boolean execute(MerchantCertifyCreateCmd cmd) {
        return gateway.save(this.convertor.toMerchantCertifyEntity(cmd));
    }

}
