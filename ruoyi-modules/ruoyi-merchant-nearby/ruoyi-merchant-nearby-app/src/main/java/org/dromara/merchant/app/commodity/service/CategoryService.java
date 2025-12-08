package org.dromara.merchant.app.commodity.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.commodity.ICategoryService;
import org.dromara.merchant.app.commodity.executor.CategoryCreateExe;
import org.dromara.merchant.app.commodity.executor.CategoryDeleteExe;
import org.dromara.merchant.app.commodity.executor.CategoryModifyExe;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.domain.commodity.gateway.ICategoryGateway;
import org.dromara.merchant.domain.commodity.model.Category;
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


    @Override
    public boolean create(CategoryCreateCmd cmd) {
        return this.categoryCreateExe.execute(cmd);
    }

    @Override
    public boolean modify(CategoryModifyCmd cmd) {
        return this.categoryModifyExe.execute( cmd);
    }

    @Override
    public boolean delete(Integer id) {
        return this.categoryDeleteExe.execute(id);
    }

    @Override
    public Category queryById(Integer id) {
        return this.categoryGateway.queryById(id);
    }

    @Override
    public List<Category> queryByParentId(Integer parentId) {
        return this.categoryGateway.queryByParentId(parentId);
    }
}
