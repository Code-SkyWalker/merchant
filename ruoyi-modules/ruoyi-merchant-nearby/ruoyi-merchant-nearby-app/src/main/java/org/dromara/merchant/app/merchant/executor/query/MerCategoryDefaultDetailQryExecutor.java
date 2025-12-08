package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryDefaultMapper;
import org.springframework.stereotype.Component;


/**
 * @Description 分类详情查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultDetailQryExecutor {

    private final MerCategoryDefaultMapper mapper;
    private final MerCategoryConvertor convertor;

    public MerCategoryDefaultCO execute(Long categoryId) {
        return convertor.toCategoryDefaultCO(mapper.selectByPrimaryKey(categoryId));
    }
}
