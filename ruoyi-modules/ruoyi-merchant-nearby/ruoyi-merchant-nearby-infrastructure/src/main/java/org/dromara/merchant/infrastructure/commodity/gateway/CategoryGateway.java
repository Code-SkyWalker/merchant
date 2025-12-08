package org.dromara.merchant.infrastructure.commodity.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.gateway.ICategoryGateway;
import org.dromara.merchant.domain.commodity.model.Category;
import org.dromara.merchant.infrastructure.commodity.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.commodity.mapper.CategoryMapper;
import org.dromara.merchant.infrastructure.commodity.mapper.dataobject.CategoryDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryGateway implements ICategoryGateway {

    private final CategoryMapper mapper;
    private final CategoryConvertor convertor;

    @Override
    public boolean save(Category category) {
        return mapper.insertOrUpdate(convertor.toDO(category));
    }

    @Override
    public boolean delete(Integer id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Category queryById(Integer id) {
        CategoryDO categoryDO = mapper.selectById(id);
        return convertor.toEntity(categoryDO);
    }

    @Override
    public List<Category> queryByParentId(Integer parentId) {
        return convertor.toEntityList(mapper.selectByParentId(parentId));
    }
}
