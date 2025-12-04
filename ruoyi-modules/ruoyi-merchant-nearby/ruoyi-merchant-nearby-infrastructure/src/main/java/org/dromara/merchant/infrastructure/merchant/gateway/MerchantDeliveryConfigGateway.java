package org.dromara.merchant.infrastructure.merchant.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.domain.merchant.gateway.IMerchantDeliveryConfigGateway;
import org.dromara.merchant.domain.merchant.model.delivery.MerchantDeliveryConfig;
import org.dromara.merchant.infrastructure.merchant.converter.MerchantDeliveryConfigConvertor;
import org.dromara.merchant.infrastructure.merchant.mapper.MerchantDeliveryConfigMapper;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDeliveryConfigDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 商户配送配置网关实现
 * @Author Code Skywalker
 * @Date 2025/12/4 14:48
 */
@Component
@RequiredArgsConstructor
public class MerchantDeliveryConfigGateway implements IMerchantDeliveryConfigGateway {

    private final MerchantDeliveryConfigMapper mapper;
    private final MerchantDeliveryConfigConvertor convertor;

    @Override
    public boolean save(MerchantDeliveryConfig config) {
        MerchantDeliveryConfigDO configDO = convertor.toMerchantDeliveryConfigDO(config);
        return mapper.insertOrUpdate(configDO);
    }

    @Override
    public MerchantDeliveryConfig findById(Long deliveryId) {
        MerchantDeliveryConfigDO configDO = mapper.selectById(deliveryId);
        return convertor.toMerchantDeliveryConfigEntity(configDO);
    }

    @Override
    public List<MerchantDeliveryConfig> findByMerchantId(Long merchantId) {
        List<MerchantDeliveryConfigDO> configs = mapper.selectListByMerchantId(merchantId);
        return convertor.toMerchantDeliveryConfigListEntity(configs);
    }

    @Override
    public boolean deleteById(Long deliveryId) {
        return mapper.deleteByPrimaryKey(deliveryId) > 0;
    }

    @Override
    public boolean deleteByMerchantId(Long merchantId) {
        return mapper.deleteByMerchantId(merchantId) > 0;
    }
}
