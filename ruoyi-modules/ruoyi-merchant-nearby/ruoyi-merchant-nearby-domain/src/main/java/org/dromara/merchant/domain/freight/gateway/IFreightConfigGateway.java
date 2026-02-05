package org.dromara.merchant.domain.freight.gateway;

import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.domain.freight.model.FreightConfig;

import java.util.List;

/**
 * @Description 商户配送配置网关接口
 * @Author Code Skywalker
 * @Date 2025/12/4 14:46
 */
public interface IFreightConfigGateway {

    /**
     * 保存商户配送配置
     *
     * @param config 配送配置实体
     * @return 是否保存成功
     */
    boolean save(FreightConfig config);

    /**
     * 根据ID查询商户配送配置
     *
     * @param deliveryId 配送配置ID
     * @return 配送配置实体
     */
    FreightConfig findById(Long deliveryId);

    /**
     * 根据商户ID查询所有配送配置
     *
     * @param merchantId 商户ID
     * @return 配送配置列表
     */
    List<FreightConfig> findByMerchantId(Long merchantId);

    /**
     * 删除商户配送配置
     *
     * @param deliveryId 配送配置ID
     * @return 是否删除成功
     */
    boolean deleteById(Long deliveryId);

    /**
     * 根据商户ID删除所有配送配置
     *
     * @param merchantId 商户ID
     * @return 是否删除成功
     */
    boolean deleteByMerchantId(Long merchantId);

    /**
     * 根据商户ID和配送方式查询商户配送配置
     *
     * @param merchantId     商户ID
     * @param deliveryMethod 配送方式
     * @return 配送配置实体
     */
    FreightConfig queryByMerchantIdAndDeliveryMethod(Long merchantId, DeliveryMethod deliveryMethod);
}
