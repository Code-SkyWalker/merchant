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


    public static void main(String[] args) {
        String json_text = "{\"acct_id\":\"F00300982\",\"acct_split_bunch\":{\"acct_infos\":[{\"acct_id\":\"F00300982\",\"div_amt\":\"0.02\",\"huifu_id\":\"6666000109133323\"}],\"fee_acct_id\":\"F00380563\",\"fee_amt\":\"0.01\",\"fee_huifu_id\":\"6666000109133323\"},\"acct_stat\":\"I\",\"atu_sub_mer_id\":\"460360228\",\"avoid_sms_flag\":\"\",\"bagent_id\":\"6666000108840829\",\"bank_code\":\"SUCCESS\",\"bank_desc\":\"交易成功\",\"bank_message\":\"交易成功\",\"bank_order_no\":\"4200002303202406139053495866\",\"bank_seq_id\":\"800448\",\"bank_type\":\"OTHERS\",\"base_acct_id\":\"F00300982\",\"batch_id\":\"240613\",\"channel_type\":\"U\",\"charge_flags\":\"758_0\",\"combinedpay_data\":[],\"combinedpay_fee_amt\":\"0.00\",\"debit_type\":\"0\",\"delay_acct_flag\":\"N\",\"div_flag\":\"0\",\"end_time\":\"20240613105518\",\"fee_amount\":\"0.01\",\"fee_amt\":\"0.01\",\"fee_flag\":1,\"fee_formula_infos\":[{\"fee_formula\":\"MAX(0.01,AMT*0.01)\",\"fee_type\":\"TRANS_FEE\"}],\"fee_rec_type\":\"1\",\"fee_type\":\"OUTSIDE\",\"gate_id\":\"VN\",\"hf_seq_id\":\"002900TOP2B240613105504P831ac139c2000000\",\"huifu_id\":\"6666000109133323\",\"is_delay_acct\":\"0\",\"is_div\":\"0\",\"maze_resp_code\":\"\",\"mer_name\":\"上海汇涵信息科技服务有限公司\",\"mer_ord_id\":\"20240613105441800448\",\"mypaytsf_discount\":\"0.00\",\"need_big_object\":true,\"notify_type\":1,\"org_auth_no\":\"\",\"org_huifu_seq_id\":\"\",\"org_trans_date\":\"\",\"out_ord_id\":\"4200002303202406139053495866\",\"out_trans_id\":\"4200002303202406139053495866\",\"party_order_id\":\"03242406133930500803797\",\"pay_amt\":\"0.02\",\"pay_scene\":\"02\",\"posp_seq_id\":\"03242406133930500803797\",\"product_id\":\"YYZY\",\"ref_no\":\"105504800448\",\"req_date\":\"20240613\",\"req_seq_id\":\"20240613105441800448\",\"resp_code\":\"00000000\",\"resp_desc\":\"交易成功\",\"risk_check_data\":{\"ip_addr\":\"192.1.1.1\"},\"risk_check_info\":{\"client_ip\":\"192.1.1.1\"},\"settlement_amt\":\"0.02\",\"sub_resp_code\":\"00000000\",\"sub_resp_desc\":\"交易成功\",\"subsidy_stat\":\"I\",\"sys_id\":\"6666000108840829\",\"trade_type\":\"T_JSAPI\",\"trans_amt\":\"0.02\",\"trans_date\":\"20240613\",\"trans_fee_allowance_info\":{\"actual_fee_amt\":\"0.01\",\"allowance_fee_amt\":\"0.00\",\"allowance_type\":\"0\",\"cur_allowance_config_infos\":{\"activity_id\":\"AYYZY202403281643407965739\",\"activity_name\":\"\",\"allowance_sys\":\"1\",\"allowance_sys_id\":\"1234567891011\",\"create_by\":\"yunlong.zhang\",\"create_time\":1711615421000,\"end_time\":\"20241201\",\"human_flag\":\"N\",\"is_delay_allowance\":\"2\",\"is_share\":\"Y\",\"merchant_group\":\"6666000109133323\",\"pos_credit_limit_amt\":\"1000.00\",\"pos_debit_limit_amt\":\"1000.00\",\"pos_limit_amt\":\"2000.00\",\"qr_limit_amt\":\"1.00\",\"start_time\":\"20240101\",\"status\":\"1\",\"total_limit_amt\":\"1900.00\",\"update_time\":1711615421000},\"no_allowance_desc\":\"8\",\"receivable_fee_amt\":\"0.01\"},\"trans_order_info\":{\"acct_id\":\"F00300982\",\"acct_stat\":\"I\",\"agent_id\":\"6666000108840829\",\"atu_sub_mer_id\":\"460360228\",\"bagent_id\":\"6666000108840829\",\"bank_mer_id\":\"W1035134718836156463\",\"bank_mer_name\":\"上海汇涵信息科技服务有限公司\",\"bank_resp_code\":\"SUCCESS\",\"bank_resp_desc\":\"交易成功\",\"bank_seq_id\":\"800448\",\"bank_type\":\"OTHERS\",\"batch_id\":\"240613\",\"card_channel_type\":\"\",\"card_sign\":\"\",\"cash_req_date\":\"20240613105504\",\"cash_resp_code\":\"000\",\"cash_resp_desc\":\"成功\",\"cash_trans_id\":\"20240613241pkafk\",\"cashier_version\":\"V2\",\"channel_code\":\"00\",\"channel_finish_time\":1718247318000,\"channel_message\":\"交易成功\",\"channel_stat\":\"S\",\"channel_type\":\"U\",\"check_cash_flag\":\"I\",\"close_trans_stat\":\"\",\"create_time\":1718247304000,\"creator\":\"\",\"credit_fee_amt\":0.00,\"credit_type\":\"\",\"db_unit\":\"2\",\"debit_fee_amt\":0.00,\"double_limit_amt\":0.00,\"fee_acct_id\":\"F00380563\",\"fee_allowance_flag\":0,\"fee_amt\":0.01,\"fee_flag\":1,\"fee_formula\":\"\",\"fee_huifu_id\":\"6666000109133323\",\"fee_real_acct_id\":\"F00380563\",\"fee_real_cust_id\":\"6666000109133323\",\"fee_rec_type\":1,\"fee_source\":\"'SERVER'\",\"fee_split_type\":\"\",\"gate_id\":\"SPIN002\",\"goods_desc\":\"微信测试商品-无线鼠标\",\"hf_seq_id\":\"002900TOP2B240613105504P831ac139c2000000\",\"huifu_id\":\"6666000109133323\",\"icc_data\":\"\",\"id\":4175314075,\"is_acct_div\":0,\"is_acct_div_param\":0,\"is_delay_acct\":0,\"is_deleted\":0,\"is_route\":\"\",\"iss_inst_id\":\"\",\"maze_bg_date\":\"\",\"maze_bg_seq_id\":\"\",\"maze_pnr_dev_id\":\"\",\"maze_resp_code\":\"\",\"maze_resp_desc\":\"\",\"mcc\":\"\",\"mer_name\":\"上海汇涵信息科技服务有限公司\",\"mer_ord_id\":\"20240613105441800448\",\"modifier\":\"\",\"modify_time\":1718247318000,\"mypaytsf_discount\":0.00,\"ord_amt\":0.02,\"ord_id\":\"202406131055040TOP2_BL2684095952\",\"org_acct_id\":\"F00300982\",\"org_auth_no\":\"\",\"org_huifu_seq_id\":\"\",\"org_trans_date\":\"\",\"out_trans_id\":\"4200002303202406139053495866\",\"pa_mer_id\":\"SSP001\",\"party_order_id\":\"03242406133930500803797\",\"pay_amt\":0.02,\"pay_channel\":\"T\",\"pay_channel_id\":\"10000001\",\"pay_scene\":\"02\",\"pay_type\":\"JSAPI\",\"product_id\":\"YYZY\",\"real_acct_id\":\"F00300982\",\"real_cust_id\":\"6666000109133323\",\"real_gate_id\":\"VN\",\"ref_amt\":0.02,\"ref_cnt\":0,\"ref_fee_amt\":0.01,\"ref_num\":\"105504800448\",\"region_id\":\"TOP2_B\",\"req_date\":\"20240613\",\"req_seq_id\":\"20240613105441800448\",\"route_region_id\":\"C24_A\",\"settle_amt\":0.02,\"settle_trans_stat\":\"\",\"sn_code\":\"\",\"source_region_id\":\"TOP2_B\",\"subsidy_amt\":0.00,\"subsidy_ref_amt\":0.00,\"subsidy_stat\":\"I\",\"sys_id\":\"6666000108840829\",\"sys_trace_audit_num\":\"\",\"term_div_coupon_type\":3,\"time_expire\":\"20240613110004\",\"trans_date\":\"20240613\",\"trans_finish_time\":1718247319000,\"trans_notify_url\":\"VIRGO://https://iotpush.cloudpnr.com/ws-mgnt-ser/callback/jspay\",\"trans_stat\":\"S\",\"trans_type\":\"1000\",\"un_scene_info\":\"\",\"unconfirm_amt\":0.02,\"unconfirm_fee_amt\":0.01,\"version\":3},\"trans_stat\":\"S\",\"trans_time\":\"105504\",\"trans_type\":\"T_JSAPI\",\"wx_response\":{\"bank_type\":\"OTHERS\",\"coupon_fee\":\"0.00\",\"openid\":\"o8jhot5QnK-AmVoARPMJjMT-SsX0\",\"sub_appid\":\"wxdfe9a5d141f96685\",\"sub_openid\":\"oozDJ52_y7RMnxObV04Q4bpuNZEM\"}}";

        JSONObject jsonObject = JSONUtil.parseObj(json_text);

        System.out.println("jsonObject.toString() = " + jsonObject.toString());
    }

}
