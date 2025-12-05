package org.dromara.merchant.adapter.web.merchant;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.merchant.IMerchantDeliveryConfigService;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantDeliveryConfig;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantDeliveryConfigConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantDeliveryConfigMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDeliveryConfigDO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:44
 */
@Validated
@RestController
@RequestMapping("/merchant/delivery/config")
@RequiredArgsConstructor
public class MerchantDeliveryConfigController {

    private final IMerchantDeliveryConfigService configService;

    private final MerchantDeliveryConfigMapper mapper;
    private final MerchantDeliveryConfigConvertor convertor;

    @PostMapping
    public R<Boolean> create(@Validated @RequestBody MerchantDeliveryConfigCreateCmd cmd) {
        return R.ok(configService.create(cmd));
    }

    @GetMapping("/{configId}")
    public R<MerchantDeliveryConfig> create(@PathVariable Long configId) {

        MerchantDeliveryConfigDO configDO = mapper.selectById(configId);

        MerchantDeliveryConfig entity = this.convertor.toMerchantDeliveryConfigEntity(configDO);
        return R.ok(entity);
    }
}
