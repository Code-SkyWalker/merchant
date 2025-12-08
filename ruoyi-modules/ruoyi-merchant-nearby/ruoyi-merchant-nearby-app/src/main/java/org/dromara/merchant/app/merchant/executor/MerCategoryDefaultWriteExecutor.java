package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryDefaultGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultWriteExecutor {

    private final IMerCategoryDefaultGateway categoryDefaultGateway;

    public Boolean execute() {
        return this.categoryDefaultGateway.writeDefault();
    }
}
