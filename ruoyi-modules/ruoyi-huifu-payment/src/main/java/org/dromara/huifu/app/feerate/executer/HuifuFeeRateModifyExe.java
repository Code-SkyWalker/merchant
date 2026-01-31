package org.dromara.huifu.app.feerate.executer;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateModifyCmd;
import org.dromara.huifu.domain.gateway.IHuifuFeeRateGateway;
import org.dromara.huifu.domain.model.HuifuFeeRate;
import org.dromara.huifu.infrastructure.convertor.HuifuFeeRateConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.dromara.huifu.domain.model.HuifuConfig.*;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/14 15:36
 */
@Component
@RequiredArgsConstructor
public class HuifuFeeRateModifyExe {

    private final IHuifuFeeRateGateway gateway;
    private final HuifuFeeRateConvertor convertor;

    public boolean execute(HuifuFeeRateModifyCmd cmd) {

        List<HuifuFeeRate> rates = List.of(
            new HuifuFeeRate(cmd.getMerchantId(), cmd.getMerchantRate(), MERCHANT_TYPE),
            new HuifuFeeRate(cmd.getMerchantId(), cmd.getPlatformRate(), PLATFORM_TYPE),
            new HuifuFeeRate(cmd.getMerchantId(), cmd.getChannelRate(), CHANNEL_TYPE)
        );

        return this.gateway.save(rates, true);
    }

}
