package org.dromara.merchant.adapter.web.commodity;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.commodity.IParaService;
import org.dromara.merchant.client.commodity.dto.data.command.ParaCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.ParaModifyCmd;
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

}
