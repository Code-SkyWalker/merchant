package org.dromara.huifu.app.payment.service;


import cn.hutool.json.JSONObject;

/**
 * @Description 汇付回调处理接口
 * @Author Code Skywalker
 * @Date 2025/11/17 14:55
 */
public interface IHuifuCallbackHandler {

    /**
     * 支付回调处理
     * @param data 回调数据
     */
    void paymentCallBack(JSONObject data);

}
