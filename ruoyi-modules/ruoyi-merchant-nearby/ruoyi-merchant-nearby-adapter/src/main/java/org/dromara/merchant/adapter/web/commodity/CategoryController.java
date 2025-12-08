package org.dromara.merchant.adapter.web.commodity;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.commodity.ICategoryService;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryModifyCmd;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类目
 * @Author Code Skywalker
 * @Date 2025/12/8 15:54
 */
@RestController
@RequestMapping("/merchant/commodity/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    /**
     * 添加商品类目
     * @param cmd 类目参数
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addCategory(@RequestBody final CategoryCreateCmd cmd) {
        return R.ok(categoryService.create(cmd));
    }

    /**
     * 修改商品类目
     * @param cmd 类目参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifyCategory(@RequestBody final CategoryModifyCmd cmd) {
        return R.ok(categoryService.modify(cmd));
    }

    /**
     * 删除商品类目
     * @param id 类目id
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R<Boolean> deleteCategory(@PathVariable final Integer id) {
        return R.ok(categoryService.delete(id));
    }


}
