package org.dromara.merchant.app.category.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.category.mapper.CategoryMapper;
import org.springframework.stereotype.Component;


/**
 * @Description 分类详情查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class CategoryDetailQryExecutor {

    private final CategoryMapper mapper;
    private final CategoryConvertor convertor;

    public CategoryCO execute(Long categoryId) {
        return convertor.toCategoryCO(mapper.selectByPrimaryKey(categoryId));
    }
}
