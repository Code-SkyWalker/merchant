package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryGateway;
import org.dromara.merchant.domain.merchant.model.MerCategory;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类创建执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryCreateExecutor implements Executor<MerCategoryCreateCmd, Boolean> {

    private final IMerCategoryGateway categoryGateway;
    private final MerCategoryConvertor convertor;

    @Override
    public Boolean execute(MerCategoryCreateCmd cmd) {
        MerCategory merCategoryEntity = convertor.toCategoryEntity(cmd);
        return this.categoryGateway.create(merCategoryEntity);
    }
}
