package org.dromara.merchant.app.merchant.executor.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryMapper;
import org.springframework.stereotype.Component;

/**
 * @Description 分类分页查询执行器
 * @Author Code Skywalker
 * @Date 2025-11-03 15:40
 */
@Component
@RequiredArgsConstructor
public class MerCategoryPageQryExecutor {

    private final MerCategoryMapper mapper;
    private final MerCategoryConvertor convertor;

    public Page<MerCategoryCO> execute(MerCategoryPageQry qry, PageQuery query) {
        return convertor.toCategoryCOPage(mapper.selectPages(qry, query.build()));
    }
}
