package org.dromara.merchant.domain.marketing.gateway;

import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.model.Marketing;

import java.math.BigDecimal;
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
     * 计算商品最终价格
     *
     * @param sku           商品SKU
     * @param marketingList 营销活动列表
     * @param quantity      购买数量
     * @return 最终价格
     */
    BigDecimal calculateFinalPrice(Sku sku, List<Marketing> marketingList, Integer quantity);
}
