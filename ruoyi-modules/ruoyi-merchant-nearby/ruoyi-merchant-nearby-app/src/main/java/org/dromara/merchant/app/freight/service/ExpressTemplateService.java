package org.dromara.merchant.app.freight.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.freight.executor.*;
import org.dromara.merchant.app.freight.IExpressTemplateService;
import org.dromara.merchant.app.freight.executor.query.ExpressTemplateDetailQryExecutor;
import org.dromara.merchant.app.freight.executor.query.ExpressTemplatePageQryExecutor;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateModifyCmd;
import org.dromara.merchant.client.freight.dto.data.command.query.ExpressTemplatePageQry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 商户运费模板服务实现
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Service
@RequiredArgsConstructor
public class ExpressTemplateService implements IExpressTemplateService {

    private final ExpressTemplateCreateExecutor createExecutor;
    private final ExpressTemplateDeleteExecutor deleteExecutor;
    private final ExpressTemplateModifyExecutor modifyExecutor;
    private final ExpressTemplateDetailQryExecutor detailQryExecutor;
    private final ExpressTemplatePageQryExecutor pageQryExecutor;
    private final ExpressTemplateSetDefaultExecutor setDefaultExecutor;

    private final ExpressAreaCreateExecutor areaCreateExecutor;
    private final ExpressAreaModifyExecutor areaModifyExecutor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(ExpressTemplateCreateCmd cmd) {

        // 创建商户运费模板
        Long createdTemplateId = createExecutor.execute(cmd);

        // 创建商户配送区域
        areaCreateExecutor.execute(cmd.getAreas(), createdTemplateId);
        return createdTemplateId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modify(ExpressTemplateModifyCmd cmd) {

        // 修改商户配送区域
        boolean modifyArea = this.areaModifyExecutor.execute(cmd.getAreas());

        // 修改商户运费模板
        boolean modifyTemplate = modifyExecutor.execute(cmd);

        // 如果是默认模板，则设置默认运费模板
        if (cmd.getIsDefault()) {
            this.setDefault(cmd.getTemplateId(), cmd.getMerchantId());
        }

        return modifyTemplate && modifyArea;
    }

    @Override
    public boolean delete(Long templateId) {
        return deleteExecutor.execute(templateId);
    }

    @Override
    public boolean setDefault(Long templateId, Long merchantId) {
        return setDefaultExecutor.execute(templateId, merchantId);
    }


    @Override
    public ExpressTemplateCO queryById(Long templateId) {
        return detailQryExecutor.execute(templateId);
    }

    @Override
    public Page<ExpressTemplateCO> queryPage(ExpressTemplatePageQry qry, PageQuery pageQuery) {
        return pageQryExecutor.execute(qry, pageQuery);
    }

}
