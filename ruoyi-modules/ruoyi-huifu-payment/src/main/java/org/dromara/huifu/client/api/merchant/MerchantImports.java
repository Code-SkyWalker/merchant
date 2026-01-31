package org.dromara.huifu.client.api.merchant;

import com.huifu.bspay.sdk.opps.client.BasePayClient;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 商户进件
 * @Author Code Skywalker
 * @Date 2025-11-10 10:38
 */
@Component
public class MerchantImports {

    /**
     * 商户统一进件（页面版）
     * @param request {@link V2MerchantUrlForwardRequest}
     * @return 返回参数
     */
    public Map<String, Object> webBasicDataImport(V2MerchantUrlForwardRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 统一进件页面版查询
     * @param request {@link V2MerchantBusiStatusQueryRequest}
     * @return 返回参数
     */
    public Map<String, Object> webBasicDataQuery(V2MerchantBusiStatusQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 图片上传
     * @param request {@link V2SupplementaryPictureRequest}
     * @return 返回参数
     */
    public Map<String, Object> uploadImg(V2SupplementaryPictureRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 企业商户进件
     * @param request {@link V2MerchantBasicdataEntRequest}
     * @return 响应参数
     */
    public Map<String, Object> enterpriseBasicDataImport(V2MerchantBasicdataEntRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 个人商户进件
     * @param request {@link V2MerchantBasicdataIndvRequest}
     * @return 响应参数
     */
    public Map<String, Object> individualBasicDataImport(V2MerchantBasicdataIndvRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 修改商户基础信息
     * @param request {@link V2MerchantBasicdataModifyRequest}
     * @return 响应参数
     */
    public Map<String, Object> modifyBasicData(V2MerchantBasicdataModifyRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 修改商户基础信息
     * @param request {@link V2MerchantBasicdataQueryRequest}
     * @return 响应参数
     */
    public Map<String, Object> queryBasicData(V2MerchantBasicdataQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 商户业务开通
     * @param request {@link V2MerchantBusiOpenRequest}
     * @return 响应参数
     */
    public Map<String, Object> businessOpen(V2MerchantBusiOpenRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 商户业务开通修改
     * @param request {@link V2MerchantBusiModifyRequest}
     * @return 响应参数
     */
    public Map<String, Object> businessModify(V2MerchantBusiModifyRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 申请单状态查询
     * @param request {@link V2MerchantBusiStatusQueryRequest}
     * @return 响应参数
     */
    public Map<String, Object> basicDataStatusQuery(V2MerchantBasicdataStatusQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 商户短信发送
     * @param request {@link V2MerchantBasicdataSmsSendRequest}
     * @return 响应参数
     */
    public Map<String, Object> businessStatusQuery(V2MerchantBasicdataSmsSendRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 商户状态变更
     * @param request {@link V2MerchantBusiModifyBusistatusRequest}
     * @return 响应参数
     */
    public Map<String, Object> modifyBusinessStatus(V2MerchantBusiModifyBusistatusRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 开通下级商户权限配置接口
     * @param request {@link V2MerchantBusiHeadConfigRequest}
     * @return 响应参数
     */
    public Map<String, Object> openSubMerchantPermission(V2MerchantBusiHeadConfigRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }
}
