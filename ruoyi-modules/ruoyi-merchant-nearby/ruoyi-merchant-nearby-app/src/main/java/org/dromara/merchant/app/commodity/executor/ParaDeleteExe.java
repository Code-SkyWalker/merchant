package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.service.TemplateConformityService;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 商品参数删除执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 14:09
 */
@Component
@RequiredArgsConstructor
public class ParaDeleteExe {

    private final IParaGateway paraGateway;
    private final TemplateConformityService templateConformityService;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(Integer id) {
        // 删除前先更新模板的参数数量
        templateConformityService.decrementParaCount(id);
        return paraGateway.delete(id);
    }
}
