package org.dromara.merchant.interfaces.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.web.core.BaseController;
import org.dromara.merchant.app.merchant.IMerchantShippingAreaService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingAreaCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantShippingAreaPageQry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 商户配送区域Controller
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Api(tags = "商户配送区域管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchant/shipping-area")
public class MerchantShippingAreaController extends BaseController {

    private final IMerchantShippingAreaService merchantShippingAreaService;

    /**
     * 创建商户配送区域
     */
    @ApiOperation("创建商户配送区域")
    @PostMapping
    public R<Boolean> create(@Validated(AddGroup.class) @RequestBody MerchantShippingAreaCreateCmd cmd) {
        return R.ok(merchantShippingAreaService.create(cmd));
    }

    /**
     * 修改商户配送区域
     */
    @ApiOperation("修改商户配送区域")
    @PutMapping
    public R<Boolean> modify(@Validated(EditGroup.class) @RequestBody MerchantShippingAreaModifyCmd cmd) {
        return R.ok(merchantShippingAreaService.modify(cmd));
    }

    /**
     * 删除商户配送区域
     */
    @ApiOperation("删除商户配送区域")
    @DeleteMapping("/{areaId}")
    public R<Boolean> remove(@PathVariable Long areaId) {
        return R.ok(merchantShippingAreaService.deleteById(areaId));
    }

    /**
     * 查询商户配送区域详情
     */
    @ApiOperation("查询商户配送区域详情")
    @GetMapping("/{areaId}")
    public R<MerchantShippingAreaCO> detail(@PathVariable Long areaId) {
        return R.ok(merchantShippingAreaService.getDetail(areaId));
    }

    /**
     * 分页查询商户配送区域
     */
    @ApiOperation("分页查询商户配送区域")
    @GetMapping("/page")
    public R<Page<MerchantShippingAreaCO>> page(MerchantShippingAreaPageQry qry, PageQuery pageQuery) {
        return R.ok(merchantShippingAreaService.getPage(qry, pageQuery));
    }

    /**
     * 根据模板ID查询所有配送区域
     */
    @ApiOperation("根据模板ID查询所有配送区域")
    @GetMapping("/list/{templateId}")
    public R<List<MerchantShippingAreaCO>> listByTemplateId(@PathVariable Long templateId) {
        return R.ok(merchantShippingAreaService.getListByTemplateId(templateId));
    }
}