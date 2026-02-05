package org.dromara.merchant.app.freight;

import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.domain.freight.model.FreightConfig;

import java.util.List;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/4 17:42
 */
public interface IFreightConfigService {

    /**
     * 创建运费配置
     *
     * @param cmd 创建命令
     * @return 配置ID
     */
    Long create(FreightConfigCreateCmd cmd);

    /**
     * 根据ID查询运费配置
     *
     * @param id ID
     * @return 运费配置
     */
    FreightConfig queryById(Long id);

    /**
     * 根据商家ID查询运费配置
     *
     * @param merchantId 商家ID
     * @return 运费配置列表
     */
    List<FreightConfig> queryByMerchantId(Long merchantId);

    /**
     * 根据商家ID和配送方式查询运费配置
     *
     * @param merchantId     商家ID
     * @param deliveryMethod 配送方式
     * @return 运费配置
     */
    FreightConfig queryByMerchantIdAndDeliveryMethod(Long merchantId, DeliveryMethod deliveryMethod);
}
