package org.dromara.merchant.app.category.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.category.ICategoryService;
import org.dromara.merchant.app.category.executor.*;
import org.dromara.merchant.app.category.executor.query.CategoryDetailQryExecutor;
import org.dromara.merchant.app.category.executor.query.CategoryPageQryExecutor;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.client.category.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryPageQry;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 16:15
 */
@Component
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryPageQryExecutor pageQryExecutor;
    private final CategoryModifyExecutor modifyExecutor;
    private final CategoryDeleteExecutor deleteExecutor;
    private final CategoryDetailQryExecutor detailQryExecutor;
    private final CategoryCreateExecutor createExecutor;
    private final DefaultCategoryWriteExecutor writeExecutor;


    @Override
    public boolean create(CategoryCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(CategoryModifyCmd cmd) {
        return modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.deleteExecutor.execute(categoryId);
    }

    @Override
    public CategoryCO queryById(Long categoryId) {
        return this.detailQryExecutor.execute(categoryId);
    }

    @Override
    public Page<CategoryCO> queryPage(CategoryPageQry qry, PageQuery query) {
        Page<CategoryCO> categoryCOs = this.pageQryExecutor.execute(qry, query);
        if (categoryCOs.getRecords().isEmpty()) {
            this.writeDefaultCategory();
            categoryCOs = this.pageQryExecutor.execute(qry, query);
        }
        return categoryCOs;
    }

    public boolean writeDefaultCategory() {
        return writeExecutor.execute();
    }
}
