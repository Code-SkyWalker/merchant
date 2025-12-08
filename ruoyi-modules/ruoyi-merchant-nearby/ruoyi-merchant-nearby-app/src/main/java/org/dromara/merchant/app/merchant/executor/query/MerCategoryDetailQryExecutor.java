package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryMapper;
import org.springframework.stereotype.Component;


/**
 * @Description 分类详情查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDetailQryExecutor {

    private final MerCategoryMapper mapper;
    private final MerCategoryConvertor convertor;

    public MerCategoryCO execute(Long categoryId) {
        return convertor.toCategoryCO(mapper.selectByPrimaryKey(categoryId));
    }
}
