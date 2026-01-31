package org.dromara.huifu.client.api.merchant;

import com.huifu.bspay.sdk.opps.client.BasePayClient;
import com.huifu.bspay.sdk.opps.core.exception.BasePayException;
import com.huifu.bspay.sdk.opps.core.request.*;

import java.util.Map;

/**
 * @Description 用户进件
 * @Author Code Skywalker
 * @Date 2025-11-10 11:24
 */
public class PersonalImports {

    /**
     * 企业用户进件
     * @param request {@link V2UserBasicdataEntRequest}
     */
    public Map<String, Object> entUserImport(V2UserBasicdataEntRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 企业用户修改
     * @param request {@link V2UserBasicdataEntModifyRequest}
     */
    public Map<String, Object> entUserModify(V2UserBasicdataEntModifyRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 个人用户进件
     * @param request {@link V2UserBasicdataIndvRequest}
     */
    public Map<String, Object> indvUserImport(V2UserBasicdataIndvRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 个人用户进件修改
     * @param request {@link V2UserBasicdataIndvModifyRequest}
     */
    public Map<String, Object> indvUserModify(V2UserBasicdataIndvModifyRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 用户业务入驻
     * @param request {@link V2UserBusiOpenRequest}
     */
    public Map<String, Object> userBusinessOpen(V2UserBusiOpenRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 用户业务入驻修改
     * @param request {@link V2UserBusiModifyRequest}
     */
    public Map<String, Object> userBusinessModify(V2UserBusiModifyRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 用户列表查询
     * @param request {@link V2UserListQueryRequest}
     */
    public Map<String, Object> userListQuery(V2UserListQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 用户信息查询
     * @param request {@link V2UserBasicdataQueryRequest}
     */
    public Map<String, Object> userBasicDataQuery(V2UserBasicdataQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }

    /**
     * 用户申请单状态查询
     * @param request {@link V2UserApplyQueryRequest}
     */
    public Map<String, Object> userApplyQuery(V2UserApplyQueryRequest request) throws BasePayException, IllegalAccessException {
        return BasePayClient.request(request);
    }
}
