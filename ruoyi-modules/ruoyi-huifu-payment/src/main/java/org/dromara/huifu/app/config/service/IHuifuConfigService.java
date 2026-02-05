package org.dromara.huifu.app.config.service;

import org.dromara.huifu.client.config.dto.cmd.HuifuConfigCreateCmd;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigModifyCmd;
import org.dromara.huifu.domain.model.HuifuConfig;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:34
 */
public interface IHuifuConfigService {

    /**
     * 创建汇付配置
     */
    boolean create(HuifuConfigCreateCmd cmd);

    /**
     * 修改汇付配置
     */
    boolean modify(HuifuConfigModifyCmd cmd);

    /**
     * 删除汇付配置
     */
    boolean delete(Long huifuId);

    /**
     * 根据租户编号查询
     */
    HuifuConfig queryByTenantId(String tenantId);

    /**
     * 根据商户ID查询
     */
    HuifuConfig queryByMerchantId(Long merchantId);

    /**
     * 查询 汇付渠道商配置
     */
    HuifuConfig queryChannelConfig();

}
