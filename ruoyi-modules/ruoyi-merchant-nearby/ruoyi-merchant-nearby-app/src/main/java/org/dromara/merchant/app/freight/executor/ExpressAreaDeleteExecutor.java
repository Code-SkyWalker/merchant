package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.freight.gateway.IExpressAreaGateway;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 删除商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaDeleteExecutor {

    private final IExpressAreaGateway merchantShippingAreaGateway;

    public boolean execute(Long areaId) {
        return merchantShippingAreaGateway.deleteById(areaId);
    }

    public boolean execute(List<Long> areaIds) {
        return merchantShippingAreaGateway.deleteByIds(areaIds);
    }

}
