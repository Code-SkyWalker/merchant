package org.dromara.express.app.service;

import lombok.RequiredArgsConstructor;
import org.dromara.express.app.IConfigService;
import org.dromara.express.app.excutor.ConfigCreateExe;
import org.dromara.express.app.excutor.ConfigDeleteExe;
import org.dromara.express.app.excutor.ConfigModifyExe;
import org.dromara.express.client.dto.data.command.ConfigCreateCmd;
import org.dromara.express.client.dto.data.command.ConfigModifyCmd;
import org.dromara.express.domain.gateway.IConfigGateway;
import org.dromara.express.domain.model.Config;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 14:21
 */
@Component
@RequiredArgsConstructor
public class ConfigService implements IConfigService {

    private final ConfigCreateExe configCreateExe;
    private final ConfigModifyExe configModifyExe;
    private final ConfigDeleteExe configDeleteExe;

    private final IConfigGateway configGateway;


    /**
     * 创建配置
     *
     * @param cmd 配置
     * @return 是否创建成功
     */
    @Override
    public boolean createConfig(ConfigCreateCmd cmd) {
        return this.configCreateExe.execute(cmd);
    }

    /**
     * 修改配置
     *
     * @param cmd 配置
     * @return 是否修改成功
     */
    @Override
    public boolean modifyConfig(ConfigModifyCmd cmd) {
        return this.configModifyExe.execute(cmd);
    }

    /**
     * 删除配置
     *
     * @param id 配置ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteConfig(Long id) {
        return this.configDeleteExe.execute(id);
    }

    /**
     * 获取配置
     *
     * @param id 配置ID
     * @return 配置
     */
    @Override
    public Config queryConfigById(Long id) {
        return this.configGateway.queryById(id);
    }
}
