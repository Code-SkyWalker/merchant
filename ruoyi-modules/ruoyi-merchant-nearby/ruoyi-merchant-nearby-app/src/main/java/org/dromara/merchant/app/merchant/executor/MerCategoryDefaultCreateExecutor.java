package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultCreateCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryDefaultGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类创建执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultCreateExecutor implements Executor<MerCategoryDefaultCreateCmd, Boolean> {

    private final IMerCategoryDefaultGateway categoryDefaultGateway;
    private final MerCategoryConvertor convertor;

    @Override
    public Boolean execute(MerCategoryDefaultCreateCmd cmd) {
        return this.categoryDefaultGateway.create(convertor.toCategoryDefaultEntity(cmd));
    }
}
