package org.dromara.huifu.client.api.pay;

import com.huifu.bspay.sdk.opps.client.BasePayClient;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.*;
import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 聚合支付
 * @Author Code Skywalker
 * @Date 2025-11-10 14:37
 */
@Component
public class AggregatePayment {


    public static final String NOTIFY_URL = "prod-api/api/payment/huifu/pay/notify";

    /**
     * 创建聚合支付订单
     *
     * @param request {@link V3TradePaymentJspayRequest}
     * @return 响应参数
     */
    public Map<String, Object> createPaymentOrder(V3TradePaymentJspayRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 交易查询
     *
     * @param request {@link V3TradePaymentScanpayQueryRequest}
     */
    public Map<String, Object> queryPaymentOrder(V3TradePaymentScanpayQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 交易关单
     *
     * @param request {@link V2TradePaymentScanpayCloseRequest}
     */
    public Map<String, Object> closePaymentOrder(V2TradePaymentScanpayCloseRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 交易关单查询
     *
     * @param request {@link V2TradePaymentScanpayClosequeryRequest}
     */
    public Map<String, Object> queryClosePaymentOrder(V2TradePaymentScanpayClosequeryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 交易退款
     * @param request {@link V3TradePaymentScanpayRefundRequest}
     */
    public Map<String, Object> refund(V3TradePaymentScanpayRefundRequest request) throws BasePayException, IllegalAccessException {
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setReqSeqId(SequenceTools.getReqSeqId32());
        return BasePayClient.request(request);
    }

    /**
     * 退款查询
     * @param request {@link V3TradePaymentScanpayRefundqueryRequest}
     */
    public Map<String, Object> queryRefund(V3TradePaymentScanpayRefundqueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 手续费计算
     * @param request {@link V2TradeFeecalcRequest}
     */
    public Map<String, Object> feeCalc(V2TradeFeecalcRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }
}
