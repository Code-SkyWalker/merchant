package org.dromara.merchant.app.address.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.client.address.dto.data.command.AddressModifyCmd;
import org.dromara.merchant.domain.address.gateway.IAddressGateway;
import org.dromara.merchant.infrastructure.address.converter.AddressConverter;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/16 16:48
 */
@Component
@RequiredArgsConstructor
public class AddressModifyExe implements Executor<AddressModifyCmd, Boolean> {

    private final IAddressGateway addressGateway;

    private final AddressConverter addressConverter;

    /**
     * 执行命令
     *
     * @param command 命令
     */
    @Override
    public Boolean execute(AddressModifyCmd command) {
        return this.addressGateway.save(this.addressConverter.toEntity(command));
    }
}
