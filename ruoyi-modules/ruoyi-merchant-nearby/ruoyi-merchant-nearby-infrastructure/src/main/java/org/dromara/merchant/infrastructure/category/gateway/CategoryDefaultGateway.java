package org.dromara.merchant.infrastructure.category.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.merchant.domain.category.gateway.ICategoryDefaultGateway;
import org.dromara.merchant.domain.category.model.CategoryDefault;
import org.dromara.merchant.infrastructure.category.converter.CategoryConvertor;
import org.dromara.merchant.infrastructure.category.mapper.CategoryDefaultMapper;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.category.mapper.dataobject.CategoryDefaultDO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 13:49
 */
@Component
@RequiredArgsConstructor
public class CategoryDefaultGateway implements ICategoryDefaultGateway {

    private final CategoryDefaultMapper categoryMapper;

    private final CategoryConvertor categoryConvertor;


    @Override
    public boolean create(CategoryDefault categoryDefault) {
        return this.categoryMapper.insertSelective(categoryConvertor.toCategoryDefaultDO(categoryDefault)) > 0;
    }

    @Override
    public boolean modify(CategoryDefault categoryDefault) {
        return false;
    }

    @Override
    public boolean delete(Long categoryId) {
        return false;
    }

    @Override
    public boolean deleteByTenantId(String tenantId) {
        return this.categoryMapper.deleteByTenantId(tenantId) > 0;
    }

    @Override
    public CategoryDefault selectById(Long categoryId) {
        return null;
    }

    @Override
    public boolean writeDefault() {
        // 创建一个带自动填充字段的 CategoryDefaultDO 对象
        Long userId = LoginHelper.getUserId();
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setTenantId(LoginHelper.getTenantId());
        categoryDO.setCreateBy(userId);
        categoryDO.setUpdateBy(userId);
        categoryDO.setCreateDept(LoginHelper.getDeptId());

        return this.categoryMapper.writeDefault(categoryDO) > 0;
    }
}
