package org.dromara.merchant.app.merchant;

import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:42
 */
public interface IMerchantDeliveryConfigService {

    boolean create(MerchantDeliveryConfigCreateCmd cmd);
}
