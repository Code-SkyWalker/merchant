package org.dromara.huifu.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import org.dromara.huifu.domain.gateway.IHuifuConfigGateway;
import org.dromara.huifu.domain.model.HuifuConfig;
import org.dromara.huifu.infrastructure.convertor.HuifuConfigConvertor;
import org.dromara.huifu.infrastructure.mapper.HuifuConfigMapper;
import org.dromara.huifu.infrastructure.mapper.dataobject.HuifuConfigDO;
import org.springframework.stereotype.Component;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:04
 */
@Component
@RequiredArgsConstructor
public class HuifuConfigGateway implements IHuifuConfigGateway {

    private final HuifuConfigMapper mapper;

    private final HuifuConfigConvertor convertor;

    @Override
    public boolean save(HuifuConfig config) {
        return this.mapper.insertOrUpdate(convertor.toDO(config));
    }

    @Override
    public boolean delete(Long huifuId) {
        return this.mapper.deleteById(huifuId) > 0;
    }

    @Override
    public HuifuConfig queryByTenantId(Long tenantId) {
        HuifuConfigDO huifuConfigDO = this.mapper.queryByTenantId(tenantId);
        return this.convertor.toEntity(huifuConfigDO);
    }

    @Override
    public HuifuConfig queryChannelConfig() {
        HuifuConfigDO huifuConfigDO = this.mapper.queryChannelConfig();
        return this.convertor.toEntity(huifuConfigDO);
    }

    @Override
    public HuifuConfig queryByMerchantId(Long merchantId) {
        HuifuConfigDO huifuConfigDO = this.mapper.queryByMerchantId(merchantId);
        return this.convertor.toEntity(huifuConfigDO);
    }

}
