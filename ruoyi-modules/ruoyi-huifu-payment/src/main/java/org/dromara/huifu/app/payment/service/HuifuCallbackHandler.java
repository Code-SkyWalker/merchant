package org.dromara.huifu.app.payment.service;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/17 16:53
 */
@Component
@RequiredArgsConstructor
public class HuifuCallbackHandler {

    private final List<IHuifuCallbackHandler> huifuCallbackHandlers;

    public void paymentCallBack(JSONObject data) {
        huifuCallbackHandlers.forEach(
                huifuCallbackHandler -> huifuCallbackHandler.paymentCallBack(data)
        );
    }

}
