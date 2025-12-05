package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @Data
    public static class NoneDeliveryInfo {
        private String infoName;
        private Boolean show;
        private Boolean required;
    }
}