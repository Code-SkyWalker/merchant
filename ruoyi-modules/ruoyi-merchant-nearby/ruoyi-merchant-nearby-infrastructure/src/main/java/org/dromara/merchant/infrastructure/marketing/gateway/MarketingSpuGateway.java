package org.dromara.merchant.infrastructure.marketing.gateway;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.marketing.gateway.IMarketingSpuGateway;
import org.dromara.merchant.domain.marketing.model.MarketingSpu;
import org.dromara.merchant.infrastructure.marketing.converter.MarketingSpuConvertor;
import org.dromara.merchant.infrastructure.marketing.mapper.MarketingSpuMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 营销活动与商品关联网关实现
 * @Author Code Skywalker
 * @Date 2025/12/29 16:28
 */
@Component
@RequiredArgsConstructor
public class MarketingSpuGateway implements IMarketingSpuGateway {

    private final MarketingSpuMapper mapper;
    private final MarketingSpuConvertor convertor;

    @Override
    public boolean save(List<MarketingSpu> marketingSpus) {
        if (CollUtil.isEmpty(marketingSpus)) return false;

        // 根据 deleted 分组
        Map<Boolean, List<MarketingSpu>> grouped = marketingSpus.stream()
            .collect(Collectors.groupingBy(MarketingSpu::getDeleted));

        boolean result = true;

        // 处理删除操作
        List<MarketingSpu> toDelete = grouped.get(true);
        if (CollUtil.isNotEmpty(toDelete)) {
            List<Long> idList = toDelete.stream().map(MarketingSpu::getId).toList();
            result = mapper.deleteByIds(idList) > 0;
        }

        // 处理新增操作
        List<MarketingSpu> toInsert = grouped.get(false);
        if (CollUtil.isNotEmpty(toInsert)) {
            boolean insertResult = mapper.insertOrUpdateBatch(this.convertor.toDO(toInsert));
            result = result && insertResult;
        }

        return result;

    }


    @Override
    public boolean deleteByMarketingId(Long marketingId) {
        return this.mapper.deleteByMarketingId(marketingId) > 0;
    }
}
