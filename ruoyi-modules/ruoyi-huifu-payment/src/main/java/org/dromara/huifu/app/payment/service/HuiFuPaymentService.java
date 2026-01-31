package org.dromara.huifu.app.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huifu.bspay.sdk.opps.core.BasePay;
import com.huifu.bspay.sdk.opps.core.config.MerConfig;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V3TradePaymentJspayRequest;
import com.huifu.bspay.sdk.opps.core.utils.RsaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.huifu.app.config.service.IHuifuConfigService;
import org.dromara.huifu.app.feerate.service.IHuifuFeeRateService;
import org.dromara.huifu.app.payment.executor.*;
import org.dromara.huifu.client.api.pay.AggregatePayment;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.dromara.huifu.client.api.pay.AggregatePayment.NOTIFY_URL;

/**
 * @Description 汇付支付服务类，处理支付订单创建、签名验证等功能
 * @Author Code Skywalker
 * @Date 2025-11-11 17:12
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HuiFuPaymentService {

    private static final String DELAY_ACCT_FLAG_YES = "Y";
    private static final String DELAY_ACCT_FLAG_NO = "N";

    private String domain = "https://api.bspay.net";

    private final AggregatePayment payment;

    private final IHuifuFeeRateService feeRateService;

    private final IHuifuConfigService configService;

    private final ObjectMapper mapper = new ObjectMapper();


    public Map<String, Object> createOrder(HuiFuPaymentRequest request) {
        Objects.requireNonNull(request, "支付请求参数不能为空");
        try {
            log.info("开始创建支付订单，商户号：{}，订单金额：{}", request.getHuifuId(), request.getTransAmt());
            Map<String, Object> result = payment.createPaymentOrder(createRequest(request));
            log.info("支付订单创建成功，订单号：{}", request.getInner_order_id());
            return result;
        } catch (BasePayException e) {
            log.error("创建支付订单失败，原因：{}", e.getMessage(), e);
            throw new ServiceException("支付订单创建失败：" + e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("访问权限异常，原因：{}", e.getMessage(), e);
            throw new ServiceException("系统内部错误：" + e.getMessage());
        }
    }

    /**
     * 验证签名
     *
     * @param sign      签名
     * @param resp_data 签名数据
     * @return 验证结果
     */
    public boolean validateSignature(String sign, String resp_data) {
        if (!StringUtils.hasText(sign) || !StringUtils.hasText(resp_data)) {
            log.warn("签名验证失败：签名或响应数据为空");
            return false;
        }
        try {
            MerConfig config = BasePay.getConfig("default");
            if (config == null) {
                log.error("获取汇付配置失败");
                return false;
            }
            boolean isValid = RsaUtils.verify(resp_data, config.getRsaPublicKey(), sign);
            log.debug("签名验证结果：{}", isValid);
            return isValid;
        } catch (Exception e) {
            log.error("签名验证过程发生异常：{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 验证签名
     *
     * @param resp_data 签名数据
     * @return 验证结果
     * @throws JsonProcessingException 签名数据转换异常
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> verifySignature(String resp_data) throws JsonProcessingException {
        return mapper.readValue(resp_data, Map.class);
    }

    private V3TradePaymentJspayRequest createRequest(HuiFuPaymentRequest req) {
        Objects.requireNonNull(req, "支付请求对象不能为空");

        V3TradePaymentJspayRequest v3TradePaymentJspayRequest = getV3TradePaymentJspayRequest(req);

        // 设置通知地址
        setNotifyUrl(v3TradePaymentJspayRequest);

        // 设置支付渠道特定数据
        setPaymentChannelData(v3TradePaymentJspayRequest, req);

        // 设置分账信息
        setAccountSplitInfo(v3TradePaymentJspayRequest, req);

        return v3TradePaymentJspayRequest;
    }

    /**
     * 设置支付通知地址
     *
     * @param request 支付请求对象
     */
    private void setNotifyUrl(V3TradePaymentJspayRequest request) {
        request.addExtendInfo("notify_url", domain + NOTIFY_URL);
    }

    /**
     * 根据支付类型设置支付渠道特定数据
     *
     * @param request 支付请求对象
     * @param req     原始支付请求
     */
    private void setPaymentChannelData(V3TradePaymentJspayRequest request, HuiFuPaymentRequest req) {
        String tradeType = req.getTradeType();
        if (tradeType.startsWith("A_")) {
            // 支付宝相关数据
            AlipayData alipayData = new AlipayData().setGoods_detail(req.getCommodityDetail());
            request.addExtendInfo("alipay_data", alipayData.toString());
        } else if (tradeType.startsWith("T_")) {
            // 微信相关数据
            WechatData wechatData = new WechatData()
                    .setGoods_detail(req.getCommodityDetail())
                    .setSub_appid(req.getSub_appid())
                    .setSub_openid(req.getSub_openid());
            request.addExtendInfo("wx_data", wechatData.toString());
        }
    }

    /**
     * 设置分账信息
     *
     * @param request 支付请求对象
     * @param req     原始支付请求
     */
    private void setAccountSplitInfo(V3TradePaymentJspayRequest request, HuiFuPaymentRequest req) {
        // 查询分账方信息
        HuifuConfig huifuConfig = this.configService.queryByMerchantId(Long.valueOf(req.getShopId()));
        if (huifuConfig == null) {
            throw new ServiceException("商户支付未配置");
        }

        // 查询分账比例（接收方比例）
        BigDecimal outPercent = this.feeRateService.queryByMerchantId(String.valueOf(req.getTenantId()));

        // 构建分账信息
        AccountSplitBunch accountSplitBunch = new AccountSplitBunch();
        List<AccountInfo> accountInfos = generateAccountInfos(huifuConfig, outPercent);
        accountSplitBunch.setAcct_infos(accountInfos);

        // 添加分账相关信息到请求
        request.addExtendInfo("acct_split_bunch", accountSplitBunch.toString());
        request.addExtendInfo("party_order_id", req.getInner_order_id());
        request.addExtendInfo("delay_acct_flag", DELAY_ACCT_FLAG_YES); // Y 为延迟 N为不延迟，这里传Y表示延迟分账
    }

    private static List<AccountInfo> generateAccountInfos(HuifuConfig huifuConfig, BigDecimal outPercent) {
        Objects.requireNonNull(huifuConfig, "汇付配置信息不能为空");
        Objects.requireNonNull(outPercent, "分账比例不能为空");

        // 计算输出方分账比例
        BigDecimal outputPercent = BigDecimal.valueOf(100L).subtract(outPercent);

        AccountInfo outputAccountInfo = new AccountInfo();
        outputAccountInfo.setHuifu_id(huifuConfig.getHuifuId().toString());  // 分账方（交易收款方）
        outputAccountInfo.setPercentage_div(outputPercent.toString());

        AccountInfo inputAccountInfo = new AccountInfo();
        inputAccountInfo.setHuifu_id(BasePay.getConfig("default").getSysId());  // 分账接收方（渠道商）
        inputAccountInfo.setPercentage_div(outPercent.toString());

        List<AccountInfo> accountInfos = new ArrayList<>();
        accountInfos.add(outputAccountInfo);
        accountInfos.add(inputAccountInfo);

        return accountInfos;
    }

    private static V3TradePaymentJspayRequest getV3TradePaymentJspayRequest(HuiFuPaymentRequest req) {
        V3TradePaymentJspayRequest v3TradePaymentJspayRequest = new V3TradePaymentJspayRequest();
        v3TradePaymentJspayRequest.setHuifuId(req.getHuifuId());
        v3TradePaymentJspayRequest.setReqDate(req.getReqDate());
        v3TradePaymentJspayRequest.setReqSeqId(req.getReqSeqId());
        v3TradePaymentJspayRequest.setTransAmt(req.getTransAmt());
        v3TradePaymentJspayRequest.setGoodsDesc(req.getGoodsDesc());
        v3TradePaymentJspayRequest.setTradeType(req.getTradeType());
        v3TradePaymentJspayRequest.setExtendInfo(req.getExtendInfos());
        return v3TradePaymentJspayRequest;
    }

}
