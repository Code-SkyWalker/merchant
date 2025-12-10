package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.merchant.domain.merchant.gateway.IMerCategoryDefaultGateway;
import org.dromara.merchant.domain.merchant.model.MerCategoryDefault;
import org.dromara.merchant.infrastructure.merchant.converter.MerCategoryConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryDefaultMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDO;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.CategoryDefaultDO;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025-11-03 13:49
 */
@Component
@RequiredArgsConstructor
public class MerCategoryDefaultGateway implements IMerCategoryDefaultGateway {

    private final MerCategoryDefaultMapper categoryMapper;

    private final MerCategoryConvertor merCategoryConvertor;


    @Override
    public boolean create(MerCategoryDefault merCategoryDefault) {
        return this.categoryMapper.insertSelective(merCategoryConvertor.toCategoryDefaultDO(merCategoryDefault)) > 0;
    }

    @Override
    public boolean modify(MerCategoryDefault merCategoryDefault) {
        return false;
    }

    @Override
    public boolean delete(Long categoryId) {
        return this.categoryMapper.deleteByPrimaryKey(categoryId) > 0;
    }

    @Override
    public boolean deleteByTenantId(String tenantId) {
        return this.categoryMapper.deleteByTenantId(tenantId) > 0;
    }

    @Override
    public MerCategoryDefault selectById(Long categoryId) {
        CategoryDefaultDO categoryDefaultDO = this.categoryMapper.selectByPrimaryKey(categoryId);
        return merCategoryConvertor.toCategoryDefaultEntity(categoryDefaultDO);
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
