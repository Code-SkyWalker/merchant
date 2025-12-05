package org.dromara.merchant.adapter.web.freight;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.freight.IFreightConfigService;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.model.FreightConfig;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.FreightConfigDO;
import org.dromara.merchant.infrastructure.freight.converter.FreightConfigConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.FreightConfigMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商城配送设置
 * @Author Code Skywalker
 * @Date 2025/12/4 17:44
 */
@Validated
@RestController
@RequestMapping("/merchant/freight/config")
@RequiredArgsConstructor
public class FreightConfigController {

    private final IFreightConfigService configService;

    private final FreightConfigMapper mapper;
    private final FreightConfigConvertor convertor;

    @PostMapping
    public R<Boolean> create(@Validated @RequestBody FreightConfigCreateCmd cmd) {
        return R.ok(configService.create(cmd));
    }

    @GetMapping("/{configId}")
    public R<FreightConfig> create(@PathVariable Long configId) {

        FreightConfigDO configDO = mapper.selectById(configId);

        FreightConfig entity = this.convertor.toMerchantDeliveryConfigEntity(configDO);
        return R.ok(entity);
    }
}
