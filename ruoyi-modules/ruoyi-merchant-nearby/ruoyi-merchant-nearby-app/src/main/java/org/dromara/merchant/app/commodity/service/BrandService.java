package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.IBrandService;
import org.dromara.merchant.app.commodity.executor.BrandCreateExe;
import org.dromara.merchant.app.commodity.executor.BrandDeleteExe;
import org.dromara.merchant.app.commodity.executor.BrandModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.BrandCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.IBrandGateway;
import org.dromara.merchant.domain.commodity.model.Brand;
import org.springframework.stereotype.Component;

/**
 * @Description 商品品牌服务接口
 * @Author Code Skywalker
 * @Date 2025/12/8 13:21
 */
@Component
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandCreateExe createExe;
    private final BrandModifyExe modifyExe;
    private final BrandDeleteExe deleteExe;

    private final IBrandGateway brandGateway;

    @Override
    public boolean create(BrandCreateCmd cmd) {
        return this.createExe.execute(cmd);
    }

    @Override
    public boolean modify(BrandModifyCmd cmd) {
        return this.modifyExe.execute(cmd);
    }

    @Override
    public boolean delete(BrandDeleteCmd cmd) {
        return this.deleteExe.execute(cmd);
    }

    @Override
    public Brand queryById(Integer id) {
        return brandGateway.queryById(id);
    }
}
