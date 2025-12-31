package org.dromara.merchant.infrastructure.marketing.converter;

import org.dromara.merchant.client.marketing.dto.data.command.MarketingSpu;
import org.dromara.merchant.infrastructure.marketing.mapper.dataobject.MarketingSpuDO;
import org.mapstruct.*;

import java.util.List;

/**
 * @Description MarketingSpu转换器
 * @Author Code Skywalker
 * @Date 2025/12/26 11:14
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarketingSpuConvertor {

    /**
     * MarketingSpuDOs 转 marketingSpus
     * @param marketingDOs marketingDO列表
     * @return marketingSpus列表
     */
    List<org.dromara.merchant.domain.marketing.model.MarketingSpu> toEntity(List<MarketingSpuDO> marketingDOs);

    /**
     * MarketingSpus 转 MarketingSpuDOs
     * @param marketingSpus marketingSpus列表
     * @return marketingDO列表
     */
    List<MarketingSpuDO> toDO(List<org.dromara.merchant.domain.marketing.model.MarketingSpu> marketingSpus);

    /**
     * MarketingSpuCmds 转 MarketingSpus
     * @param marketingSpus marketingSpuCmds列表
     * @return marketingSpus列表
     */
    List<org.dromara.merchant.domain.marketing.model.MarketingSpu> cmdToEntity(List<MarketingSpu> marketingSpus);
}
