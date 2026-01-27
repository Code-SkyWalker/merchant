package org.dromara.express.app;

import org.dromara.express.client.dto.data.command.ConfigCreateCmd;
import org.dromara.express.client.dto.data.command.ConfigModifyCmd;
import org.dromara.express.domain.model.Config;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 14:18
 */
public interface IConfigService {

    /**
     * 创建配置
     *
     * @param cmd 配置
     * @return 是否创建成功
     */
    boolean createConfig(ConfigCreateCmd cmd);

    /**
     * 修改配置
     *
     * @param cmd 配置
     * @return 是否修改成功
     */
    boolean modifyConfig(ConfigModifyCmd cmd);

    /**
     * 删除配置
     *
     * @param id 主键
     * @return 是否删除成功
     */
    boolean deleteConfig(Long id);

    /**
     * 获取配置
     *
      * @param id 配置ID
     * @return 配置
     */
    Config queryConfigById(Long id);
}
