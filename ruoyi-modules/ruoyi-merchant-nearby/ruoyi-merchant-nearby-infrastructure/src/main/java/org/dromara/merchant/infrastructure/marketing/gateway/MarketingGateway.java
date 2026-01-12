package org.dromara.merchant.infrastructure.marketing.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.marketing.gateway.IMarketingGateway;
import org.dromara.merchant.domain.marketing.model.activity.Marketing;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.MarketingMapper;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingDO;
import org.springframework.stereotype.Component;

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

    @Override
    public List<Marketing> queryAvailableMarketing(Long spuId) {
        List<MarketingDO> marketingDOList = this.mapper.selectAvailableMarketingsBySpuId(spuId);
        return convertor.toMarketingList(marketingDOList);
    }

}
