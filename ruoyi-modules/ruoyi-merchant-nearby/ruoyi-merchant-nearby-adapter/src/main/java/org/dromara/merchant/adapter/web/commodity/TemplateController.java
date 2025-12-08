package org.dromara.merchant.adapter.web.commodity;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.commodity.ITemplateService;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.TemplateModifyCmd;
import org.springframework.web.bind.annotation.*;

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
    public R<Boolean> addTemplate(@RequestBody final TemplateCreateCmd cmd) {
        return R.ok(templateService.create(cmd));
    }

    /**
     * 修改商品模板
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifyTemplate(@RequestBody final TemplateModifyCmd cmd) {
        return R.ok(templateService.modify(cmd));
    }

    /**
     * 删除商品模板
     * @param id ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R<Boolean> deleteTemplate(@PathVariable final Integer id) {
        return R.ok(templateService.delete(id));
    }

}
