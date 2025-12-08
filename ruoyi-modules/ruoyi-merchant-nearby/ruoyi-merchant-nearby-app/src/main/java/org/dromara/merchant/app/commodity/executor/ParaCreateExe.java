package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.service.TemplateConformityService;
import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.infrastructure.commodity.converter.ParaConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 商品参数创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 14:09
 */
@Component
@RequiredArgsConstructor
public class ParaCreateExe {

    private final IParaGateway paraGateway;
    private final ParaConvertor paraConvertor;
    private final TemplateConformityService templateConformityService;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(ParaCreateCmd cmd) {
        Para para = paraConvertor.toEntity(cmd);
        boolean result = paraGateway.save(para);
        // 保存成功后更新模板的参数数量
        if (result) {
            templateConformityService.incrementParaCount(para);
        }
        return result;
    }
}
