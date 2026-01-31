package org.dromara.express.app.excutor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.express.client.dto.data.command.ConfigModifyCmd;
import org.dromara.express.domain.gateway.IConfigGateway;
import org.dromara.express.infrastructure.convert.ConfigConverter;
import org.springframework.stereotype.Component;

/**
 * @Description 配置修改执行器
 * @Author Code Skywalker
 * @Date 2026/1/22 14:10
 */
@Component
@RequiredArgsConstructor
public class ConfigModifyExe implements Executor<ConfigModifyCmd, Boolean> {

    private final IConfigGateway configGateway;
    private final ConfigConverter configConverter;

    /**
     * 执行命令
     *
     * @param command 配置修改命令
     */
    @Override
    public Boolean execute(ConfigModifyCmd command) {
        return this.configGateway.save(this.configConverter.toEntity(command));
    }
}
