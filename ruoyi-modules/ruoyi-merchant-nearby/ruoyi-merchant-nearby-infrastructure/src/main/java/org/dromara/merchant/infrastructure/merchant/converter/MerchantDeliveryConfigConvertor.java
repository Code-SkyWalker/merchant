package org.dromara.merchant.infrastructure.merchant.converter;

import cn.hutool.json.JSONObject;
import org.dromara.merchant.client.merchant.dto.data.command.MerchantDeliveryConfigCreateCmd;
import org.dromara.merchant.domain.merchant.model.delivery.*;
import org.dromara.merchant.infrastructure.merchant.mapper.dataobject.MerchantDeliveryConfigDO;
import org.mapstruct.*;

import java.util.List;

/**
 * @Description 商户配送配置转换器
 * @Author Code Skywalker
 * @Date 2025/12/4 14:49
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MerchantDeliveryConfigConvertor {

    /**
     * DO转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true) // 忽略自动映射，使用AfterMapping手动处理
    MerchantDeliveryConfig toMerchantDeliveryConfigEntity(MerchantDeliveryConfigDO merchantDeliveryConfigDO);

    /**
     * Entity转DO
     */
    @Mapping(target = "deliveryConfig", ignore = true) // 忽略自动映射，使用AfterMapping手动处理
    MerchantDeliveryConfigDO toMerchantDeliveryConfigDO(MerchantDeliveryConfig merchantDeliveryConfig);

    /**
     * DO列表转Entity列表
     */
    List<MerchantDeliveryConfig> toMerchantDeliveryConfigListEntity(List<MerchantDeliveryConfigDO> merchantDeliveryConfigDOs);

    /**
     * Entity列表转DO列表
     */
    List<MerchantDeliveryConfigDO> toMerchantDeliveryConfigDO(List<MerchantDeliveryConfig> merchantDeliveryConfigs);

    /**
     * cmd转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true)
    MerchantDeliveryConfig toMerchantDeliveryConfig(MerchantDeliveryConfigCreateCmd cmd);


    @AfterMapping
    default void afterMappingDOToEntity(MerchantDeliveryConfigDO source, @MappingTarget MerchantDeliveryConfig target) {
        // 根据配送方式确定具体的配置类型
        String deliveryMethodStr = source.getDeliveryMethod();
        DeliveryMethod method = DeliveryMethod.getByCode(deliveryMethodStr);

        if (method == null || source.getDeliveryConfig() == null || source.getDeliveryConfig().isEmpty()) {
            return;
        }

        JSONObject jsonObject = new JSONObject(source.getDeliveryConfig());
        DeliveryConfig deliveryConfig = null;

        switch (method) {
            case EXPRESS_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigExpress.class);
                break;
            case LOCAL_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigLocal.class);
                break;
            case PICKUP_DELIVERY:
                // 这里可以添加 Pickup 配置的处理逻辑
                break;
            case NONE_DELIVERY:
                // 无配送方式不需要配置
                break;
            default:
                break;
        }

        if (deliveryConfig != null) {
            target.setDeliveryConfig(deliveryConfig);
        }
    }

    @AfterMapping
    default void afterMappingEntityToDO(MerchantDeliveryConfig source, @MappingTarget MerchantDeliveryConfigDO target) {
        // 将 DeliveryConfig 对象转换为 JSON 字符串
        if (source.getDeliveryConfig() != null) {
            target.setDeliveryConfig(source.getDeliveryConfig().toJson());
        }
    }

    @AfterMapping
    default void afterMappingCmdToEntity(MerchantDeliveryConfigCreateCmd source, @MappingTarget MerchantDeliveryConfig target) {
        // 根据配送方式确定具体的配置类型
        String deliveryMethodStr = source.getDeliveryMethod();
        DeliveryMethod method = DeliveryMethod.getByCode(deliveryMethodStr);

        if (method == null || source.getDeliveryConfig() == null) {
            return;
        }

        DeliveryConfig deliveryConfig = null;

        switch (method) {
            case EXPRESS_DELIVERY:
                deliveryConfig = (DeliveryConfigExpress) source.getDeliveryConfig();
                break;
            case LOCAL_DELIVERY:
                deliveryConfig = (DeliveryConfigLocal) source.getDeliveryConfig();
                break;
            case PICKUP_DELIVERY:
                // 这里可以添加 Pickup 配置的处理逻辑
                break;
            case NONE_DELIVERY:
                // 无配送方式不需要配置
                break;
            default:
                break;
        }

        if (deliveryConfig != null) {
            target.setDeliveryConfig(deliveryConfig);
        }
    }

}
