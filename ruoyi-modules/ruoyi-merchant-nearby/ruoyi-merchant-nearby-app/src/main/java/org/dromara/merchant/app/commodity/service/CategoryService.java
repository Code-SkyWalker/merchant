package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.constant.CacheNames;
import org.dromara.common.core.utils.TreeIfyUtils;
import org.dromara.merchant.app.commodity.ICategoryService;
import org.dromara.merchant.app.commodity.executor.CategoryCreateExe;
import org.dromara.merchant.app.commodity.executor.CategoryDeleteExe;
import org.dromara.merchant.app.commodity.executor.CategoryModifyExe;
import org.dromara.merchant.client.commodity.dto.data.clientobject.CategoryTreeCO;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ICategoryGateway;
import org.dromara.merchant.domain.commodity.model.Category;
import org.dromara.merchant.infrastructure.commodity.mapper.CategoryMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 15:50
 */
@Component
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryCreateExe categoryCreateExe;
    private final CategoryModifyExe categoryModifyExe;
    private final CategoryDeleteExe categoryDeleteExe;

    private final ICategoryGateway categoryGateway;

    private final CategoryMapper categoryMapper;

    @Override
    @CacheEvict(cacheNames = "goods.category", key = "#cmd.merchantId")
    public boolean create(CategoryCreateCmd cmd) {
        return this.categoryCreateExe.execute(cmd);
    }

    @Override
    @CacheEvict(cacheNames = "goods.category", key = "#cmd.merchantId")
    public boolean modify(CategoryModifyCmd cmd) {
        return this.categoryModifyExe.execute( cmd);
    }

    @Override
    @CacheEvict(cacheNames = "goods.category", key = "#merchantId")
    public boolean delete(Integer id, Long merchantId) {
        return this.categoryDeleteExe.execute(id, merchantId);
    }

    @Override
    public Category queryById(Integer id) {
        return this.categoryGateway.queryById(id);
    }

    @Override
    public List<Category> queryByParentId(Integer parentId) {
        return this.categoryGateway.queryByParentId(parentId);
    }

    @Override
    @CacheEvict(cacheNames = "goods.category", key = "#merchantId")
    public List<CategoryTreeCO> queryTree(Long merchantId) {
        List<CategoryTreeCO> nodes = categoryMapper.selectAll(merchantId);
        return TreeIfyUtils.treeIfy(
            nodes,
            CategoryTreeCO::isRoot,
            (current, next) -> current.getId().equals(next.getParentId()),
            CategoryTreeCO::setChildren
        );
    }
}
