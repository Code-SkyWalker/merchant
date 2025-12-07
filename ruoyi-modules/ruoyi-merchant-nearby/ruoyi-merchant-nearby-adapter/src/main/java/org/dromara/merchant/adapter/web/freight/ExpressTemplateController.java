package org.dromara.merchant.adapter.web.freight;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.freight.IExpressTemplateService;
import org.dromara.merchant.client.freight.dto.data.clientobject.ExpressTemplateCO;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.ExpressTemplateModifyCmd;
import org.dromara.merchant.client.freight.dto.data.command.query.ExpressTemplatePageQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商户快递运费模板控制器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Validated
@RestController
@RequestMapping("/merchant/freight/express/template")
@RequiredArgsConstructor
public class ExpressTemplateController {

    @Autowired
    private IExpressTemplateService expressTemplateService;

    /**
     * 运费模板列表
     * @param query 查询参数
     * @param page 分页参数
     * @return 运费模板列表
     */
    @GetMapping("/pages")
    public TableDataInfo<ExpressTemplateCO> queryPage(@ModelAttribute ExpressTemplatePageQry query, @ModelAttribute PageQuery page) {
        Page<ExpressTemplateCO> pages = this.expressTemplateService.queryPage(query, page);
        return TableDataInfo.build(pages);
    }

    /**
     * 创建运费模板
     * @param cmd 创建参数
     * @return 创建的模板ID
     */
    @PostMapping
    @Log(title = "商户运费模板", businessType = BusinessType.INSERT)
    public R<Long> create(@RequestBody ExpressTemplateCreateCmd cmd) {
        return R.ok(this.expressTemplateService.create(cmd));
    }

    /**
     * 修改运费模板
     * @param cmd 修改参数
     * @return 是否修改成功
     */
    @PutMapping
    @Log(title = "商户运费模板", businessType = BusinessType.UPDATE)
    public R<Boolean> modify(@RequestBody ExpressTemplateModifyCmd cmd) {
        return R.ok(this.expressTemplateService.modify(cmd));
    }

    /**
     * 删除运费模板
     * @param templateId 运费模板ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{templateId}")
    @Log(title = "商户运费模板", businessType = BusinessType.DELETE)
    public R<Boolean> delete(@PathVariable Long templateId) {
        return R.ok(this.expressTemplateService.delete(templateId));
    }

    /**
     * 设置默认运费模板
     * @param templateId 运费模板ID
     * @param merchantId 商户ID
     * @return 是否设置成功
     */
    @PostMapping("/set-default/{templateId}/{merchantId}")
    @Log(title = "商户运费模板", businessType = BusinessType.UPDATE)
    public R<Boolean> setDefault(@PathVariable Long templateId, @PathVariable Long merchantId) {
        return R.ok(this.expressTemplateService.setDefault(templateId, merchantId));
    }

}
