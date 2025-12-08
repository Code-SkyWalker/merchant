package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 分类删除执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDeleteExecutor implements Executor<Long, Boolean> {

    private final IMerCategoryGateway categoryGateway;

    @Override
    public Boolean execute(Long categoryId) {
        return this.categoryGateway.delete(categoryId);
    }
}
