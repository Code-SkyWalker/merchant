package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.service.TemplateConformityService;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 商品规格删除执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 13:41
 */
@Component
@RequiredArgsConstructor
public class SpecDeleteExe {

    private final ISpecGateway specGateway;
    private final TemplateConformityService templateConformityService;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(Integer id) {
        // 删除前先更新模板的规格数量
        templateConformityService.decrementSpecCount(id);
        return specGateway.delete(id);
    }
}
