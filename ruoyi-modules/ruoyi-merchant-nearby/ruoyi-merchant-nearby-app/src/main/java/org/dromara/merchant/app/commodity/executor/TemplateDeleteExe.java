package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ITemplateGateway;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 15:02
 */
@Component
@RequiredArgsConstructor
public class TemplateDeleteExe {

    private final ITemplateGateway templateGateway;

    public boolean execute(Integer id) {
        return templateGateway.delete(id);
    }
}
