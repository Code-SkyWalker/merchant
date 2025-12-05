package org.dromara.merchant.domain.merchant.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * @Description 无配送配置
 * @Author Code Skywalker
 * @Date 2025/12/4 15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryConfigNone implements DeliveryConfig {

    private List<NoneDeliveryInfo> noneDeliveryInfo;


    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Data
    public static class NoneDeliveryInfo {
        private String infoName;
        private Boolean show;
        private Boolean required;
    }
}
