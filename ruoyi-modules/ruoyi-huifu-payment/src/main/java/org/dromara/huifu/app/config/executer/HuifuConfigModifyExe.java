package org.dromara.huifu.app.config.executer;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigModifyCmd;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.dromara.huifu.infrastructure.convertor.HuifuConfigConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:14
 */
@Component
@RequiredArgsConstructor
public class HuifuConfigModifyExe {

    private final IHuifuConfigGateway configGateway;
    private final HuifuConfigConvertor configConvertor;

    public boolean execute(HuifuConfigModifyCmd cmd) {

        return this.configGateway.save(configConvertor.toEntity(cmd));
    }


}
