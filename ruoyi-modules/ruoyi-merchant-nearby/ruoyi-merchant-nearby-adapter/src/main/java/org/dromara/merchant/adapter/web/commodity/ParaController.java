package org.dromara.merchant.adapter.web.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.commodity.IParaService;
import org.dromara.merchant.client.commodity.dto.data.clientobject.BrandPageCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.ParaPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.BrandQry;
import org.dromara.merchant.client.commodity.dto.data.command.query.ParaQry;
import org.springframework.web.bind.annotation.*;

/**
 * 商品参数模板
 * @Author Code Skywalker
 * @Date 2025/12/8 13:26
 */
@RestController
@RequestMapping("/merchant/commodity/template/para")
@RequiredArgsConstructor
public class ParaController {

    private final IParaService paraService;

    /**
     * 添加商品参数模板
     * @param cmd 添加参数
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addPara(@RequestBody final ParaCreateCmd cmd) {
        boolean add = paraService.create(cmd);
        return R.ok(add);
    }

    /**
     * 修改商品参数模板
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifyPara(@RequestBody final ParaModifyCmd cmd) {
        boolean modify = paraService.modify(cmd);
        return R.ok(modify);
    }

    /**
     * 删除商品参数模板
     * @param id 模板ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R<Boolean> deletePara(@PathVariable final Integer id) {
        boolean delete = paraService.delete(id);
        return R.ok(delete);
    }

    /**
     * 分页查询商品参数
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品品牌列表
     */
    @GetMapping("/page")
    public TableDataInfo<ParaPageCO> queryPage(@ModelAttribute ParaQry qry, @ModelAttribute PageQuery page) {
        Page<ParaPageCO> paraPage = paraService.queryPage(qry, page);
        return TableDataInfo.build(paraPage);
    }

}
