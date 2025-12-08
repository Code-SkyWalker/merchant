package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.domain.commodity.gateway.ICategoryBrandGateway;
import org.dromara.merchant.infrastructure.commodity.converter.CategoryBrandConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 13:22
 */
@Component
@RequiredArgsConstructor
public class BrandDeleteExe {

    private final ICategoryBrandGateway categoryBrandGateway;
    private final CategoryBrandConvertor convertor;

    public boolean execute(BrandDeleteCmd cmd) {
        return categoryBrandGateway.deleteByCategoryIdAndBrandId(convertor.toEntity(cmd));
    }
}
