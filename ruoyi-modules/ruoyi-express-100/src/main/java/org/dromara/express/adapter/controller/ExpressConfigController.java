package org.dromara.express.adapter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.express.app.IConfigService;
import org.dromara.express.client.dto.data.clientobject.ConfigCO;
import org.dromara.express.client.dto.data.command.ConfigCreateCmd;
import org.dromara.express.client.dto.data.command.ConfigModifyCmd;
import org.dromara.express.infrastructure.mapper.ConfigMapper;
import org.springframework.web.bind.annotation.*;


/**
 * 快递配置
 */
@RestController
@RequestMapping("/express/config")
@RequiredArgsConstructor
public class ExpressConfigController {

    private final IConfigService configService;

    private final ConfigMapper configMapper;

    /**
     * 根据商户ID查询快递配置
     */
    @GetMapping("/{merchantId}")
    public R<ConfigCO> queryConfigByMerchantId(@PathVariable Long merchantId) {
        return R.ok(configMapper.queryByMerchantId(merchantId));
    }

    /**
     * 新增快递配置
     */
    @PostMapping
    public R<Boolean> addExpressConfig(@Valid @RequestBody ConfigCreateCmd cmd) {
        return R.ok(configService.createConfig(cmd));
    }

    /**
     * 修改快递配置
     */
    @PutMapping
    public R<Boolean> updateExpressConfig(@Valid @RequestBody ConfigModifyCmd cmd) {
        return R.ok(configService.modifyConfig(cmd));
    }

    /**
     * 删除快递配置
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteExpressConfig(@PathVariable Long id) {
        return R.ok(configService.deleteConfig(id));
    }
}
