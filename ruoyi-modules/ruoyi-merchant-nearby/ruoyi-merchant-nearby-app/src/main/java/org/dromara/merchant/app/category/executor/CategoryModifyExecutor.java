package org.dromara.merchant.app.category.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.category.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.domain.category.gateway.ICategoryGateway;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class CategoryModifyExecutor implements Executor<CategoryModifyCmd, Boolean> {

    private final ICategoryGateway categoryGateway;
    private final CategoryConvertor convertor;

    @Override
    public Boolean execute(CategoryModifyCmd cmd) {
        return this.categoryGateway.modify(convertor.toCategoryEntity(cmd));
    }
}
