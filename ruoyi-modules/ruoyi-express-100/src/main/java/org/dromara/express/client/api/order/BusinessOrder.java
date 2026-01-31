package org.dromara.express.client.api.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuaidi100.sdk.api.BOrderOfficial;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.BOrderCancelReq;
import com.kuaidi100.sdk.request.BOrderOfficialQueryPriceReq;
import com.kuaidi100.sdk.request.BOrderReq;
import com.kuaidi100.sdk.request.PrintReq;
import com.kuaidi100.sdk.response.PrintBaseResp;
import org.dromara.express.client.api.ExpressBasis;
import org.dromara.express.domain.model.Config;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;


/**
 * @Description 商家寄件
 * @Author Code Skywalker
 * @Date 2026/1/22 09:15
 */
@Component
public class BusinessOrder extends ExpressBasis {


    /**
     * 商家寄件查询价格
     *
     * @param params 查询参数
     * @param config 配置参数
     */
    public BigDecimal queryPrice(BOrderOfficialQueryPriceReq params, Config config) throws Exception {
        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.B_ORDER_OFFICIAL_PRICE_METHOD);
        HttpResult result = new BOrderOfficial().execute(printReq);
        Map<String, Object> data = httpResultDataProcessing(result, new TypeToken<PrintBaseResp<Map<String, Object>>>() {}).getData();
        return new BigDecimal(data.get("price").toString());
    }

    /**
     * 商家寄件创建订单
     *
     * @param params 创建订单参数
     * @param config 配置参数
     */
    public Map<String, Object> create(BOrderReq params, Config config) throws Exception {
        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.B_ORDER_OFFICIAL_ORDER_METHOD);
        HttpResult result = new BOrderOfficial().execute(printReq);

        return  httpResultDataProcessing(result, new TypeToken<PrintBaseResp<Map<String, Object>>>() {}).getData();
    }

    /**
     * 商家寄件取消订单
     *
     * @param params 取消订单参数
     * @param config 配置参数
     */
    public Map<String, Object> cancel(BOrderCancelReq params, Config config) throws Exception {
        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.B_ORDER_OFFICIAL_CANCEL_METHOD);
        HttpResult result = new BOrderOfficial().execute(printReq);
        return httpResultDataProcessing(result, new TypeToken<PrintBaseResp<Map<String, Object>>>() {}).getData();
    }


}
