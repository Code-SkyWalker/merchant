package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryGateway;
import org.dromara.merchant.domain.merchant.model.MerCategory;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
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
public class MerCategoryGateway implements IMerCategoryGateway {

    private final CategoryMapper categoryMapper;
    private final MerCategoryConvertor merCategoryConvertor;


    @Override
    public boolean create(MerCategory merCategory) {
        return this.categoryMapper.insertSelective(merCategoryConvertor.toCategoryDO(merCategory)) > 0;
    }

    @Override
    public boolean modify(MerCategory merCategory) {
        return this.categoryMapper.updateByPrimaryKeySelective(merCategoryConvertor.toCategoryDO(merCategory)) > 0;
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.categoryMapper.deleteByPrimaryKey(categoryId) > 0;
    }

    @Override
    public MerCategory selectById(Long categoryId) {
        CategoryDO categoryDO = this.categoryMapper.selectByPrimaryKey(categoryId);
        return this.merCategoryConvertor.toCategoryEntity(categoryDO);
    }

}
