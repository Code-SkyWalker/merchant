package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ISpuGateway;
import org.springframework.stereotype.Component;

/**
 * @Description 商品spu删除执行器
 * @Author Code Skywalker
 * @Date 2025/12/9 14:42
 */
@Component
@RequiredArgsConstructor
public class SpuDeleteExe {

    private final ISpuGateway spuGateway;

    public boolean execute(Long id) {
        return spuGateway.deleteById(id);
    }

}
