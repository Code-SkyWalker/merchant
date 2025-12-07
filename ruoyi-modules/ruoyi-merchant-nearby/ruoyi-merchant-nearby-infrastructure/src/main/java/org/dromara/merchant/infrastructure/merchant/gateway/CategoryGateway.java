package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.ICategoryGateway;
import org.dromara.merchant.domain.merchant.model.MerCategory;
import org.dromara.merchant.infrastructure.merchant.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.CategoryMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;
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
    public boolean create(MerCategory merCategory) {
        return this.categoryMapper.insertSelective(categoryConvertor.toCategoryDO(merCategory)) > 0;
    }

    @Override
    public boolean modify(MerCategory merCategory) {
        return this.categoryMapper.updateByPrimaryKeySelective(categoryConvertor.toCategoryDO(merCategory)) > 0;
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.categoryMapper.deleteByPrimaryKey(categoryId) > 0;
    }

    @Override
    public MerCategory selectById(Long categoryId) {
        CategoryDO categoryDO = this.categoryMapper.selectByPrimaryKey(categoryId);
        return this.categoryConvertor.toCategoryEntity(categoryDO);
    }

}
