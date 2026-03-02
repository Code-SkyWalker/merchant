package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.Merchant;

import java.util.Collection;
import java.util.List;

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

    /**
     * 根据用户ID查询商户
     *
     * @param userId 用户ID
     * @return 商户实体
     */
    Merchant queryByUserId(Long userId);

    /**
     * 查询所有有效商户
     *
     * @return 商户实体列表
     */
    List<Merchant> queryAllValidMerchants();

    /**
     * 根据商户ID列表查询商户
     *
     * @param merchantIds 商户ID列表
     * @return 商户实体列表
     */
    List<Merchant> queryMerchantsByMerchantIds(Collection<Long> merchantIds);

}
