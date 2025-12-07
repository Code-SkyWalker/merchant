package org.dromara.merchant.app.category.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.CategoryDefaultMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;
import org.springframework.stereotype.Component;


/**
 * @Description 分类分页查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryPageQryExecutor {

    private final CategoryDefaultMapper mapper;
    private final CategoryConvertor convertor;

    public Page<CategoryDefaultCO> execute(DefaultCategoryPageQry qry, PageQuery query) {
        Page<CategoryDefaultDO> defaultCategories = mapper.selectPages(qry, query.build());
        return convertor.toCategoryDefaultCOList(defaultCategories);
    }
}
