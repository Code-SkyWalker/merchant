package org.dromara.huifu.adapter.huifu;

import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.V2TradeWxusermarkQueryRequest;
import com.huifu.bspay.sdk.opps.core.utils.DateTools;
import com.huifu.bspay.sdk.opps.core.utils.SequenceTools;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.huifu.app.config.service.HuifuConfigService;
import org.dromara.huifu.client.api.pay.WeChatPay;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 汇付微信支付获取用户openId
 * @Author Code Skywalker
 * @Date 2025/11/17 17:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment/huifu/wechat")
public class WechatPayController {

    private final WeChatPay weChatPay;

    private final HuifuConfigService configService;

    /**
     * 获取用户openId
     * @param merchantId    商户ID
     * @param authCode      授权码
     */
    @GetMapping("/openId")
    public R<Map<String, Object>> openId(@RequestParam Long merchantId, @RequestParam String authCode) throws BasePayException, IllegalAccessException {

        HuifuConfig config = configService.queryByMerchantId(merchantId);
        if (config == null) return R.fail("商户支付未配置");
        V2TradeWxusermarkQueryRequest request = new V2TradeWxusermarkQueryRequest();
        request.setHuifuId(config.getHuifuId().toString());
        request.setReqDate(DateTools.getCurrentDateYYYYMMDD());
        request.setReqSeqId(SequenceTools.getReqSeqId32());
        request.setAuthCode(authCode);
        return R.ok(weChatPay.wxUserMark(request));
    }
}
