package org.dromara.merchant.app.freight.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.freight.gateway.IExpressTemplateGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 设置商户默认运费模板执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:30
 */
@Component
@RequiredArgsConstructor
public class ExpressTemplateSetDefaultExecutor {

    private final IExpressTemplateGateway expressTemplateGateway;

    public boolean execute(Long templateId, Long merchantId) {
        return expressTemplateGateway.setDefault(templateId, merchantId);
    }
}
