package org.dromara.merchant.adapter.web.merchant;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.merchant.app.merchant.IMerchantDeliveryConfigService;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public R<Boolean> create(@Validated MerchantDeliveryConfigCreateCmd cmd) {
        return R.ok(configService.create(cmd));
    }
}
