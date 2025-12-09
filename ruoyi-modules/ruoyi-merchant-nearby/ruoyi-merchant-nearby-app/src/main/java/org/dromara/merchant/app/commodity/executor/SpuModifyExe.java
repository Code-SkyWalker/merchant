package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ISpuGateway;
import org.dromara.merchant.infrastructure.commodity.converter.SpuConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 商品spu修改执行器
 * @Author Code Skywalker
 * @Date 2025/12/9 14:42
 */
@Component
@RequiredArgsConstructor
public class SpuModifyExe {

    private final ISpuGateway spuGateway;
    private final SpuConvertor spuConvertor;

    public boolean execute(SpuModifyCmd cmd) {
        return spuGateway.save(this.spuConvertor.toEntity(cmd));
    }

}
