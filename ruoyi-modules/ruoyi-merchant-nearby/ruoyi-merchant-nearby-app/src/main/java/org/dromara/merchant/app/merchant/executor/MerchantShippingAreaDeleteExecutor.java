package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingAreaGateway;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 删除商户配送区域执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaDeleteExecutor {

    private final IMerchantShippingAreaGateway merchantShippingAreaGateway;

    public boolean execute(Long areaId) {
        return merchantShippingAreaGateway.deleteById(areaId);
    }

    public boolean execute(List<Long> areaIds) {
        return merchantShippingAreaGateway.deleteByIds(areaIds);
    }

}
