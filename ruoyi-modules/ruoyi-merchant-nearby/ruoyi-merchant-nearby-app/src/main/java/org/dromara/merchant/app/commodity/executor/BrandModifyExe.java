package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.BrandModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.IBrandGateway;
import org.dromara.merchant.domain.commodity.model.Brand;
import org.dromara.merchant.infrastructure.commodity.converter.BrandConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description 商品品牌修改执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 13:07
 */
@Component
@RequiredArgsConstructor
public class BrandModifyExe {

    private final IBrandGateway brandGateway;
    private final BrandConvertor convertor;

    public boolean execute(BrandModifyCmd cmd) {
        Integer save = brandGateway.save(convertor.toBrandEntity(cmd));
        return save != null;
    }

}
