package org.dromara.merchant.adapter.web.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.commodity.IBrandService;
import org.dromara.merchant.client.commodity.dto.data.clientobject.BrandPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.BrandCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.BrandQry;
import org.springframework.web.bind.annotation.*;

/**
 * 商品品牌
 * @Author Code Skywalker
 * @Date 2025/12/8 13:26
 */
@RestController
@RequestMapping("/merchant/commodity/brand")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService brandService;

    /**
     * 添加商品品牌
     * @param cmd 品牌参数
     * @return 添加结果
     */
    @PostMapping
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    public R<Boolean> addBrand(@RequestBody final BrandCreateCmd cmd) {
        boolean add = brandService.create(cmd);
        return R.ok(add);
    }

    /**
     * 修改商品品牌
     * @param cmd 品牌参数
     * @return 修改结果
     */
    @PutMapping
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    public R<Boolean> modifyBrand(@RequestBody final BrandModifyCmd cmd) {
        boolean modify = brandService.modify(cmd);
        return R.ok(modify);
    }

    /**
     * 删除商品品牌
     * @param cmd 品牌参数
     * @return 删除结果
     */
    @DeleteMapping
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    public R<Boolean> deleteBrand(@RequestBody final BrandDeleteCmd cmd) {
        boolean delete = brandService.delete(cmd);
        return R.ok(delete);
    }

    /**
     * 分页查询商品品牌
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品品牌列表
     */
    @GetMapping("/page")
    public TableDataInfo<BrandPageCO> queryPage(@ModelAttribute BrandQry qry, @ModelAttribute PageQuery page) {
        Page<BrandPageCO> brandPage = brandService.queryPage(qry, page);
        return TableDataInfo.build(brandPage);
    }

}
