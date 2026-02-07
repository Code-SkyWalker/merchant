package org.dromara.merchant.adapter.web.freight;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.freight.IFreightConfigService;
import org.dromara.merchant.app.freight.executor.query.FreightConfigQryExe;
import org.dromara.merchant.client.freight.dto.data.clientobject.FreightConfigCO;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商家配送设置
 * @Author Code Skywalker
 * @Date 2025/12/4 17:44
 */
@Validated
@RestController
@RequestMapping("/merchant/freight/config")
@RequiredArgsConstructor
public class FreightConfigController {

    private final IFreightConfigService configService;

    private final FreightConfigQryExe configQryExe;

    /**
     * 创建配送设置
     * @param cmd 配送设置参数
     * @return 创建结果
     */
    @PostMapping
    public R<Long> create(@Validated @RequestBody FreightConfigCreateCmd cmd) {
        return R.ok(configService.create(cmd));
    }

    /**
     * 获取配送设置
     * @param merchantId 配送设置ID
     * @return 配送设置
     */
    @GetMapping(value = "/{merchantId}")
    public R<FreightConfigCO> queryConfig(@PathVariable Long merchantId, @RequestParam String deliveryMethod) {
        FreightConfigCO expressConfig = configQryExe.execute(merchantId, DeliveryMethod.valueOf(deliveryMethod));
        return R.ok(expressConfig);
    }
}
