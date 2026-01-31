package org.dromara.huifu.client.api.pay;

import com.huifu.bspay.sdk.opps.client.BasePayClient;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2TradeWxusermarkQueryRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 微信支付
 * @Author Code Skywalker
 * @Date 2025-11-10 15:20
 */
@Component
public class WeChatPay {

    /**
     * 微信用户标记查询
     * @param request {@link V2TradeWxusermarkQueryRequest}
     */
    public Map<String, Object> wxUserMark(V2TradeWxusermarkQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }
}
