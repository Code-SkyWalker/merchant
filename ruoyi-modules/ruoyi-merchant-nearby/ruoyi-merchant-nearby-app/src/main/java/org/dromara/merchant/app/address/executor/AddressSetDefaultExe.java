package org.dromara.merchant.app.address.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.satoken.utils.LoginHelper;
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
public class AddressSetDefaultExe implements Executor<Long, Boolean> {

    private final IAddressGateway addressGateway;

    /**
     * 执行命令
     *
     * @param id 命令
     */
    @Override
    public Boolean execute(Long id) {
        return this.addressGateway.setDefault(id, LoginHelper.getUserId());
    }
}
