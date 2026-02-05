package org.dromara.merchant.app.freight.service;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.app.freight.IFreightConfigService;
import org.dromara.merchant.app.freight.executor.FreightConfigCreateExecutor;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.gateway.IFreightConfigGateway;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.domain.freight.model.FreightConfig;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:43
 */
@Component
@RequiredArgsConstructor
public class FreightConfigService implements IFreightConfigService {

    private final FreightConfigCreateExecutor createExecutor;

    private final IFreightConfigGateway freightConfigGateway;

    @Override
    public Long create(FreightConfigCreateCmd cmd) {
        return createExecutor.execute(cmd);
    }

    /**
     * 根据ID查询运费配置
     *
     * @param id ID
     * @return 运费配置
     */
    @Override
    public FreightConfig queryById(Long id) {
        return this.freightConfigGateway.findById(id);
    }

    /**
     * 根据商家ID查询运费配置
     *
     * @param merchantId 商家ID
     * @return 运费配置列表
     */
    @Override
    public List<FreightConfig> queryByMerchantId(Long merchantId) {
        return this.freightConfigGateway.findByMerchantId(merchantId);
    }

    /**
     * 根据商家ID和配送方式查询运费配置
     *
     * @param merchantId     商家ID
     * @param deliveryMethod 配送方式
     * @return 运费配置
     */
    @Override
    public FreightConfig queryByMerchantIdAndDeliveryMethod(Long merchantId, DeliveryMethod deliveryMethod) {
        return this.freightConfigGateway.queryByMerchantIdAndDeliveryMethod(merchantId, deliveryMethod);
    }
}
