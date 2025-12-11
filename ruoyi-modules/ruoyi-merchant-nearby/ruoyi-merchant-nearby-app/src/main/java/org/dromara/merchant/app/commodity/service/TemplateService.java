package org.dromara.merchant.app.commodity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.merchant.app.commodity.ITemplateService;
import org.dromara.merchant.app.commodity.executor.TemplateCreateExe;
import org.dromara.merchant.app.commodity.executor.TemplateDeleteExe;
import org.dromara.merchant.app.commodity.executor.TemplateModifyExe;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SkuTempCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.TemplatePageCO;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.TemplateQry;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.dromara.merchant.domain.commodity.model.Template;
import org.dromara.merchant.infrastructure.commodity.mapper.TemplateMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 14:58
 */
@Component
@RequiredArgsConstructor
public class TemplateService implements ITemplateService {

    private final TemplateCreateExe templateCreateExe;
    private final TemplateModifyExe templateModifyExe;
    private final TemplateDeleteExe templateDeleteExe;

    private final ITemplateGateway templateGateway;

    private final TemplateMapper templateMapper;

    @Override
    public boolean create(TemplateCreateCmd cmd) {
        return this.templateCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(TemplateModifyCmd cmd) {
        return this.templateModifyExe.execute(cmd);
    }

    @Override
    public boolean delete(Integer id) {
        return this.templateDeleteExe.execute(id);
    }

    @Override
    public Template queryById(Integer id) {
        return this.templateGateway.queryById(id);
    }

    @Override
    public Page<TemplatePageCO> queryPage(TemplateQry qry, PageQuery page) {
        return templateMapper.selectPage(page.build(), qry);
    }

    @Override
    public List<SkuTempCO> queryByTemplateId(Integer templateId) {
        return this.templateMapper.selectById(templateId);
    }
}
