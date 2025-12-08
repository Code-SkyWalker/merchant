package org.dromara.merchant.adapter.web.commodity;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.commodity.IBrandService;
import org.dromara.merchant.client.commodity.dto.data.command.BrandCreateCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandDeleteCmd;
import org.dromara.merchant.client.commodity.dto.data.command.BrandModifyCmd;
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
    public R<Boolean> deleteBrand(@RequestBody final BrandDeleteCmd cmd) {
        boolean delete = brandService.delete(cmd);
        return R.ok(delete);
    }

}
