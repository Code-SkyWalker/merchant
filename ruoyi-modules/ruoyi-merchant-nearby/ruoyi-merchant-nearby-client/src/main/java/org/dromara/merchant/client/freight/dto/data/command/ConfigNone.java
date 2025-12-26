package org.dromara.merchant.client.freight.dto.data.command;

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
public class ConfigNone implements DeliveryConfig {

    /**
     * 无配送配置信息
     */
    private List<NoneDeliveryInfo> noneDeliveryInfo;


    @Data
    public static class NoneDeliveryInfo {
        /**
         * 无配送配置信息名称
         */
        private String infoName;

        /**
         * 是否显示
         */
        private Boolean show;

        /**
         * 是否必填
         */
        private Boolean required;
    }
}
