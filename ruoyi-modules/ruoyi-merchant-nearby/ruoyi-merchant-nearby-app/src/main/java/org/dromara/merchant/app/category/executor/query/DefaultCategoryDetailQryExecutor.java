package org.dromara.merchant.app.category.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.category.mapper.CategoryDefaultMapper;
import org.springframework.stereotype.Component;


/**
 * @Description 分类详情查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryDetailQryExecutor {

    private final CategoryDefaultMapper mapper;
    private final CategoryConvertor convertor;

    public CategoryDefaultCO execute(Long categoryId) {
        return convertor.toCategoryDefaultCO(mapper.selectByPrimaryKey(categoryId));
    }
}
