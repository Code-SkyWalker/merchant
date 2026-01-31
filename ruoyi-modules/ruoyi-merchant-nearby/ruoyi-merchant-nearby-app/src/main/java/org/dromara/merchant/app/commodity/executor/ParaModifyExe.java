package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.IParaGateway;
import org.dromara.merchant.domain.commodity.model.Para;
import org.dromara.merchant.infrastructure.commodity.converter.ParaConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 14:09
 */
@Component
@RequiredArgsConstructor
public class ParaModifyExe {

    private final IParaGateway paraGateway;
    private final ParaConvertor paraConvertor;

    public boolean execute(ParaModifyCmd cmd) {
        Para para = paraConvertor.toEntity(cmd);
        return paraGateway.save(para);
    }
}
