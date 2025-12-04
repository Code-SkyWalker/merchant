package org.dromara.merchant.domain.merchant.gateway;

import org.dromara.merchant.domain.merchant.model.delivery.MerchantDeliveryConfig;

import java.util.List;

/**
 * @Description 商户配送配置网关接口
 * @Author Code Skywalker
 * @Date 2025/12/4 14:46
 */
public interface IMerchantDeliveryConfigGateway {

    /**
     * 保存商户配送配置
     *
     * @param config 配送配置实体
     * @return 是否保存成功
     */
    boolean save(MerchantDeliveryConfig config);

    /**
     * 根据ID查询商户配送配置
     *
     * @param deliveryId 配送配置ID
     * @return 配送配置实体
     */
    MerchantDeliveryConfig findById(Long deliveryId);

    /**
     * 根据商户ID查询所有配送配置
     *
     * @param merchantId 商户ID
     * @return 配送配置列表
     */
    List<MerchantDeliveryConfig> findByMerchantId(Long merchantId);

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
}