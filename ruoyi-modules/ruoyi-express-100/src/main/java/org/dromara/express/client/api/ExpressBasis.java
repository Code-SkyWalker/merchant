package org.dromara.express.client.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.PrintReq;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.BOrderQueryData;
import com.kuaidi100.sdk.response.PrintBaseResp;
import com.kuaidi100.sdk.utils.SignUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.express.domain.model.Config;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/22 10:41
 */
public abstract class ExpressBasis {

    /**
     * 打印请求参数处理
     *
     * @param params 打印请求参数
     * @param config 配置参数
     * @param method 请求方法
     * @return 打印请求参数
     */
    protected PrintReq printReqProcessing(String params, Config config, String method) {
        String t = String.valueOf(System.currentTimeMillis());

        PrintReq request = new PrintReq();
        request.setKey(config.getKey());
        request.setT(t);
        request.setSign(SignUtils.printSign(params, t, config.getKey(), config.getSecret()));
        request.setParam(params);
        request.setMethod(method);

        return request;
    }

    /**
     * 跟踪请求参数处理
     *
     * @param params 跟踪请求参数
     * @param config 配置参数
     * @return 跟踪请求参数
     */
    protected QueryTrackReq trackReqProcessing(String params, Config config) {

        QueryTrackReq request = new QueryTrackReq();
        request.setParam(params);
        request.setCustomer(config.getCustomer());
        request.setSign(SignUtils.querySign(params, config.getKey(), config.getCustomer()));

        return request;
    }

    /**
     * http结果数据处理
     *
     * @param result http结果
     * @param token  数据类型
     * @return 数据
     */
    protected <T> T httpResultDataProcessing(HttpResult result, TypeToken<T> token) {

        if (result.getStatus() == HttpStatus.SC_OK && StringUtils.isNotBlank(result.getBody())) {
            return new Gson().fromJson(result.getBody(), token.getType());
        }

        return null;
    }

}
