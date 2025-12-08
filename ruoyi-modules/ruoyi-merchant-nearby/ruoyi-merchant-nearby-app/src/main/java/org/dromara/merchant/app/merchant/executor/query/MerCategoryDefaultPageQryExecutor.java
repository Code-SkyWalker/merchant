package org.dromara.merchant.app.merchant.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryDefaultPageQry;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryDefaultMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;
import org.springframework.stereotype.Component;


/**
 * @Description 分类分页查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultPageQryExecutor {

    private final MerCategoryDefaultMapper mapper;
    private final MerCategoryConvertor convertor;

    public Page<MerCategoryDefaultCO> execute(MerCategoryDefaultPageQry qry, PageQuery query) {
        Page<CategoryDefaultDO> defaultCategories = mapper.selectPages(qry, query.build());
        return convertor.toCategoryDefaultCOList(defaultCategories);
    }
}
