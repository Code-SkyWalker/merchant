package org.dromara.merchant.infrastructure.category.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.category.gateway.ICategoryGateway;
import org.dromara.merchant.domain.category.model.Category;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.category.mapper.CategoryMapper;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDO;
import org.springframework.stereotype.Component;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 13:49
 */
@Component
@RequiredArgsConstructor
public class CategoryGateway implements ICategoryGateway {

    private final CategoryMapper categoryMapper;
    private final CategoryConvertor categoryConvertor;


    @Override
    public boolean create(Category category) {
        return this.categoryMapper.insertSelective(categoryConvertor.toCategoryDO(category)) > 0;
    }

    @Override
    public boolean modify(Category category) {
        return this.categoryMapper.updateByPrimaryKeySelective(categoryConvertor.toCategoryDO(category)) > 0;
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.categoryMapper.deleteByPrimaryKey(categoryId) > 0;
    }

    @Override
    public Category selectById(Long categoryId) {
        CategoryDO categoryDO = this.categoryMapper.selectByPrimaryKey(categoryId);
        return this.categoryConvertor.toCategoryEntity(categoryDO);
    }

}
