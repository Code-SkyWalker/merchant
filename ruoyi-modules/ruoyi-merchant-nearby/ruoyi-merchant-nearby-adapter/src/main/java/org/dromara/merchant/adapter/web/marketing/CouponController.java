package org.dromara.merchant.adapter.web.marketing;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.merchant.app.marketing.ICouponService;
import org.dromara.merchant.client.marketing.dto.data.client.CouponCO;
import org.dromara.merchant.client.marketing.dto.data.client.CouponPageCO;
import org.dromara.merchant.client.marketing.dto.data.command.CouponCreateCmd;
import org.dromara.merchant.client.marketing.dto.data.command.CouponModifyCmd;
import org.dromara.merchant.client.marketing.dto.data.command.query.CouponPageQry;
import org.dromara.merchant.infrastructure.marketing.mapper.CouponMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券控制器
 * @Author Code Skywalker
 * @Date 2025/12/30 10:23
 */
@Validated
@RestController
@RequestMapping("/merchant/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService couponService;
    private final CouponMapper couponMapper;

    /**
     * 前端查询 - 根据商品ID查询优惠券
     * @param spuId 商品ID
     * @return 优惠券列表
     */
    @GetMapping("/frontend/{spuId}")
    public R<List<CouponCO>> queryCouponsBySpuId(@PathVariable final Long spuId) {
        return R.ok(this.couponMapper.queryCouponBySpuId(spuId));
    }

    /**
     * 添加优惠券
     * @param cmd 添加优惠券命令
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addCoupon(@RequestBody final CouponCreateCmd cmd) {
        boolean couponCreated = this.couponService.create(cmd);
        return couponCreated ? R.ok(true) : R.fail("创建失败");
    }

    /**
     * 修改优惠券
     * @param cmd 修改优惠券命令
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> modifyCoupon(@RequestBody final CouponModifyCmd cmd) {
        boolean couponModified = this.couponService.modify(cmd);
        return couponModified ? R.ok(true) : R.fail("修改失败");
    }

    /**
     * 删除优惠券
     * @param id 优惠券ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteCoupon(@PathVariable final Long id) {
        boolean couponDeleted = this.couponService.delete(id);
        return couponDeleted ? R.ok(true) : R.fail("删除失败");
    }

    /**
     * 主键查询优惠券
     * @param id 优惠券ID
     * @return 优惠券信息
     */
    @GetMapping("/{id}")
    public R<CouponCO> queryCoupon(@PathVariable final Long id) {
        return R.ok(this.couponMapper.selectByCouponId(id));
    }

    /**
     * 列表查询优惠券
     * @return 优惠券列表
     */
    @GetMapping("/page")
    public TableDataInfo<CouponPageCO> queryCouponPages(@ModelAttribute final CouponPageQry qry, @ModelAttribute PageQuery page) {
        return TableDataInfo.build(this.couponMapper.queryPages(qry, page.build()));
    }


}
