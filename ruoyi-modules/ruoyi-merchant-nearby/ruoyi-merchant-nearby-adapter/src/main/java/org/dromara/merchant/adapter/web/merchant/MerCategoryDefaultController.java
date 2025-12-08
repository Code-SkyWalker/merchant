package org.dromara.merchant.adapter.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.merchant.IMerCategoryDefaultService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerCategoryDefaultCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerCategoryDefaultModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerCategoryDefaultPageQry;
import org.springframework.web.bind.annotation.*;

/**
 * 平台分类接口
 * @Author Code Skywalker
 * @Date 2025-11-03 13:55
 */
@RestController
@RequestMapping("/merchant/category-default")
@RequiredArgsConstructor
public class MerCategoryDefaultController {

    private final IMerCategoryDefaultService defaultCategoryService;

    /**
     * 后台分类条件分页查询
     * @param qry 查询参数
     * @param page 分页参数
     * @return 分类列表
     */
    @GetMapping("/page")
    public TableDataInfo<MerCategoryDefaultCO> page(@ModelAttribute MerCategoryDefaultPageQry qry, @ModelAttribute PageQuery page) {
        Page<MerCategoryDefaultCO> categoryDefaultCOs = this.defaultCategoryService.queryPage(qry, page);
        return TableDataInfo.build(categoryDefaultCOs);
    }

    /**
     * 主键查询
     * @param categoryId 分类ID
     * @return 分类信息
     */
    @GetMapping("/{categoryId}")
    public R<MerCategoryDefaultCO> queryById(@PathVariable Long categoryId) {
        MerCategoryDefaultCO merCategoryDefaultCO = this.defaultCategoryService.queryById(categoryId);
        return R.ok(merCategoryDefaultCO);
    }

    /**
     * 创建分类
     * @param cmd 创建参数
     * @return 创建结果
     */
    @PostMapping
    public R<Boolean> create(@RequestBody MerCategoryDefaultCreateCmd cmd) {
        boolean created = this.defaultCategoryService.create(cmd);
        return R.ok(created);
    }

    /**
     * 修改分类
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modify(@RequestBody MerCategoryDefaultModifyCmd cmd) {
        boolean modified = this.defaultCategoryService.modify(cmd);
        return R.ok(modified);
    }

    /**
     * 删除分类
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{categoryId}")
    public R<Boolean> delete(@PathVariable Long categoryId) {
        boolean deleted = this.defaultCategoryService.delete(categoryId);
        return R.ok(deleted);
    }

}
