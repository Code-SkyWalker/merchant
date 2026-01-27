package org.dromara.merchant.app.address.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.domain.address.gateway.IAddressGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 16:48
 */
@Component
@RequiredArgsConstructor
public class AddressDeleteExe implements Executor<Long, Boolean> {

    private final IAddressGateway addressGateway;

    /**
     * 执行命令
     *
     * @param id 用户地址Id
     */
    @Override
    public Boolean execute(Long id) {
        return this.addressGateway.delete(id);
    }
}
