package org.dromara.express.app.excutor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.express.domain.gateway.IConfigGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 14:22
 */
@Component
@RequiredArgsConstructor
public class ConfigDeleteExe implements Executor<Long, Boolean> {

    private final IConfigGateway configGateway;

    /**
     * 执行命令
     *
     * @param id
     */
    @Override
    public Boolean execute(Long id) {
        return this.configGateway.deleteById(id);
    }
}
