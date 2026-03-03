package org.dromara.merchant.adapter.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.merchant.IMerCategoryService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.MerCategoryMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商家分类接口
 *
 * @Author Code Skywalker
 * @Date 2025-11-03 13:55
 */
@RestController
@RequestMapping("/merchant/category")
@RequiredArgsConstructor
public class MerCategoryController {

    private final IMerCategoryService categoryService;

    private final MerCategoryMapper merCategoryMapper;

    /**
     * 前端查询-分类查询
     *
     * @param parentId 父级ID 0表示一级分类
     * @return 分类列表
     */
    @GetMapping("/frontend/page")
    public TableDataInfo<MerCategoryCO> frontend(@RequestParam Long parentId, @ModelAttribute PageQuery query) {
        MerCategoryPageQry qry = new MerCategoryPageQry();
        qry.setParentId(parentId);
        Page<MerCategoryCO> categoryCOs = this.categoryService.queryPage(qry, query);
        return TableDataInfo.build(categoryCOs);
    }

    /**
     * 后台分类条件分页查询
     *
     * @param qry   查询参数
     * @param query 分页参数
     * @return 分类列表
     */
    @GetMapping("/backend/page")
    public TableDataInfo<MerCategoryCO> page(@ModelAttribute MerCategoryPageQry qry, @ModelAttribute PageQuery query) {
        Page<MerCategoryCO> categoryCOs = this.categoryService.queryPage(qry, query);
        return TableDataInfo.build(categoryCOs);
    }

    /**
     * 主键查询
     * @param categoryId 分类ID
     * @return 分类信息
     */
    @GetMapping("/{categoryId}")
    public R<MerCategoryCO> queryById(@PathVariable Long categoryId) {
        MerCategoryCO merCategoryCO = this.categoryService.queryById(categoryId);
        return R.ok(merCategoryCO);
    }

    /**
     * 创建分类
     *
     * @param cmd 创建参数
     * @return 创建结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody MerCategoryCreateCmd cmd) {
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
    public R<Boolean> modify(@RequestBody MerCategoryModifyCmd cmd) {
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
