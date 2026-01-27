package org.dromara.merchant.app.merchant.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.Executor;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultModifyCmd;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryDefaultGateway;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 分类修改执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 14:43
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultModifyExecutor implements Executor<MerCategoryDefaultModifyCmd, Boolean> {

    private final IMerCategoryDefaultGateway categoryDefaultGateway;
    private final MerCategoryConvertor convertor;

    @Override
    public Boolean execute(MerCategoryDefaultModifyCmd cmd) {
        return this.categoryDefaultGateway.modify(convertor.toCategoryDefaultEntity(cmd));
    }
}
