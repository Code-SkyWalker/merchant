package org.dromara.merchant.adapter.web.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.commodity.ISkuService;
import org.dromara.merchant.app.commodity.ISpuService;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuDetailCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpuPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.SkuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SkuGenCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpuQry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品spu
 * @Author Code Skywalker
 * @Date 2025/12/10 11:18
 */
@RestController
@RequestMapping("/merchant/spu")
@RequiredArgsConstructor
public class SpuController {

    private final ISpuService spuService;

    private final ISkuService skuService;

    /**
     * 添加商品
     * @param cmd 添加参数
     * @return 添加结果
     */
    @PostMapping
    @Log(title = "商品spu", businessType = BusinessType.INSERT)
    public R<Boolean> addSpu(@RequestBody final SpuCreateCmd cmd) {
        return R.ok(spuService.create(cmd));
    }

    /**
     * 修改商品
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    @Log(title = "商品spu", businessType = BusinessType.UPDATE)
    public R<Boolean> modifySpu(@RequestBody final SpuModifyCmd cmd) {
        return R.ok(spuService.modify(cmd));
    }

    /**
     * 删除商品
     * @param id ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    @Log(title = "商品spu", businessType = BusinessType.DELETE)
    public R<Boolean> deleteSpu(@PathVariable final Long id) {
        return R.ok(spuService.delete(id));
    }

    /**
     * 生成商品SKU
     * @param cmd 生成参数
     * @return 生成结果
     */
    @PostMapping("/generateSkus")
    public R<List<SkuCreateCmd>> generateSkus(@RequestBody final SkuGenCmd cmd) {
        cmd.setSpuId(SnowflakeIdGenerator.generateId());
        return R.ok(skuService.generateSkus(cmd.getSpuId(), cmd.getSpecItems(), cmd.getBaseSku()));
    }

    /**
     * 前端可用 - 查询商品分页
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品分页
     */
    @GetMapping("/page")
    public TableDataInfo<SpuPageCO> queryPage(@ModelAttribute final SpuQry qry, @ModelAttribute PageQuery page) {
        Page<SpuPageCO> spu = spuService.queryPage(qry, page);
        return TableDataInfo.build(spu);
    }

    /**
     * 查询商品详情
     * @param id ID
     * @return 商品详情
     */
    @GetMapping("/{id}")
    public R<SpuDetailCO> queryDetailById(@PathVariable final Long id) {
        return R.ok(spuService.queryDetailById(id));
    }

}
