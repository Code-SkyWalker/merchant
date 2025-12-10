package org.dromara.merchant.adapter.web.commodity;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.commodity.ISpuService;
import org.dromara.merchant.client.commodity.dto.data.command.SpuCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.SpuModifyCmd;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加商品
     * @param cmd 添加参数
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addSpu(@RequestBody final SpuCreateCmd cmd) {
        return R.ok(spuService.create(cmd));
    }

    /**
     * 修改商品
     * @param cmd 修改参数
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifySpu(@RequestBody final SpuModifyCmd cmd) {
        return R.ok(spuService.modify(cmd));
    }

    /**
     * 删除商品
     * @param id ID
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R<Boolean> deleteSpu(@PathVariable final Long id) {
        return R.ok(spuService.delete(id));
    }

}
