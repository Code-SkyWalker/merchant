package org.dromara.merchant.app.category.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryCreateCmd;
import org.dromara.merchant.domain.category.gateway.ICategoryDefaultGateway;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类创建执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryCreateExecutor implements Executor<DefaultCategoryCreateCmd, Boolean> {

    private final ICategoryDefaultGateway categoryDefaultGateway;
    private final CategoryConvertor convertor;

    @Override
    public Boolean execute(DefaultCategoryCreateCmd cmd) {
        return this.categoryDefaultGateway.create(convertor.toCategoryDefaultEntity(cmd));
    }
}
