package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.BrandCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.IBrandGateway;
import org.dromara.merchant.domain.commodity.gateway.ICategoryBrandGateway;
import org.dromara.merchant.domain.commodity.model.CategoryBrand;
import org.dromara.merchant.infrastructure.commodity.converter.BrandConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 商品品牌创建执行器
 * @Author Code Skywalker
 * @Date 2025/12/8 13:07
 */
@Component
@RequiredArgsConstructor
public class BrandCreateExe {

    private final ICategoryBrandGateway categoryBrandGateway;
    private final IBrandGateway brandGateway;
    private final BrandConvertor convertor;

    @Transactional(rollbackFor = Exception.class)
    public boolean execute(BrandCreateCmd cmd) {
        Integer brandId = brandGateway.save(convertor.toBrandEntity(cmd));
        List<CategoryBrand> categoryBrandList = cmd.getCategoryIds().stream()
            .map(categoryId -> new CategoryBrand(categoryId, brandId))
            .toList();
        if (categoryBrandList.isEmpty()) return brandId != null;

        boolean relationInsert = categoryBrandGateway.saveBatch(categoryBrandList);
        return brandId != null && relationInsert;
    }

}
