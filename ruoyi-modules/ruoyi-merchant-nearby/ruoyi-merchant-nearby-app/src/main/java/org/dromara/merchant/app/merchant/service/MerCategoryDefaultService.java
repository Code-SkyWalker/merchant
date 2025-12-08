package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.merchant.IMerCategoryDefaultService;
import org.dromara.merchant.app.merchant.executor.MerCategoryDefaultCreateExecutor;
import org.dromara.merchant.app.merchant.executor.MerCategoryDefaultDeleteExecutor;
import org.dromara.merchant.app.merchant.executor.MerCategoryDefaultModifyExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerCategoryDefaultDetailQryExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerCategoryDefaultPageQryExecutor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryDefaultPageQry;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 16:59
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultService implements IMerCategoryDefaultService {

    private final MerCategoryDefaultCreateExecutor createExecutor;
    private final MerCategoryDefaultDeleteExecutor deleteExecutor;
    private final MerCategoryDefaultDetailQryExecutor detailQryExecutor;
    private final MerCategoryDefaultModifyExecutor modifyExecutor;
    private final MerCategoryDefaultPageQryExecutor pageQryExecutor;

    @Override
    public boolean create(MerCategoryDefaultCreateCmd cmd) {
        return this.createExecutor.execute(cmd);
    }

    @Override
    public boolean modify(MerCategoryDefaultModifyCmd cmd) {
        return this.modifyExecutor.execute(cmd);
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.deleteExecutor.execute(categoryId);
    }

    @Override
    public MerCategoryDefaultCO queryById(Long categoryId) {
        return this.detailQryExecutor.execute(categoryId);
    }

    @Override
    public Page<MerCategoryDefaultCO> queryPage(MerCategoryDefaultPageQry qry, PageQuery page) {
        return this.pageQryExecutor.execute(qry, page);
    }
}
