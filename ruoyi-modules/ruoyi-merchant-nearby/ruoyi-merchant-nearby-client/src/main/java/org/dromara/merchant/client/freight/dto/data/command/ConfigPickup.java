package org.dromara.merchant.client.freight.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 自提配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigPickup implements DeliveryConfig {

    private Object reservationConfig;

}
