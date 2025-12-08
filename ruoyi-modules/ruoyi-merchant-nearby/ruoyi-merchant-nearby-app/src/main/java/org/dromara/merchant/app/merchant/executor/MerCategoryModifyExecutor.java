package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.Executor;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryModifyExecutor implements Executor<MerCategoryModifyCmd, Boolean> {

    private final IMerCategoryGateway categoryGateway;
    private final MerCategoryConvertor convertor;

    @Override
    public Boolean execute(MerCategoryModifyCmd cmd) {
        return this.categoryGateway.modify(convertor.toCategoryEntity(cmd));
    }
}
