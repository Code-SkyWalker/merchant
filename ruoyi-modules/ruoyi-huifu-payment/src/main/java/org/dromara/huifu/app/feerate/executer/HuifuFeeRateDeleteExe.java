package org.dromara.huifu.app.feerate.executer;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.domain.gateway.IHuifuFeeRateGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/14 15:36
 */
@Component
@RequiredArgsConstructor
public class HuifuFeeRateDeleteExe {

    private final IHuifuFeeRateGateway gateway;

    public boolean execute(Long userSubordinate) {
        return this.gateway.delete(userSubordinate);
    }
}
