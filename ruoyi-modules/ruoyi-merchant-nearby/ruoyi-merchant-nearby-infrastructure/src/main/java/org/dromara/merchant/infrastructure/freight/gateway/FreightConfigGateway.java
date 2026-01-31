package org.dromara.merchant.infrastructure.freight.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.freight.gateway.IFreightConfigGateway;
import org.dromara.merchant.domain.freight.model.FreightConfig;
import org.dromara.merchant.infrastructure.freight.converter.FreightConfigConvertor;
import org.dromara.merchant.infrastructure.freight.mapper.FreightConfigMapper;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.FreightConfigDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送配置网关实现
 * @Author Code Skywalker
 * @Date 2025/12/4 14:48
 */
@Component
@RequiredArgsConstructor
public class FreightConfigGateway implements IFreightConfigGateway {

    private final FreightConfigMapper mapper;
    private final FreightConfigConvertor convertor;

    @Override
    public boolean save(FreightConfig config) {
        FreightConfigDO configDO = convertor.toMerchantDeliveryConfigDO(config);
        return mapper.insertOrUpdate(configDO);
    }

    @Override
    public FreightConfig findById(Long deliveryId) {
        FreightConfigDO configDO = mapper.selectById(deliveryId);
        return convertor.toMerchantDeliveryConfigEntity(configDO);
    }

    @Override
    public List<FreightConfig> findByMerchantId(Long merchantId) {
        List<FreightConfigDO> configs = mapper.selectListByMerchantId(merchantId);
        return convertor.toMerchantDeliveryConfigListEntity(configs);
    }

    @Override
    public boolean deleteById(Long deliveryId) {
        return mapper.deleteById(deliveryId) > 0;
    }

    @Override
    public boolean deleteByMerchantId(Long merchantId) {
        return mapper.deleteByMerchantId(merchantId) > 0;
    }
}
