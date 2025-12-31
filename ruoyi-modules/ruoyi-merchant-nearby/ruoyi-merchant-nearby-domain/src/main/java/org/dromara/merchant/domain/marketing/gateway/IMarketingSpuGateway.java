package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.marketing.model.MarketingSpu;

import java.util.List;

/**
 * @Description 营销活动与商品关联网关
 * @Author Code Skywalker
 * @Date 2025/12/29 16:24
 */
public interface IMarketingSpuGateway {

    /**
     * 保存
     *
     * @param marketingSpus 营销和商品关联实体列表
     * @return 保存结果
     */
    boolean save(List<MarketingSpu> marketingSpus);

    /**
     * 根据营销ID删除
     *
     * @param marketingId 营销ID
     * @return 删除结果
     */
    boolean deleteByMarketingId(Long marketingId);
}
