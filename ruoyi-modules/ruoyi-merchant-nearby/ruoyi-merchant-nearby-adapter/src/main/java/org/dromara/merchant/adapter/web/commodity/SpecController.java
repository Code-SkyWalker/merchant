package org.dromara.merchant.adapter.web.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.commodity.ISpecService;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SpecPageCO;
import org.dromara.merchant.client.commodity.dto.data.command.SpecCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpecModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.SpecQry;
import org.springframework.web.bind.annotation.*;

/**
 * 商品规格模板
 * @Author Code Skywalker
 * @Date 2025/12/8 13:26
 */
@RestController
@RequestMapping("/merchant/commodity/template/spec")
@RequiredArgsConstructor
public class SpecController {

    private final ISpecService specService;

    /**
     * 添加商品规格模板
     * @param cmd 添加参数
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addSpec(@RequestBody final SpecCreateCmd cmd) {
        boolean add = specService.create(cmd);
        return R.ok(add);
    }

    /**
     * 修改商品规格模板
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifySpec(@RequestBody final SpecModifyCmd cmd) {
        boolean modify = specService.modify(cmd);
        return R.ok(modify);
    }

    /**
     * 删除商品规格模板
     * @param id ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R<Boolean> deletePara(@PathVariable final Integer id) {
        boolean delete = specService.delete(id);
        return R.ok(delete);
    }

    /**
     * 分页查询商品规格
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品规格列表
     */
    @GetMapping("/page")
    public TableDataInfo<SpecPageCO> queryPage(@ModelAttribute SpecQry qry, @ModelAttribute PageQuery page) {
        Page<SpecPageCO> specPage = specService.queryPage(qry, page);
        return TableDataInfo.build(specPage);
    }

}
