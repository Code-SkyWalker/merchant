package org.dromara.merchant.domain.merchant.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.hutool.json.JSONObject;

/**
 * @Description 自提配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigPickup implements DeliveryConfig {

    private Object reservationConfig;

    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }
}
