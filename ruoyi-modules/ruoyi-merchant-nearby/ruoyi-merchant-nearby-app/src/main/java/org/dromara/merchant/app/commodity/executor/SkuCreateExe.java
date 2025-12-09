package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.ISkuGateway;
import org.dromara.merchant.infrastructure.commodity.converter.SkuConvertor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商品sku创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/9 17:19
 */
@Component
@RequiredArgsConstructor
public class SkuCreateExe {

    private final ISkuGateway skuGateway;
    private final SkuConvertor skuConvertor;

    public boolean execute(List<SkuCreateCmd> cmd) {
        return skuGateway.save(skuConvertor.createCmdToEntityList(cmd));
    }

}
