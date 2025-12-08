package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.SpecModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ISpecGateway;
import org.dromara.merchant.infrastructure.commodity.converter.SpecConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 商品规格修改执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 13:41
 */
@Component
@RequiredArgsConstructor
public class SpecModifyExe {

    private final ISpecGateway specGateway;
    private final SpecConvertor convertor;

    public boolean execute(SpecModifyCmd cmd) {
        return specGateway.save(convertor.toEntity(cmd));
    }
}
