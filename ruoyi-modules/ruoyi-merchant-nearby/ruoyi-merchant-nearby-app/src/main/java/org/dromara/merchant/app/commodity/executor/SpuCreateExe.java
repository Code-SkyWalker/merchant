package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.ISpuGateway;
import org.dromara.merchant.domain.commodity.model.Spu;
import org.dromara.merchant.infrastructure.commodity.converter.SpuConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 商品spu创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/9 14:41
 */
@Component
@RequiredArgsConstructor
public class SpuCreateExe {

    private final ISpuGateway spuGateway;
    private final SpuConvertor spuConvertor;

    public Long execute(SpuCreateCmd cmd) {
        cmd.setId(SnowflakeIdGenerator.generateId());
        Spu spu = spuConvertor.toEntity(cmd);
        if (spuGateway.save(spu)) return spu.getId();
        return null;
    }
}
