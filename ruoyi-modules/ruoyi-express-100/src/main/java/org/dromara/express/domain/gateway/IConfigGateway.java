package org.dromara.express.domain.gateway;

import org.dromara.express.domain.model.Config;

/**
 * 快递配置仓储接口
 */
public interface IConfigGateway {

    /**
     * 保存快递配置
     */
    boolean save(Config config);

    /**
     * 根据ID删除快递配置
     */
    boolean deleteById(Long id);

    /**
     * 根据ID获取快递配置
     */
    Config queryById(Long id);

}
