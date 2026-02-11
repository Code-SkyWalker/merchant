package org.dromara.merchant.adapter.web.merchant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.merchant.IMerchantService;
import org.dromara.merchant.app.merchant.service.MerchantGeoCacheService;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantCO;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantCreateCmd;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantModifyCmd;
import org.dromara.merchant.client.merchant.dto.data.command.query.MerchantPageQry;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商家信息接口
 *
 * @Author Code Skywalker
 * @Date 2025/12/1 14:10
 */
@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final IMerchantService merchantService;

    private final MerchantMapper merchantMapper;

    private final MerchantGeoCacheService merchantGeoCacheService;

    /**
     * 商家详情
     *
     * @param merchantId 商家ID
     * @return 商家信息
     */
    @GetMapping("/{merchantId}")
    public R<MerchantCO> queryById(@PathVariable Long merchantId) {
        MerchantCO merchantCO = this.merchantService.queryCOById(merchantId);
        return R.ok(merchantCO);
    }

    /**
     * 商家列表
     *
     * @param query 查询参数
     * @param page  分页参数
     * @return 商家列表
     */
    @GetMapping("/pages")
    public TableDataInfo<MerchantCO> queryPage(@ModelAttribute MerchantPageQry query, @ModelAttribute PageQuery page) {
        Page<MerchantCO> pages = this.merchantService.queryPage(query, page);
        return TableDataInfo.build(pages);
    }

    /**
     * 创建商家
     *
     * @param cmd 创建参数
     * @return 是否创建成功
     */
    @PostMapping
    public R<Boolean> create(@RequestBody MerchantCreateCmd cmd) {
        return R.ok(this.merchantService.create(cmd));
    }

    /**
     * 修改商家
     *
     * @param cmd 修改参数
     * @return 是否修改成功
     */
    @PutMapping
    public R<Boolean> modify(@RequestBody MerchantModifyCmd cmd) {
        return R.ok(this.merchantService.modify(cmd));
    }

    /**
     * 删除商家
     *
     * @param merchantId 商家ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{merchantId}")
    public R<Boolean> delete(@PathVariable Long merchantId) {
        return R.ok(this.merchantService.delete(merchantId));
    }

    /**
     * 前端查询-分类
     *
     * @param categoryId 分类ID
     * @param page       分页参数
     * @return 商家列表
     */
    @GetMapping("/frontend/{categoryId}/pages")
    public TableDataInfo<MerchantCO> queryByCategoryIdPages(@PathVariable Long categoryId, @ModelAttribute PageQuery page) {
        Page<MerchantCO> merchantCOs = this.merchantMapper.queryByCategoryIdPages(page.build(), categoryId);
        return TableDataInfo.build(merchantCOs);
    }

    /**
     * 前端查询-本地
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    搜索半径公里
     * @return 附近商家列表
     */
    @GetMapping("/frontend/nearby")
    public TableDataInfo<MerchantCO> queryNearby(@RequestParam Double longitude, @RequestParam Double latitude,
                                                 @RequestParam(required = false, defaultValue = "5") Double radius, @ModelAttribute PageQuery page,
                                                 @RequestParam(required = false) Long categoryId) {
        List<Long> nearbyMerchants = this.merchantGeoCacheService.searchNearbyMerchants(longitude, latitude, radius);

        if (nearbyMerchants.isEmpty()) return TableDataInfo.build();

        Page<MerchantCO> merchantCOs = this.merchantMapper.queryByIdsPages(page.build(), nearbyMerchants, categoryId);

        return TableDataInfo.build(merchantCOs);
    }

    /**
     * 前端查询-购物
     *
     * @param merchantName 商家名称
     * @return 附近商家列表
     */
    @GetMapping("/front")
    public TableDataInfo<MerchantCO> queryNearby(@ModelAttribute PageQuery page, @RequestParam(required = false) String merchantName) {
        Page<MerchantCO> merchantCOs = this.merchantMapper.queryPagesFrontend(page.build(), merchantName);
        return TableDataInfo.build(merchantCOs);
    }


}
