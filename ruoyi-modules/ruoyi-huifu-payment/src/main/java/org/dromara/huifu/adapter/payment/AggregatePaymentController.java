package org.dromara.huifu.adapter.payment;

import com.alibaba.fastjson.JSONObject;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2TradePaymentScanpayCloseRequest;
import com.huifu.bspay.sdk.opps.core.request.V2TradePaymentScanpayClosequeryRequest;
import com.huifu.bspay.sdk.opps.core.request.V3TradePaymentScanpayQueryRequest;
import com.huifu.bspay.sdk.opps.core.request.V3TradePaymentScanpayRefundRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.huifu.app.payment.service.HuifuCallbackHandler;
import org.dromara.huifu.client.api.pay.AggregatePayment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 汇付支付相关接口
 * @Author Code Skywalker
 * @Date 2025-11-11 15:13
 */
@RestController
@RequestMapping("/payment/huifu/pay")
@RequiredArgsConstructor
public class AggregatePaymentController {

    private final AggregatePayment aggregatePayment;

    private final HuifuCallbackHandler huifuCallbackHandler;

    @PostMapping("/notify")
    public String paymentCallBack(HttpServletRequest request) {
        // 回调数据resp_data
        String data = request.getParameter("resp_data");
        // 回调数据sign
        String sign = request.getParameter("sign");

        JSONObject data_object = JSONObject.parseObject(data);

        huifuCallbackHandler.paymentCallBack(data_object);

        String reqSeqId = data_object.getString("req_seq_id");
        return "RECV_ORD_ID_" + reqSeqId;
    }

    /**
     * 交易查询
     *
     * @param request 交易查询请求
     * @return 交易查询结果
     */
    @PostMapping("/queryPaymentOrder")
    public R<Map<String, Object>> queryPaymentOrder(@RequestBody V3TradePaymentScanpayQueryRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.queryPaymentOrder(request));
    }

    /**
     * 交易关单
     *
     * @param request 关单请求
     * @return 关单结果
     */
    @PostMapping("/closePaymentOrder")
    public R closePaymentOrder(@RequestBody V2TradePaymentScanpayCloseRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.closePaymentOrder(request));
    }

    /**
     * 交易关单查询
     *
     * @param request 关单查询请求
     * @return 关单查询结果
     */
    @PostMapping("/queryClosePaymentOrder")
    public R queryClosePaymentOrder(@RequestBody V2TradePaymentScanpayClosequeryRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.queryClosePaymentOrder(request));
    }

    /**
     * 交易退款
     *
     * @param request 退款请求
     * @return 退款结果
     */
    @PostMapping("/refund")
    public R refundPaymentOrder(@RequestBody V3TradePaymentScanpayRefundRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.refund(request));
    }

}
