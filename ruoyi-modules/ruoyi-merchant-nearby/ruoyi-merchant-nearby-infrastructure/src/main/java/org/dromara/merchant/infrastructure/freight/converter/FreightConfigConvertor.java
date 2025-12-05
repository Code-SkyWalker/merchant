package org.dromara.merchant.infrastructure.freight.converter;

import cn.hutool.json.JSONObject;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.domain.freight.model.*;
import org.dromara.merchant.infrastructure.freight.gateway.dataobject.FreightConfigDO;
import org.mapstruct.*;

import java.util.List;

/**
 * @Description 商户配送配置转换器
 * @Author Code Skywalker
 * @Date 2025/12/4 14:49
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreightConfigConvertor {

    /**
     * DO转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true) // 忽略自动映射，使用AfterMapping手动处理
    FreightConfig toMerchantDeliveryConfigEntity(FreightConfigDO freightConfigDO);

    /**
     * Entity转DO
     */
    @Mapping(target = "deliveryConfig", ignore = true) // 忽略自动映射，使用AfterMapping手动处理
    FreightConfigDO toMerchantDeliveryConfigDO(FreightConfig freightConfig);

    /**
     * DO列表转Entity列表
     */
    List<FreightConfig> toMerchantDeliveryConfigListEntity(List<FreightConfigDO> freightConfigDOS);

    /**
     * Entity列表转DO列表
     */
    List<FreightConfigDO> toMerchantDeliveryConfigDO(List<FreightConfig> freightConfigs);

    /**
     * cmd转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true)
    FreightConfig toMerchantDeliveryConfig(FreightConfigCreateCmd cmd);


    @AfterMapping
    default void afterMappingDOToEntity(FreightConfigDO source, @MappingTarget FreightConfig target) {
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
    default void afterMappingEntityToDO(FreightConfig source, @MappingTarget FreightConfigDO target) {
        // 将 DeliveryConfig 对象转换为 JSON 字符串
        if (source.getDeliveryConfig() != null) {
            target.setDeliveryConfig(source.getDeliveryConfig().toJson());
        }
    }

    @AfterMapping
    default void afterMappingCmdToEntity(FreightConfigCreateCmd source, @MappingTarget FreightConfig target) {
        // 根据配送方式确定具体的配置类型
        String deliveryMethodStr = source.getDeliveryMethod();
        DeliveryMethod method = DeliveryMethod.getByCode(deliveryMethodStr);

        if (method == null || source.getDeliveryConfig() == null) {
            return;
        }

        // 使用JSON序列化/反序列化来转换客户端对象到领域对象
        DeliveryConfig deliveryConfig = null;
        JSONObject jsonObject = new JSONObject(source.getDeliveryConfig());

        switch (method) {
            case EXPRESS_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigExpress.class);
                break;
            case LOCAL_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigLocal.class);
                break;
            case PICKUP_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigPickup.class);
                break;
            case NONE_DELIVERY:
                deliveryConfig = jsonObject.toBean(DeliveryConfigNone.class);
                break;
            default:
                break;
        }

        if (deliveryConfig != null) {
            target.setDeliveryConfig(deliveryConfig);
        }
    }

}
