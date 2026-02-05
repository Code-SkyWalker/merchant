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
import org.dromara.huifu.domain.model.HuifuFeeRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.dromara.huifu.domain.model.HuifuConfig.*;

/**
 * @Description 汇付支付服务类，处理支付订单创建、签名验证等功能
 * @Author Code Skywalker
 * @Date 2025-11-11 17:12
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HuiFuPaymentService {

    private static final String DELAY_ACCT_FLAG_YES = "Y";  // 延迟分账
    private static final String DELAY_ACCT_FLAG_NO = "N";  // 不延迟分账

    // 支付回调访问路径
    public static final String NOTIFY_URL = "/merchant_nearby/payment/huifu/pay/notify";

    @Value("${default.domain}")
    private String domain;


    private final AggregatePayment payment;

    private final IHuifuFeeRateService feeRateService;

    private final IHuifuConfigService configService;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 创建支付订单
     *
     * @param request 支付请求参数
     * @return 支付订单参数
     */
    public Map<String, Object> createOrder(HuiFuPaymentRequest request) {
        Objects.requireNonNull(request, "支付请求参数不能为空");
        try {
            log.info("开始创建支付订单，商户id：{}，订单金额：{}", request.getMerchantId(), request.getTransAmt());
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
     * 创建支付请求对象
     *
     * @param req 支付请求对象
     * @return 支付请求对象
     */
    private V3TradePaymentJspayRequest createRequest(HuiFuPaymentRequest req) {
        Objects.requireNonNull(req, "支付请求对象不能为空");

        // 创建支付请求对象
        V3TradePaymentJspayRequest v3TradePaymentJspayRequest = getV3TradePaymentJspayRequest(req);

        // 设置通知地址
        setNotifyUrl(v3TradePaymentJspayRequest);

        // 设置支付方式
        setPaymentChannelData(v3TradePaymentJspayRequest, req);

        // 设置分账信息
        setAccountSplitInfo(v3TradePaymentJspayRequest, req);

        return v3TradePaymentJspayRequest;
    }

    /**
     * 创建支付请求对象
     *
     * @param req 支付请求对象
     * @return 支付请求对象
     */
    private static V3TradePaymentJspayRequest getV3TradePaymentJspayRequest(HuiFuPaymentRequest req) {
        V3TradePaymentJspayRequest v3TradePaymentJspayRequest = new V3TradePaymentJspayRequest();
        v3TradePaymentJspayRequest.setReqDate(req.getReqDate());
        v3TradePaymentJspayRequest.setReqSeqId(req.getReqSeqId());
        v3TradePaymentJspayRequest.setTransAmt(req.getTransAmt());
        v3TradePaymentJspayRequest.setGoodsDesc(req.getGoodsDesc());
        v3TradePaymentJspayRequest.setTradeType(req.getPaymentMethod());
        v3TradePaymentJspayRequest.setExtendInfo(req.getExtendInfos());
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
     * 根据支付类型设置支付方式
     *
     * @param request 支付请求对象
     * @param req     原始支付请求
     */
    private void setPaymentChannelData(V3TradePaymentJspayRequest request, HuiFuPaymentRequest req) {
        String tradeType = req.getPaymentMethod();
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
        HuifuConfig huifuConfig = this.configService.queryByMerchantId(req.getMerchantId());
        if (huifuConfig == null) throw new ServiceException("商户支付未配置");

        // 设置汇付商户号
        request.setHuifuId(huifuConfig.getHuifuId().toString());

        // 添加分账相关信息到请求
        request.addExtendInfo("acct_split_bunch", generateAccountSplitBunch(huifuConfig).toString());
        request.addExtendInfo("party_order_id", req.getInner_order_id());
        request.addExtendInfo("delay_acct_flag", DELAY_ACCT_FLAG_YES); // Y 为延迟 N为不延迟，这里传Y表示延迟分账
    }

    private AccountSplitBunch generateAccountSplitBunch(HuifuConfig huifuConfig) {

        // 查询分账比例 比例之和必须等于100%，添加时经过校验
        List<HuifuFeeRate> rates = this.feeRateService.queryByMerchantId(huifuConfig.getMerchantId());
        if (rates.isEmpty()) throw new ServiceException("商户分账比例未配置");

        // 构建分账比例映射
        Map<Integer, BigDecimal> typeRateMap = rates.stream()
            .collect(Collectors.toMap(HuifuFeeRate::getType, HuifuFeeRate::getFeeRate));

        List<AccountInfo> accountInfos = new ArrayList<>();

        // 构建输出账户信息
        if (typeRateMap.containsKey(MERCHANT_TYPE)) {
            AccountInfo outputAccountInfo = new AccountInfo();
            outputAccountInfo.setHuifu_id(huifuConfig.getHuifuId().toString());  // 分账方（交易收款方）
            outputAccountInfo.setPercentage_div(typeRateMap.get(MERCHANT_TYPE).toString());
            accountInfos.add(outputAccountInfo);
        }

        // 构建输入账户信息（子平台）
        if (typeRateMap.containsKey(PLATFORM_TYPE)) {
            AccountInfo inputAccountInfoTenant = new AccountInfo();
            HuifuConfig huifuConfigTenant = this.configService.queryByTenantId(huifuConfig.getTenantId());
            // 如果子平台配置不为空
            if (huifuConfigTenant != null) {
                inputAccountInfoTenant.setHuifu_id(String.valueOf(huifuConfigTenant.getHuifuId()));  // 分账接收方（子平台）
                inputAccountInfoTenant.setPercentage_div(typeRateMap.get(PLATFORM_TYPE).toString());
                accountInfos.add(inputAccountInfoTenant);
            }

            // 否则子平台配置为空，则将子平台比例加到渠道商比例中
            typeRateMap.put(CHANNEL_TYPE, typeRateMap.get(PLATFORM_TYPE).add(typeRateMap.get(CHANNEL_TYPE)));
        }

        // 构建输入账户信息（渠道商）
        if (typeRateMap.containsKey(PLATFORM_TYPE)) {
            AccountInfo inputAccountInfoChannel = new AccountInfo();
            inputAccountInfoChannel.setHuifu_id(BasePay.getConfig("default").getSysId());  // 分账接收方（渠道商）
            inputAccountInfoChannel.setPercentage_div(typeRateMap.get(CHANNEL_TYPE).toString());
            accountInfos.add(inputAccountInfoChannel);
        }

        // 构建分账信息
        AccountSplitBunch accountSplitBunch = new AccountSplitBunch();
        accountSplitBunch.setAcct_infos(accountInfos);

        return accountSplitBunch;
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

}
