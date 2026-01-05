package org.dromara.merchant.infrastructure.marketing.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.commodity.model.Sku;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.Marketing;
import org.dromara.merchant.domain.marketing.service.PriceCalculationStrategy;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.MarketingMapper;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 营销活动规则网关实现
 * @Author Code Skywalker
 * @Date 2025/12/26 11:12
 */
@Component
@RequiredArgsConstructor
public class MarketingGateway implements IMarketingGateway {

    private final MarketingMapper mapper;
    private final MarketingConvertor convertor;

    private final PriceCalculationStrategy bestDiscountStrategy;

    /**
     * 保存营销活动规则
     *
     * @param marketing 营销活动规则
     * @return 是否保存成功
     */
    @Override
    public Long save(Marketing marketing) {
        this.mapper.insertOrUpdate(convertor.toDo(marketing));
        return marketing.getId();
    }

    /**
     * 删除营销活动规则
     *
     * @param id 营销活动规则ID
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    /**
     * 根据ID查询营销活动规则
     *
     * @param id 营销活动规则ID
     * @return 营销活动规则
     */
    @Override
    public Marketing queryById(Long id) {
        MarketingDO marketingDO = this.mapper.selectById(id);
        return convertor.toEntity(marketingDO);
    }

    /**
     * 计算最终价格, 使用最佳优惠(可替换其他计算方式)
     *
     * @param sku           商品SKU
     * @param marketingList 营销活动列表
     * @param quantity      商品数量
     * @return 最终价格
     */
    @Override
    public BigDecimal calculateFinalPrice(Sku sku, List<Marketing> marketingList, Integer quantity) {
        return bestDiscountStrategy.calculateFinalPrice(sku, marketingList, quantity);
    }
}
