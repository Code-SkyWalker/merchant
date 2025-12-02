package org.dromara.merchant.domain.merchant.gateway;

import java.util.List;

/**
 * @Description 商家网关接口
 * @Author Code Skywalker
 * @Date 2025-10-23 11:37
 */
public interface IMerchantCategoryGateway {

    /**
     * 保存商户与分类关系
     *
     * @param merchantId 商户ID
     * @param categoryIds 商户分类ID
     * @return 是否保存成功
     */
    boolean save(Long merchantId, List<Long> categoryIds);

}
