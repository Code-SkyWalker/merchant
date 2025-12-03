package org.dromara.system.controller.system;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.domain.bo.SysDistrictBo;
import org.dromara.system.domain.vo.SysDistrictVo;
import org.dromara.system.service.ISysDistrictService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/3 09:52
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/district")
public class SysDistrictController extends BaseController {

    private final ISysDistrictService sysDistrictService;

    /**
     * 查询行政区划列表
     */
    @GetMapping("/list")
    public TableDataInfo<SysDistrictVo> list(SysDistrictBo bo, PageQuery pageQuery) {
        return sysDistrictService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取行政区划详细信息
     *
     * @param districtId 行政区划ID
     */
    @GetMapping("/{districtId}")
    public R<SysDistrictVo> getInfo(@PathVariable("districtId") Long districtId) {
        return R.ok(sysDistrictService.queryById(districtId));
    }

    /**
     * 根据父级ID查询子行政区划
     */
    @GetMapping("/listByParentId")
    public R<List<SysDistrictVo>> listByParentId(@RequestParam(value = "parentId", defaultValue = "100000") Long parentId) {
        return R.ok(sysDistrictService.selectDistrictListByParentId(parentId));
    }

    /**
     * 同步高德地图行政区划数据
     */
    @Log(title = "行政区划", businessType = BusinessType.UPDATE)
    @PostMapping("/syncGaodeData")
    public R<Void> syncGaodeData(@RequestParam(defaultValue = "中国") String keywords,
                                 @RequestParam(defaultValue = "3") Integer subdistrict) {
        sysDistrictService.syncDistrictDataFromGaode(keywords, subdistrict);
        return R.ok("同步成功");
    }

}
