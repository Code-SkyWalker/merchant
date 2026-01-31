package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.merchant.IMerCategoryService;
import org.dromara.merchant.app.merchant.executor.MerCategoryCreateExecutor;
import org.dromara.merchant.app.merchant.executor.MerCategoryDefaultWriteExecutor;
import org.dromara.merchant.app.merchant.executor.MerCategoryDeleteExecutor;
import org.dromara.merchant.app.merchant.executor.MerCategoryModifyExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerCategoryDetailQryExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerCategoryPageQryExecutor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 16:15
 */
@Component
@RequiredArgsConstructor
public class MerCategoryService implements IMerCategoryService {

    private final MerCategoryPageQryExecutor pageQryExecutor;
    private final MerCategoryModifyExecutor modifyExecutor;
    private final MerCategoryDeleteExecutor deleteExecutor;
    private final MerCategoryDetailQryExecutor detailQryExecutor;
    private final MerCategoryCreateExecutor createExecutor;
    private final MerCategoryDefaultWriteExecutor writeExecutor;


    @Override
    public boolean create(MerCategoryCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(MerCategoryModifyCmd cmd) {
        return modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.deleteExecutor.execute(categoryId);
    }

    @Override
    public MerCategoryCO queryById(Long categoryId) {
        return this.detailQryExecutor.execute(categoryId);
    }

    @Override
    public Page<MerCategoryCO> queryPage(MerCategoryPageQry qry, PageQuery query) {
        Page<MerCategoryCO> categoryCOs = this.pageQryExecutor.execute(qry, query);
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
