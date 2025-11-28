package org.dromara.merchant.app.category.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.client.category.dto.data.command.CategoryPageQry;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.category.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 分类分页查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class CategoryPageQryExecutor {

    private final CategoryMapper mapper;
    private final CategoryConvertor convertor;

    public Page<CategoryCO> execute(CategoryPageQry qry, PageQuery query) {
        return convertor.toCategoryCOPage(mapper.selectPages(qry, query.build()));
    }
}
