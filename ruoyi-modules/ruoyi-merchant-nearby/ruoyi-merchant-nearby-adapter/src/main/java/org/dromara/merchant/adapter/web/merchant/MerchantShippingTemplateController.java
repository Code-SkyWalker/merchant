package org.dromara.merchant.adapter.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.merchant.app.merchant.IMerchantShippingTemplateService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingTemplateCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplateModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingTemplatePageQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 商户运费模板控制器
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Validated
@RestController
@RequestMapping("/merchant/shipping/template")
@RequiredArgsConstructor
public class MerchantShippingTemplateController {

    @Autowired
    private IMerchantShippingTemplateService merchantShippingTemplateService;

    /**
     * 运费模板列表
     * @param query 查询参数
     * @param page 分页参数
     * @return 运费模板列表
     */
    @GetMapping("/pages")
    public TableDataInfo<MerchantShippingTemplateCO> queryPage(@ModelAttribute MerchantShippingTemplatePageQry query, @ModelAttribute PageQuery page) {
        Page<MerchantShippingTemplateCO> pages = this.merchantShippingTemplateService.queryPage(query, page);
        return TableDataInfo.build(pages);
    }

    /**
     * 创建运费模板
     * @param cmd 创建参数
     * @return 是否创建成功
     */
    @PostMapping
    @Log(title = "商户运费模板", businessType = BusinessType.INSERT)
    public R<Boolean> create(@RequestBody MerchantShippingTemplateCreateCmd cmd) {
        return R.ok(this.merchantShippingTemplateService.create(cmd));
    }

    /**
     * 修改运费模板
     * @param cmd 修改参数
     * @return 是否修改成功
     */
    @PutMapping
    @Log(title = "商户运费模板", businessType = BusinessType.UPDATE)
    public R<Boolean> modify(@RequestBody MerchantShippingTemplateModifyCmd cmd) {
        return R.ok(this.merchantShippingTemplateService.modify(cmd));
    }

    /**
     * 删除运费模板
     * @param templateId 运费模板ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{templateId}")
    @Log(title = "商户运费模板", businessType = BusinessType.DELETE)
    public R<Boolean> delete(@PathVariable Long templateId) {
        return R.ok(this.merchantShippingTemplateService.delete(templateId));
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
        return R.ok(this.merchantShippingTemplateService.setDefault(templateId, merchantId));
    }

    /**
     * 获取商户默认运费模板
     * @param merchantId 商户ID
     * @return 默认运费模板
     */
    @GetMapping("/default/{merchantId}")
    public R<MerchantShippingTemplateCO> getDefaultByMerchantId(@PathVariable Long merchantId) {
        return R.ok(this.merchantShippingTemplateService.getDefaultByMerchantId(merchantId));
    }

}
