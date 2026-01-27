package org.dromara.express.client.api.query;

import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.api.QueryTrackMap;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.QueryTrackMapResp;
import com.kuaidi100.sdk.response.QueryTrackResp;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.dromara.express.client.api.ExpressBasis;
import org.dromara.express.domain.model.Config;
import org.springframework.stereotype.Component;

/**
 * @Description 快递查询
 * @Author Code Skywalker
 * @Date 2026/1/19 17:49
 */
@Component
public class PackageTrace extends ExpressBasis {

    /**
     * 快递查询地图轨迹
     *
     * @param prams  {@link QueryTrackParam}快递查询参数
     * @param config 快递查询配置
     * @return HttpResult
     */
    public QueryTrackResp queryTrack(QueryTrackParam prams, Config config) throws Exception {
        String param = new Gson().toJson(prams);

        QueryTrackReq req = super.trackReqProcessing(param, config);
        return new QueryTrack().queryTrack(req);
    }

    /**
     * 快递查询地图轨迹
     *
     * @param params {@link QueryTrackParam}快递查询参数
     * @param config 快递查询配置
     * @return HttpResult
     */
    public QueryTrackMapResp queryMapView(QueryTrackParam params, Config config) throws Exception {
        String param = new Gson().toJson(params);

        QueryTrackReq req = super.trackReqProcessing(param, config);
        HttpResult result = new QueryTrackMap().execute(req);
        if (result.getStatus() == HttpStatus.SC_OK && StringUtils.isNotBlank(result.getBody())) {
            return new Gson().fromJson(result.getBody(), QueryTrackMapResp.class);
        }

        return null;
    }

}
