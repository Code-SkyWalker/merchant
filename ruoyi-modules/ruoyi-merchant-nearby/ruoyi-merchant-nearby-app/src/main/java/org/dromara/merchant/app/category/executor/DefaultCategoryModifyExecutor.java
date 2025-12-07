package org.dromara.merchant.app.category.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.ICategoryDefaultGateway;
import org.dromara.merchant.infrastructure.merchant.converter.CategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryModifyExecutor implements Executor<DefaultCategoryModifyCmd, Boolean> {

    private final ICategoryDefaultGateway categoryDefaultGateway;
    private final CategoryConvertor convertor;

    @Override
    public Boolean execute(DefaultCategoryModifyCmd cmd) {
        return this.categoryDefaultGateway.modify(convertor.toCategoryDefaultEntity(cmd));
    }
}
