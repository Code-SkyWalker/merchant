package org.dromara.merchant.domain.freight.model;

import cn.hutool.json.JSONObject;
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
public class DeliveryConfigPickup implements DeliveryConfig {

    private final String type = "pickup";

    private Object reservationConfig;

    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Override
    public String type() {
        return this.type;
    }
}
