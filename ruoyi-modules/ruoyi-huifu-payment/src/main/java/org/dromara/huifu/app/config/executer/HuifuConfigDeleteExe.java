package org.dromara.huifu.app.config.executer;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:14
 */
@Component
@RequiredArgsConstructor
public class HuifuConfigDeleteExe {

    private final IHuifuConfigGateway configGateway;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(Long huifuId) {
        return this.configGateway.delete(huifuId);
    }


}
