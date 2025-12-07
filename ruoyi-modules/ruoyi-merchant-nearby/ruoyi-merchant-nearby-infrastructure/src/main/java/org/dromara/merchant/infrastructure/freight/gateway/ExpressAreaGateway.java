package org.dromara.merchant.infrastructure.freight.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.freight.gateway.IExpressAreaGateway;
import org.dromara.merchant.domain.freight.model.ExpressArea;
import org.dromara.merchant.infrastructure.freight.converter.ExpressAreaConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.ExpressAreaDO;
import org.dromara.merchant.infrastructure.freight.mapper.ExpressAreaMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送区域网关实现
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class ExpressAreaGateway implements IExpressAreaGateway {

    private final ExpressAreaMapper mapper;
    private final ExpressAreaConvertor convertor;

    @Override
    public boolean save(ExpressArea area) {
        ExpressAreaDO areaDO = convertor.toMerchantShippingAreaDO(area);
        return mapper.insertOrUpdate(areaDO);
    }

    @Override
    public boolean batchSave(List<ExpressArea> areas) {
        List<ExpressAreaDO> expressAreaDO = convertor.toMerchantShippingAreaDO(areas);
        return mapper.insertBatch(expressAreaDO);
    }

    @Override
    public ExpressArea findById(Long areaId) {
        ExpressAreaDO areaDO = mapper.selectById(areaId);
        return convertor.toMerchantShippingAreaEntity(areaDO);
    }

    @Override
    public boolean deleteById(Long areaId) {
        return mapper.deleteById(areaId) > 0;
    }

    @Override
    public boolean deleteByIds(List<Long> areaIds) {
        return mapper.deleteByIds(areaIds) > 0;
    }

    @Override
    public boolean deleteByTemplateId(Long templateId) {
        return mapper.deleteByTemplateId(templateId) > 0;
    }
}
