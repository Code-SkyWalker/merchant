package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantShippingAreaGateway;
import org.dromara.merchant.domain.merchant.model.MerchantShippingArea;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantShippingAreaConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantShippingAreaMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantShippingAreaDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送区域网关实现
 * @Author Code Skywalker
 * @Date 2025/12/3 16:00
 */
@Component
@RequiredArgsConstructor
public class MerchantShippingAreaGateway implements IMerchantShippingAreaGateway {

    private final MerchantShippingAreaMapper mapper;
    private final MerchantShippingAreaConvertor convertor;

    @Override
    public boolean save(MerchantShippingArea area) {
        MerchantShippingAreaDO areaDO = convertor.toMerchantShippingAreaDO(area);
        return mapper.insertOrUpdateSelective(areaDO) > 0;
    }

    @Override
    public boolean batchSave(List<MerchantShippingArea> areas) {
        return mapper.batchInsertOrUpdate(convertor.toMerchantShippingAreaDO(areas)) > 0;
    }

    @Override
    public MerchantShippingArea findById(Long areaId) {
        MerchantShippingAreaDO areaDO = mapper.selectByPrimaryKey(areaId);
        return convertor.toMerchantShippingAreaEntity(areaDO);
    }

    @Override
    public boolean deleteById(Long areaId) {
        return mapper.deleteByPrimaryKey(areaId) > 0;
    }

    @Override
    public boolean deleteByIds(List<Long> areaIds) {
        return mapper.deleteByPrimaryKeys(areaIds) > 0;
    }

    @Override
    public boolean deleteByTemplateId(Long templateId) {
        // TODO: 需要实现根据模板ID删除所有配送区域的方法
        return true;
    }

    @Override
    public List<MerchantShippingArea> findByTemplateId(Long templateId) {
        // TODO: 需要实现根据模板ID查询所有配送区域的方法
        return null;
    }
}
