package org.dromara.merchant.adapter.web.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.category.IDefaultCategoryService;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryDefaultCO;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.DefaultCategoryPageQry;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 平台分类接口
 * @Author Code Skywalker
 * @Date 2025-11-03 13:55
 */
@RestController
@RequestMapping("/merchant/category-default")
@RequiredArgsConstructor
public class DefaultCategoryController {

    private final IDefaultCategoryService defaultCategoryService;

    @GetMapping("/page")
    public TableDataInfo<CategoryDefaultCO> page(@ModelAttribute DefaultCategoryPageQry qry, @ModelAttribute PageQuery page) {
        Page<CategoryDefaultCO> categoryDefaultCOs = this.defaultCategoryService.queryPage(qry, page);
        return TableDataInfo.build(categoryDefaultCOs);
    }

    @GetMapping("/{categoryId}")
    public R<CategoryDefaultCO> queryById(@PathVariable Long categoryId) {
        CategoryDefaultCO categoryDefaultCO = this.defaultCategoryService.queryById(categoryId);
        return R.ok(categoryDefaultCO);
    }

    @PostMapping
    public R<Boolean> create(@RequestBody DefaultCategoryCreateCmd cmd) {
        boolean created = this.defaultCategoryService.create(cmd);
        return R.ok(created);
    }

    @PutMapping
    public R<Boolean> modify(@RequestBody DefaultCategoryModifyCmd cmd) {
        boolean modified = this.defaultCategoryService.modify(cmd);
        return R.ok(modified);
    }

    @DeleteMapping("/{categoryId}")
    public R<Boolean> delete(@PathVariable Long categoryId) {
        boolean deleted = this.defaultCategoryService.delete(categoryId);
        return R.ok(deleted);
    }

}
