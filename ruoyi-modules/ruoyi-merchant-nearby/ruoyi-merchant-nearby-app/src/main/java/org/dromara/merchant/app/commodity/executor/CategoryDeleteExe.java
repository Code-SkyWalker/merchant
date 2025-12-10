package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ICategoryGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 15:38
 */
@Component
@RequiredArgsConstructor
public class CategoryDeleteExe {

    private final ICategoryGateway categoryGateway;

    public boolean execute(Integer id, Long merchantId) {
        return categoryGateway.delete(id);
    }

}
