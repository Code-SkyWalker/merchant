package org.dromara.huifu.app.feerate.service;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.app.feerate.executer.HuifuFeeRateCreateExe;
import org.dromara.huifu.app.feerate.executer.HuifuFeeRateDeleteExe;
import org.dromara.huifu.app.feerate.executer.HuifuFeeRateModifyExe;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateCreateCmd;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateModifyCmd;
import org.dromara.huifu.domain.gateway.IHuifuFeeRateGateway;
import org.dromara.huifu.domain.model.HuifuFeeRate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:27
 */
@Component
@RequiredArgsConstructor
public class HuifuFeeRateService implements IHuifuFeeRateService {

    private final HuifuFeeRateCreateExe createExecutor;
    private final HuifuFeeRateModifyExe modifyExecutor;
    private final HuifuFeeRateDeleteExe deleteExecutor;

    private final IHuifuFeeRateGateway huifuFeeRateGateway;

    @Override
    public boolean create(HuifuFeeRateCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(HuifuFeeRateModifyCmd cmd) {
        return modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long merchantId) {
        return this.deleteExecutor.execute(merchantId);
    }

    @Override
    public List<HuifuFeeRate> queryByMerchantId(Long merchantId) {
        return this.huifuFeeRateGateway.queryByMerchantId(merchantId);
    }
}
