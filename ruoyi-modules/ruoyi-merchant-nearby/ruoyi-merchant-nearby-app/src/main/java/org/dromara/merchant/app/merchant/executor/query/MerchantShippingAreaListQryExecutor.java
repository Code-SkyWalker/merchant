package org.dromara.merchant.app.merchant.executor.query;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.merchant.dto.data.clientobject.MerchantShippingAreaCO;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingAreaConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingAreaMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingAreaDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送区域列表查询执行器
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaListQryExecutor {

    private final MerchantShippingAreaMapper mapper;
    private final MerchantShippingAreaConvertor convertor;

    public List<MerchantShippingAreaCO> execute(Long templateId) {
        List<MerchantShippingAreaDO> areas = mapper.selectByTemplateId(templateId);
        return this.convertor.toMerchantShippingAreaListCO(areas);
    }

}
