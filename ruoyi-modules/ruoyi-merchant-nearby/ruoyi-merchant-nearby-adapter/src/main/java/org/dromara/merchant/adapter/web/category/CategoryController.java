package org.dromara.merchant.adapter.web.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.category.ICategoryService;
import org.dromara.merchant.client.category.dto.data.clientobject.CategoryCO;
import org.dromara.merchant.client.category.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryModifyCmd;
import org.dromara.merchant.client.category.dto.data.command.CategoryPageQry;
import org.springframework.web.bind.annotation.*;


/**
 * 商家分类接口
 *
 * @Author Code Skywalker
 * @Date 2025-11-03 13:55
 */
@RestController
@RequestMapping("/merchant/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    /**
     * 后台分类条件分页查询
     *
     * @param qry   查询参数
     * @param query 分页参数
     * @return 分类列表
     */
    @GetMapping("/backend/page")
    public TableDataInfo<CategoryCO> page(@ModelAttribute CategoryPageQry qry, @ModelAttribute PageQuery query) {
        Page<CategoryCO> categoryCOs = this.categoryService.queryPage(qry, query);
        return TableDataInfo.build(categoryCOs);
    }

    /**
     * 主键查询
     * @param categoryId 分类ID
     * @return 分类信息
     */
    @GetMapping("/{categoryId}")
    public R<CategoryCO> queryById(@PathVariable Long categoryId) {
        CategoryCO categoryCO = this.categoryService.queryById(categoryId);
        return R.ok(categoryCO);
    }

    /**
     * 创建分类
     *
     * @param cmd 创建参数
     * @return 创建结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody CategoryCreateCmd cmd) {
        boolean created = this.categoryService.create(cmd);
        return created ? R.ok(true) : R.fail(false);
    }

    /**
     * 修改分类
     *
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modify(@RequestBody CategoryModifyCmd cmd) {
        boolean modified = this.categoryService.modify(cmd);
        return modified ? R.ok(true) : R.fail(false);
    }

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{categoryId}")
    public R<Boolean> delete(@PathVariable Long categoryId) {
        boolean deleted = this.categoryService.delete(categoryId);
        return deleted ? R.ok(true) : R.fail(false);
    }

}
