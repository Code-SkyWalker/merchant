package org.dromara.merchant.domain.freight.model;

import cn.hutool.json.JSONObject;
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

    private final String type = "none";

    /**
     * 无配送配置信息
     */
    private List<NoneDeliveryInfo> noneDeliveryInfo;


    @Override
    public String toJson() {
        return new JSONObject(this).toString();
    }

    @Override
    public String type() {
        return this.type;
    }

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
