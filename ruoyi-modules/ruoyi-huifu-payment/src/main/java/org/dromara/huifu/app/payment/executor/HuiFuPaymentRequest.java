package org.dromara.huifu.app.payment.executor;

import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/12 13:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HuiFuPaymentRequest {

    /** 微信公众号（pc、h5） */
    public static final String TRADE_TYPE_WX_JSAPI = "T_JSAPI";
    /** 微信小程序 (微信小程序、app） */
    public static final String TRADE_TYPE_WX_MINIAPP = "T_MINIAPP";
    /** 支付宝JS（pc、支付宝小程序） */
    public static final String TRADE_TYPE_AL_JSAPI = "A_JSAPI";
    /** 支付宝正扫（app，h5） */
    public static final String TRADE_TYPE_AL_NATIVE = "A_NATIVE";
    /** 银联正扫 */
    public static final String TRADE_TYPE_U_NATIVE = "U_NATIVE";
    /** 银联JS */
    public static final String TRADE_TYPE_U_JSAPI = "U_JSAPI";


    private String reqDate = DateTools.getCurrentDateYYYYMMDD();
    private String reqSeqId = SequenceTools.getReqSeqId32();
    private Long tenantId;
    private String shopId;
    private String huifuId;
    private String tradeType;
    private String goodsDesc;
    private String transAmt;

    private String inner_order_id;

    private String sub_appid;
    private String sub_openid;

    private List<CommodityDetail> commodityDetail;

    private Map<String, Object> extendInfos = Collections.emptyMap();
}
