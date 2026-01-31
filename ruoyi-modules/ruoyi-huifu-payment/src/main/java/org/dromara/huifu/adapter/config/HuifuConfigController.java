package org.dromara.huifu.adapter.config;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.huifu.app.config.service.IHuifuConfigService;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigCreateCmd;
import org.dromara.huifu.client.config.dto.cmd.HuifuConfigModifyCmd;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:23
 */
@RestController
@RequestMapping("/payment/huifu/config")
@RequiredArgsConstructor
public class HuifuConfigController {

    private final IHuifuConfigService huifuConfigService;

    /**
     * 创建汇付配置
     */
    @PostMapping
    public R<Boolean> create(@RequestBody HuifuConfigCreateCmd cmd) {
        boolean created = this.huifuConfigService.create(cmd);
        return created ? R.ok(true) : R.fail();
    }

    /**
     * 修改汇付配置
     */
    @PutMapping
    public R<Boolean> create(@RequestBody HuifuConfigModifyCmd cmd) {
        boolean modified = this.huifuConfigService.modify(cmd);
        return modified ? R.ok(true) : R.fail();
    }

    /**
     * 删除汇付配置
     */
    @DeleteMapping("/{huifuId}")
    public R<Boolean> delete(@PathVariable Long huifuId) {
        boolean deleted = this.huifuConfigService.delete(huifuId);
        return deleted ? R.ok(true) : R.fail();
    }

    /**
     * 根据商户id查询汇付配置
     */
    @GetMapping("/query/merchant")
    public R<HuifuConfig> queryByMerchantId(@RequestParam Long merchantId) {
        return R.ok(this.huifuConfigService.queryByMerchantId(merchantId));
    }

    /**
     * 根据商户id查询汇付配置
     */
    @GetMapping("/query/tenant")
    public R<HuifuConfig> queryByTenantId(@RequestParam Long tenantId) {
        return R.ok(this.huifuConfigService.queryByTenantId(tenantId));
    }

    /**
     * 根据商户号或用户子账号查询汇付配置
     */
    @GetMapping("/query/channel")
    public R<?> queryChannel() {
        return R.ok(this.huifuConfigService.queryChannelConfig());
    }


}
