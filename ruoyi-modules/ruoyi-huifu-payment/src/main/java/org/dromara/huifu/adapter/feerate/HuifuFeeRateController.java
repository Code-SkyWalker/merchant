package org.dromara.huifu.adapter.feerate;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.huifu.app.feerate.service.IHuifuFeeRateService;
import org.dromara.huifu.client.feerate.dto.client.RateCO;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateCreateCmd;
import org.dromara.huifu.client.feerate.dto.cmd.HuifuFeeRateModifyCmd;
import org.dromara.huifu.infrastructure.mapper.HuifuFeeRateMapper;
import org.springframework.web.bind.annotation.*;

/**
 * 分账费率
 * @Author Code Skywalker
 * @Date 2025/11/14 15:43
 */
@RestController
@RequestMapping("/payment/huifu/fee-rate")
@RequiredArgsConstructor
public class HuifuFeeRateController {

    private final IHuifuFeeRateService service;

    private final HuifuFeeRateMapper mapper;

    /**
     * 添加分账费率
     * @param cmd 分账费率创建命令
     * @return 是否添加成功
     */
    @PostMapping
    public R<Boolean> create(@RequestBody HuifuFeeRateCreateCmd cmd) {
        if (!cmd.valid()) return R.fail("比例相加必须等于100");
        boolean created = service.create(cmd);
        return created ? R.ok(true) : R.fail();
    }

    /**
     * 修改分账费率
     * @param cmd 分账费率修改命令
     * @return 是否修改成功
     */
    @PutMapping
    public R<Boolean> modify(@RequestBody HuifuFeeRateModifyCmd cmd) {
        if (!cmd.valid()) return R.fail("比例相加必须等于100");
        boolean modified = service.modify(cmd);
        return modified ? R.ok(true) : R.fail();
    }

    /**
     * 删除分账费率
      * @param merchantId 用户子账户ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{merchantId}")
    public R<Boolean> delete(@PathVariable Long merchantId) {
        boolean deleted = service.delete(merchantId);
        return deleted ? R.ok(true) : R.fail();
    }

    /**
     * 查询分账费率
      * @param merchantId 用户子账户ID
     * @return 分账费率
     */
    @GetMapping("/pages")
    public TableDataInfo<RateCO> queryByMerchantId(@RequestParam(required = false) Long merchantId, @ModelAttribute PageQuery page) {
        return TableDataInfo.build(mapper.queryRatePages(merchantId, page.build()));
    }

}
