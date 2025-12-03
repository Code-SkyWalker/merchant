package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingAreaCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingAreaConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingAreaMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingAreaDO;
import org.springframework.stereotype.Component;

/**
 * @Description 商户配送区域详情查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaDetailQryExecutor {

    private final MerchantShippingAreaMapper mapper;
    private final MerchantShippingAreaConvertor convertor;

    public MerchantShippingAreaCO execute(Long areaId) {
        MerchantShippingAreaDO area = mapper.selectByPrimaryKey(areaId);
        return this.convertor.toMerchantShippingAreaCO(area);
    }

}