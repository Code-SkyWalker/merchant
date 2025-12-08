package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.service.TemplateConformityService;
import org.dromara.merchant.client.commodity.dto.data.command.SpecCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.dromara.merchant.domain.commodity.model.Spec;
import org.dromara.merchant.infrastructure.commodity.converter.SpecConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 商品规格创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 13:41
 */
@Component
@RequiredArgsConstructor
public class SpecCreateExe {

    private final ISpecGateway specGateway;
    private final SpecConvertor convertor;
    private final TemplateConformityService templateConformityService;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(SpecCreateCmd cmd) {
        Spec spec = convertor.toEntity(cmd);
        boolean result = specGateway.save(spec);
        // 保存成功后更新模板的规格数量
        if (result) {
            templateConformityService.incrementSpecCount(spec);
        }
        return result;
    }

}
