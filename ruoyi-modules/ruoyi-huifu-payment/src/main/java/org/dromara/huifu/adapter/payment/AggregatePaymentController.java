package org.dromara.huifu.adapter.payment;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2TradePaymentScanpayCloseRequest;
import com.huifu.bspay.sdk.opps.core.request.V2TradePaymentScanpayClosequeryRequest;
import com.huifu.bspay.sdk.opps.core.request.V3TradePaymentScanpayQueryRequest;
import com.huifu.bspay.sdk.opps.core.request.V3TradePaymentScanpayRefundRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.common.core.domain.R;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.huifu.app.payment.service.HuifuCallbackHandler;
import org.dromara.huifu.client.api.pay.AggregatePayment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 汇付支付相关接口
 *
 * @Author Code Skywalker
 * @Date 2025-11-11 15:13
 */
@Slf4j
@RestController
@RequestMapping("/payment/huifu/pay")
@RequiredArgsConstructor
public class AggregatePaymentController {

    private static final Pattern JSON_ARRAY_PATTERN = Pattern.compile("^\\[\"(.*)\"\\]$");

    private final AggregatePayment aggregatePayment;

    private final HuifuCallbackHandler huifuCallbackHandler;

    @PostMapping("/notify")
    public String paymentCallBack(HttpServletRequest request) {
        // 回调数据resp_data
        String data = request.getParameter("resp_data");
        // 回调数据sign
        String sign = request.getParameter("sign");

        // 校验必要参数
        if (StringUtils.isBlank(data)) {
            log.error("支付回调参数resp_data为空");
            return "FAIL";
        }

        try {
            // 处理数组格式的resp_data参数
            String jsonData = extractJsonFromResponseData(data);

            // 校验JSON格式
            if (!JsonUtils.isJsonObject(jsonData)) {
                log.error("支付回调数据格式不正确，不是有效的JSON对象: {}", jsonData);
                return "FAIL";
            }

            JSONObject data_obj = JSONUtil.parseObj(jsonData);

            // 校验必要字段
            if (!data_obj.containsKey("req_seq_id")) {
                log.error("支付回调数据缺少必要字段req_seq_id: {}", data_obj);
                return "FAIL";
            }

            huifuCallbackHandler.paymentCallBack(data_obj);

            String reqSeqId = data_obj.get("req_seq_id").toString();
            return "RECV_ORD_ID_" + reqSeqId;
        } catch (Exception e) {
            log.error("处理支付回调异常: {}", e.getMessage(), e);
            return "FAIL";
        }
    }

    private String extractJsonFromResponseData(String responseData) {
        if (StringUtils.isBlank(responseData)) {
            return responseData;
        }

        // 处理数组格式: ["\"{...}\""]
        Matcher matcher = JSON_ARRAY_PATTERN.matcher(responseData.trim());
        if (matcher.matches()) {
            String jsonStr = matcher.group(1);
            // 移除转义的引号
            return jsonStr.replace("\\\"", "\"");
        }

        // 如果不是数组格式，直接返回
        return responseData;
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
    public R<Map<String, Object>> closePaymentOrder(@RequestBody V2TradePaymentScanpayCloseRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.closePaymentOrder(request));
    }

    /**
     * 交易关单查询
     *
     * @param request 关单查询请求
     * @return 关单查询结果
     */
    @PostMapping("/queryClosePaymentOrder")
    public R<Map<String, Object>> queryClosePaymentOrder(@RequestBody V2TradePaymentScanpayClosequeryRequest request) throws BasePayException, IllegalAccessException {
        return R.ok(aggregatePayment.queryClosePaymentOrder(request));
    }

    /**
     * 交易退款
     *
     * @param request 退款请求
     * @return 退款结果
     */
    @PostMapping("/refund")
    public R<Map<String, Object>> refundPaymentOrder(@RequestBody V3TradePaymentScanpayRefundRequest request) throws BasePayException, IllegalAccessException {
        Map<String, Object> refund = aggregatePayment.refund(request);
        // TODO: 处理退款结果，例如记录日志、更新数据库等

        return R.ok(refund);
    }

}
