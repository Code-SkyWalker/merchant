package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.Merchant;

/**
 * @Description 商家网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:37
 */
public interface IMerchantGateway {

    /**
     * 保存商户
     *
     * @param merchant 商户实体
     * @return 是否保存成功
     */
    boolean save(Merchant merchant);

    /**
     * 根据ID查询商户
     *
     * @param merchantId 商户ID
     * @return 商户实体
     */
    Merchant findById(Long merchantId);

    /**
     * 删除商户
     *
     * @param merchantId 商户ID
     * @return 是否删除成功
     */
    boolean deleteById(Long merchantId);

}