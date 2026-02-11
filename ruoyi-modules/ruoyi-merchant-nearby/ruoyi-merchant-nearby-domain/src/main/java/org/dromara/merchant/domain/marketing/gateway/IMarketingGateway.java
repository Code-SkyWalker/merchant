package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.marketing.model.activity.Marketing;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Description 营销活动规则网关
 * @Author Code Skywalker
 * @Date 2025/12/26 11:10
 */
public interface IMarketingGateway {

    /**
     * 保存
     *
     * @param marketing 营销活动规则
     * @return 是否保存成功
     */
    Long save(Marketing marketing);

    /**
     * 删除
     *
     * @param id 营销活动规则id
     * @return 是否删除成功
     */
    boolean delete(Long id);

    /**
     * 查询
     *
     * @param id 营销活动规则id
     * @return 营销活动规则
     */
    Marketing queryById(Long id);

    /**
     * 查询可用的营销活动
     *
     * @param spuId 商品id
     * @return 可用的营销活动
     */
    List<Marketing> queryAvailableMarketing(Long spuId);

    /**
     * 根据营销活动ID列表查询营销活动
     *
     * @param marketingIds 营销活动ID列表
     * @return 营销活动列表
     */
    List<Marketing> queryByIds(Collection<Long> marketingIds);
}
