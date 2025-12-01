package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCertifyModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerchantCertifyGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantCertifyConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 修改商户执行器
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantCertifyModifyExecutor {

    private final IMerchantCertifyGateway gateway;
    private final MerchantCertifyConvertor convertor;

    public boolean execute(MerchantCertifyModifyCmd cmd) {
        return this.gateway.save(this.convertor.toMerchantCertifyEntity(cmd));
    }

}
