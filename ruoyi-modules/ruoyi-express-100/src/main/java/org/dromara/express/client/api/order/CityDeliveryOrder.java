package org.dromara.express.client.api.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuaidi100.sdk.api.BsameCityExpress;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.PrintReq;
import com.kuaidi100.sdk.request.bsamecity.BsamecityAddfeeReq;
import com.kuaidi100.sdk.request.bsamecity.BsamecityCancelReq;
import com.kuaidi100.sdk.request.bsamecity.BsamecityOrderReq;
import com.kuaidi100.sdk.response.PrintBaseResp;
import com.kuaidi100.sdk.response.bsamecity.BsamecityCancelResp;
import com.kuaidi100.sdk.response.bsamecity.BsamecityOrderResp;
import com.kuaidi100.sdk.response.bsamecity.BsamecityPriceResp;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.express.client.api.ExpressBasis;
import org.dromara.express.domain.model.Config;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description 同城配送订单
 * @Author Code Skywalker
 * @Date 2026/1/22 10:25
 */
@Component
public class CityDeliveryOrder extends ExpressBasis {

    /**
     * 预下单接口
     *
     * @param params {@link BsamecityOrderReq}订单价格查询参数
     * @param config 配置参数
     * @return 请求结果
     */
    public BigDecimal queryPrice(BsamecityOrderReq params, Config config) throws Exception {
        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.PRICE);
        HttpResult result = new BsameCityExpress().execute(printReq);
        BsamecityPriceResp priceResp = httpResultDataProcessing(result, new TypeToken<PrintBaseResp<BsamecityPriceResp>>() {
        }).getData();
        return new BigDecimal(priceResp.getDeliverFee());
    }

    /**
     * 创建订单接口
     *
     * @param params {@link BsamecityOrderReq}创建订单参数
     * @param config 配置参数
     * @return 请求结果
     */
    public BsamecityOrderResp create(BsamecityOrderReq params, Config config) throws Exception {

        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.BSAMECITY_ORDER);
        HttpResult result = new BsameCityExpress().execute(printReq);
        return httpResultDataProcessing(result, new TypeToken<PrintBaseResp<BsamecityOrderResp>>() {}).getData();
    }

    /**
     * 取消订单接口
     *
     * @param params {@link BsamecityCancelReq}取消订单参数
     * @param config 配置参数
     * @return 请求结果
     */
    public BsamecityCancelResp cancel(BsamecityCancelReq params, Config config) throws Exception {

        String param = new Gson().toJson(params);

        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.BSAMECITY_CANCEL);
        HttpResult result = new BsameCityExpress().execute(printReq);
        return httpResultDataProcessing(result, new TypeToken<PrintBaseResp<BsamecityCancelResp>>() {}).getData();
    }

    /**
     * 加小费接口
     *
     * @param params {@link BsamecityAddfeeReq}加小费参数
     * @param config 配置参数
     * @return 请求结果
     */
    public boolean addTip(BsamecityAddfeeReq params, Config config) throws Exception {
        String param = new Gson().toJson(params);
        PrintReq printReq = super.printReqProcessing(param, config, ApiInfoConstant.BSAMECITY_ADDFEE);
        HttpResult result = new BsameCityExpress().execute(printReq);

        if (result.getStatus() != 200) throw new ServiceException(result.getError());
        return true;
    }


}
