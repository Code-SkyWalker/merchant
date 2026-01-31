package org.dromara.huifu.app.payment.executor;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 支付宝支付参数
 * @Author Code Skywalker
 * @Date 2025/11/13 10:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AlipayData {
    private String alipay_store_id;
    private String buyer_id;
    private String buyer_logon_id;
    private ExtendParams extend_params;
    private List<CommodityDetail> goods_detail;
    private String merchant_order_no;
    private String operator_id;
    private String product_code;
    private String seller_id;
    private String store_id;
    private ExtUserInfo ext_user_info;
    private String subject;
    private String store_name;
    private String op_app_id;
    private String ali_business_params;
    private String body;
    private String ali_promo_params;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ExtendParams {
        private String card_type;
        private String food_order_type;
        private String hb_fq_num;
        private String hb_fq_seller_percent;
        private String industry_reflux_info;
        private String fq_channels;
        private String parking_id;
        private String sys_service_provider_id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ExtUserInfo {
        private String name;
        private String mobile;
        private String cert_type;
        private String cert_no;
        private String min_age;
        private String fix_buyer;
        private String need_check_info;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
