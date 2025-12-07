package org.dromara.merchant.app.category.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.category.IDefaultCategoryService;
import org.dromara.merchant.app.category.executor.DefaultCategoryCreateExecutor;
import org.dromara.merchant.app.category.executor.DefaultCategoryDeleteExecutor;
import org.dromara.merchant.app.category.executor.DefaultCategoryModifyExecutor;
import org.dromara.merchant.app.category.executor.query.DefaultCategoryDetailQryExecutor;
import org.dromara.merchant.app.category.executor.query.DefaultCategoryPageQryExecutor;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryPageQry;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 16:59
 */
@Component
@RequiredArgsConstructor
public class DefaultCategoryService implements IDefaultCategoryService {

    private final DefaultCategoryCreateExecutor createExecutor;
    private final DefaultCategoryDeleteExecutor deleteExecutor;
    private final DefaultCategoryDetailQryExecutor detailQryExecutor;
    private final DefaultCategoryModifyExecutor modifyExecutor;
    private final DefaultCategoryPageQryExecutor pageQryExecutor;

    @Override
    public boolean create(DefaultCategoryCreateCmd cmd) {
        return this.createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(DefaultCategoryModifyCmd cmd) {
        return this.modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.deleteExecutor.execute(categoryId);
    }

    @Override
    public CategoryDefaultCO queryById(Long categoryId) {
        return this.detailQryExecutor.execute(categoryId);
    }

    @Override
    public Page<CategoryDefaultCO> queryPage(DefaultCategoryPageQry qry, PageQuery page) {
        return this.pageQryExecutor.execute(qry, page);
    }
}
