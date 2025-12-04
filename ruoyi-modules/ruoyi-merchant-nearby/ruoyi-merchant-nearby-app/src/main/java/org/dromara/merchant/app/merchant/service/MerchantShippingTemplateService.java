package org.dromara.merchant.app.merchant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.merchant.IMerchantShippingTemplateService;
import org.dromara.merchant.app.merchant.executor.*;
import org.dromara.merchant.app.merchant.executor.query.MerchantShippingTemplateDetailQryExecutor;
import org.dromara.merchant.app.merchant.executor.query.MerchantShippingTemplatePageQryExecutor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.client.merchant.dto.data.command.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 商户运费模板服务实现
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Service
@RequiredArgsConstructor
public class MerchantShippingTemplateService implements IMerchantShippingTemplateService {

    private final MerchantShippingTemplateCreateExecutor createExecutor;
    private final MerchantShippingTemplateDeleteExecutor deleteExecutor;
    private final MerchantShippingTemplateModifyExecutor modifyExecutor;
    private final MerchantShippingTemplateDetailQryExecutor detailQryExecutor;
    private final MerchantShippingTemplatePageQryExecutor pageQryExecutor;
    private final MerchantShippingTemplateSetDefaultExecutor setDefaultExecutor;
    private final MerchantShippingTemplateGetDefaultExecutor getDefaultExecutor;

    private final MerchantShippingAreaCreateExecutor areaCreateExecutor;
    private final MerchantShippingAreaModifyExecutor areaModifyExecutor;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(MerchantShippingTemplateCreateCmd cmd) {

        // 创建商户运费模板
        Long createdTemplateId = createExecutor.execute(cmd);

        // 创建商户配送区域
        areaCreateExecutor.execute(cmd.getAreas(), createdTemplateId);
        return createdTemplateId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modify(MerchantShippingTemplateModifyCmd cmd) {

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
    public MerchantShippingTemplateCO getDefaultByMerchantId(Long merchantId) {
        return getDefaultExecutor.execute(merchantId);
    }

    @Override
    public MerchantShippingTemplateCO queryById(Long templateId) {
        return detailQryExecutor.execute(templateId);
    }

    @Override
    public Page<MerchantShippingTemplateCO> queryPage(MerchantShippingTemplatePageQry qry, PageQuery pageQuery) {
        return pageQryExecutor.execute(qry, pageQuery);
    }

}
