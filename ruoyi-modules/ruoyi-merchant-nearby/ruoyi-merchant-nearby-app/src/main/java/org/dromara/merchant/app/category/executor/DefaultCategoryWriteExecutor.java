package org.dromara.merchant.app.category.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.ICategoryDefaultGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryWriteExecutor {

    private final ICategoryDefaultGateway categoryDefaultGateway;

    public Boolean execute() {
        return this.categoryDefaultGateway.writeDefault();
    }
}
