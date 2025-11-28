package org.dromara.merchant.app.category.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.domain.category.gateway.ICategoryGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 分类删除执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryDeleteExecutor implements Executor<Long, Boolean> {

    private final ICategoryGateway categoryGateway;

    @Override
    public Boolean execute(Long categoryId) {
        return this.categoryGateway.delete(categoryId);
    }
}
