package org.dromara.huifu.client.api.pay;

import com.huifu.bspay.sdk.opps.client.BasePayClient;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.*;
import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.dromara.common.core.enums.FormatsType.YYYYMMDD;
import static org.dromara.common.core.enums.FormatsType.dateTimeFormatter;

/**
 * @Description 聚合支付
 * @Author Code Skywalker
 * @Date 2025-11-10 14:37
 */
@Component
public class AggregatePayment {

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
     *
     * @param huifuId        商户号
     * @param org_req_seq_id 原交易请求号
     * @param org_req_date   原交易请求日期
     * @param refundAmt      退款金额
     */
    public Map<String, Object> refund(Long huifuId, String org_req_seq_id, LocalDateTime org_req_date, BigDecimal refundAmt) throws BasePayException, IllegalAccessException {
        V3TradePaymentScanpayRefundRequest request = new V3TradePaymentScanpayRefundRequest();
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setReqSeqId(SequenceTools.getReqSeqId32());

        request.setHuifuId(huifuId.toString());
        request.setOrdAmt(refundAmt.toString());
        request.setExtendInfo(Map.of("org_req_seq_id", org_req_seq_id, "org_req_date", org_req_date.format(dateTimeFormatter(YYYYMMDD))));

        return BasePayClient.request(request);
    }


    /**
     * 退款查询
     *
     * @param request {@link V3TradePaymentScanpayRefundqueryRequest}
     */
    public Map<String, Object> queryRefund(V3TradePaymentScanpayRefundqueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 手续费计算
     *
     * @param request {@link V2TradeFeecalcRequest}
     */
    public Map<String, Object> feeCalc(V2TradeFeecalcRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 延迟交易确认
     *
     * @param huifuId        商户号
     * @param org_req_seq_id 原交易请求号
     * @param org_req_date   原交易请求日期
     */
    public Map<String, Object> delayTransConfirm(Long huifuId, String org_req_seq_id, LocalDateTime org_req_date) throws BasePayException, IllegalAccessException {
        V2TradePaymentDelaytransConfirmRequest request = new V2TradePaymentDelaytransConfirmRequest();
        request.setHuifuId(huifuId.toString());
        request.setExtendInfo(Map.of("org_req_seq_id", org_req_seq_id, "org_req_date", org_req_date.format(dateTimeFormatter(YYYYMMDD))));
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setReqSeqId(SequenceTools.getReqSeqId32());
        return BasePayClient.request(request);
    }

    /**
     * 延迟交易确认退款
     *
     * @param huifuId        商户号
     * @param org_req_seq_id 指交易确认请求流水号
     * @param org_req_date   指交易确认请求日期
     */
    public Map<String, Object> delayTransConfirmRefund(Long huifuId, String org_req_seq_id, LocalDateTime org_req_date) throws BasePayException, IllegalAccessException {
        V2TradePaymentDelaytransConfirmrefundRequest request = new V2TradePaymentDelaytransConfirmrefundRequest();
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setReqSeqId(SequenceTools.getReqSeqId32());

        request.setHuifuId(huifuId.toString());
        request.setOrgReqDate(org_req_date.format(dateTimeFormatter(YYYYMMDD)));
        request.setOrgReqSeqId(org_req_seq_id);
        return BasePayClient.request(request);
    }
}
