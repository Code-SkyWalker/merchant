package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.MerchantCertify;


/**
 * @Description 商户审批网关接口
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
public interface IMerchantCertifyGateway {

    /**
     * 保存商户审批信息
     *
     * @param merchantCertify 商户审批实体
     * @return 是否保存成功
     */
    boolean save(MerchantCertify merchantCertify);

    /**
     * 根据ID查询商户审批信息
     *
     * @param approvalId 商户ID
     * @return 商户审批实体
     */
    MerchantCertify findById(Long approvalId);

    /**
     * 删除商户审批信息
     *
     * @param approvalId 商户ID
     * @return 是否删除成功
     */
    boolean deleteByPrimaryKey(Long approvalId);

}
