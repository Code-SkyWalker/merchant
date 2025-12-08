package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.dromara.merchant.domain.commodity.model.Template;
import org.dromara.merchant.infrastructure.commodity.converter.TemplateConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 14:59
 */
@Component
@RequiredArgsConstructor
public class TemplateModifyExe {

    private final ITemplateGateway templateGateway;
    private final TemplateConvertor templateConvertor;

    public boolean execute(TemplateModifyCmd cmd) {
        Template template = templateConvertor.toEntity(cmd);
        return templateGateway.save(template);
    }

}
