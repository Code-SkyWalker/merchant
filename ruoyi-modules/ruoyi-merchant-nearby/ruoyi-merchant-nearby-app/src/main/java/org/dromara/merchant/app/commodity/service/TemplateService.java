package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ITemplateService;
import org.dromara.merchant.app.commodity.executor.TemplateCreateExe;
import org.dromara.merchant.app.commodity.executor.TemplateDeleteExe;
import org.dromara.merchant.app.commodity.executor.TemplateModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.dromara.merchant.domain.commodity.model.Template;
import org.springframework.stereotype.Component;

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
}
