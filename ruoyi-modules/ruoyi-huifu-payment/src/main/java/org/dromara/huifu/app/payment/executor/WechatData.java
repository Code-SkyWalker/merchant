package org.dromara.huifu.app.payment.executor;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 微信支付参数
 * @Author Code Skywalker
 * @Date 2025/11/13 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WechatData {
    private String sub_appid;
    private String sub_openid;
    private String attach;
    private String body;
    private List<CommodityDetail> goods_detail;
    private String device_info;
    private String goods_tag;
    private String identity;
    private String receipt;
    private SceneInfo scene_info;
    private String spbill_create_ip;
    private String promotion_flag;
    private String product_id;
    private String limit_payer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class SceneInfo {
        private StoreInfo store_info;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class StoreInfo {
        private String id;
        private String name;
        private String area_code;
        private String address;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
