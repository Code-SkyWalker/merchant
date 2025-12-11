package org.dromara.merchant.adapter.web.commodity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.commodity.ITemplateService;
import org.dromara.merchant.client.commodity.dto.data.clientobject.SkuTempCO;
import org.dromara.merchant.client.commodity.dto.data.clientobject.TemplatePageCO;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.dromara.merchant.client.commodity.dto.data.command.query.TemplateQry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品模板
 * @Author Code Skywalker
 * @Date 2025/12/8 15:04
 */
@RestController
@RequestMapping("/merchant/commodity/template")
@RequiredArgsConstructor
public class TemplateController {

    private final ITemplateService templateService;

    /**
     * 添加商品模板
     * @param cmd 添加参数
     * @return 添加结果
     */
    @PostMapping
    @Log(title = "商品模板", businessType = BusinessType.INSERT)
    public R<Boolean> addTemplate(@RequestBody final TemplateCreateCmd cmd) {
        return R.ok(templateService.create(cmd));
    }

    /**
     * 修改商品模板
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    @Log(title = "商品模板", businessType = BusinessType.UPDATE)
    public R<Boolean> modifyTemplate(@RequestBody final TemplateModifyCmd cmd) {
        return R.ok(templateService.modify(cmd));
    }

    /**
     * 删除商品模板
     * @param id ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    @Log(title = "商品模板", businessType = BusinessType.DELETE)
    public R<Boolean> deleteTemplate(@PathVariable final Integer id) {
        return R.ok(templateService.delete(id));
    }

    /**
     * 分页查询商品规格
     * @param qry 查询参数
     * @param page 分页参数
     * @return 商品规格列表
     */
    @GetMapping("/page")
    public TableDataInfo<TemplatePageCO> queryPage(@ModelAttribute TemplateQry qry, @ModelAttribute PageQuery page) {
        Page<TemplatePageCO> specPage = templateService.queryPage(qry, page);
        return TableDataInfo.build(specPage);
    }

    /**
     * 查询商品规格参数模板
     * @param id templateId
     * @return 商品规格参数
     */
    @GetMapping("/spec-para/{id}")
    public R<List<SkuTempCO>> querySpecPara(@PathVariable final Integer id) {
        List<SkuTempCO> tempCOs = templateService.queryByTemplateId(id);
        return R.ok(tempCOs);
    }

}
