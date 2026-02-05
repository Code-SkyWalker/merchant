package org.dromara.huifu.app.config.service;

import com.huifu.bspay.sdk.opps.core.BasePay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.huifu.app.config.executer.HuifuConfigCreateExe;
import org.dromara.huifu.app.config.executer.HuifuConfigDeleteExe;
import org.dromara.huifu.app.config.executer.HuifuConfigModifyExe;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigCreateCmd;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigModifyCmd;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.huifu.infrastructure.convertor.HuifuConfigConvertor;
import org.springframework.stereotype.Component;

import static org.dromara.huifu.domain.model.HuifuConfig.CHANNEL_TYPE;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HuifuConfigService implements IHuifuConfigService {

    private final HuifuConfigCreateExe createExecutor;
    private final HuifuConfigModifyExe modifyExecutor;
    private final HuifuConfigDeleteExe deleteExecutor;

    private final IHuifuConfigGateway huifuConfigGateway;
    private final HuifuConfigConvertor huifuConfigConvertor;


    @Override
    public boolean create(HuifuConfigCreateCmd cmd) {
        boolean created = createExecutor.execute(cmd);
        try {
            if (cmd.getType().equals(CHANNEL_TYPE)) {
                BasePay.initWithMerConfig(huifuConfigConvertor.toConfig(cmd));
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return created;
    }

    @Override
    public boolean modify(HuifuConfigModifyCmd cmd) {
        boolean modified = modifyExecutor.execute(cmd);
        try {
            if (cmd.getType().equals(CHANNEL_TYPE)) {
                BasePay.initWithMerConfig(huifuConfigConvertor.toConfig(cmd));
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return modified;
    }

    @Override
    public boolean delete(Long huifuId) {
        return this.deleteExecutor.execute(huifuId);
    }

    @Override
    public HuifuConfig queryByTenantId(String tenantId) {
        return this.huifuConfigGateway.queryByTenantId(tenantId);
    }

    @Override
    public HuifuConfig queryByMerchantId(Long merchantId) {
        return this.huifuConfigGateway.queryByMerchantId(merchantId);
    }

    @Override
    public HuifuConfig queryChannelConfig() {
        return this.huifuConfigGateway.queryChannelConfig();
    }

}
