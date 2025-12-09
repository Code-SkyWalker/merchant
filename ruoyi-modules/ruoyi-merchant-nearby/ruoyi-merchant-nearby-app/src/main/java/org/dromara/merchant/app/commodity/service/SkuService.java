package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.commodity.executor.SkuCreateExe;
import org.dromara.merchant.app.commodity.executor.SkuModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ISkuGateway;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/9 17:28
 */
@Component
@RequiredArgsConstructor
public class SkuService implements ISkuService {

    private final SkuCreateExe skuCreateExe;
    private final SkuModifyExe skuModifyExe;
    private final ISkuGateway skuGateway;


    @Override
    public boolean create(List<SkuCreateCmd> cmd) {
        return this.skuCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(List<SkuModifyCmd> cmd) {
        return this.skuModifyExe.execute(cmd);
    }

    @Override
    public boolean deleteBySpuId(Long spuId) {
        return this.skuGateway.deleteBySpuId(spuId);
    }

    @Override
    public Sku queryById(Long id) {
        return this.skuGateway.queryById(id);
    }

    @Override
    public List<Sku> queryBySpuId(Long spuId) {
        return this.skuGateway.queryBySpuId(spuId);
    }
}
