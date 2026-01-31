package org.dromara.huifu.app.config.executer;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigCreateCmd;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.huifu.infrastructure.convertor.HuifuConfigConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:14
 */
@Component
@RequiredArgsConstructor
public class HuifuConfigCreateExe {

    private final IHuifuConfigGateway configGateway;
    private final HuifuConfigConvertor configConvertor;

    public boolean execute(HuifuConfigCreateCmd cmd) {
        HuifuConfig config = configConvertor.toEntity(cmd);
        return this.configGateway.save(config);
    }


}
