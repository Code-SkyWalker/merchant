package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ICategoryBrandGateway;
import org.dromara.merchant.domain.commodity.model.CategoryBrand;
import org.dromara.merchant.infrastructure.commodity.converter.CategoryBrandConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.CategoryBrandMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryBrandGateway implements ICategoryBrandGateway {

    private final CategoryBrandMapper mapper;
    private final CategoryBrandConvertor convertor;

    @Override
    public boolean save(CategoryBrand categoryBrand) {
        return this.mapper.insert(convertor.toDO(categoryBrand)) > 0;
    }

    @Override
    public boolean saveBatch(List<CategoryBrand> categoryBrands) {
        return this.mapper.insertBatch(categoryBrands.stream().map(convertor::toDO).toList());
    }

    @Override
    public boolean deleteByCategoryIdAndBrandId(CategoryBrand categoryBrand) {
        return this.mapper.deleteByMultiId(convertor.toDO(categoryBrand)) > 0;
    }

}
