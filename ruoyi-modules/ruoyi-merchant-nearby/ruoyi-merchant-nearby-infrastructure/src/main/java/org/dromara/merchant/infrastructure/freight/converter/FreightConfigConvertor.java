package org.dromara.merchant.infrastructure.freight.converter;

import cn.hutool.json.JSONObject;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigCreateCmd;
import org.dromara.merchant.client.freight.dto.data.command.FreightConfigModifyCmd;
import org.dromara.merchant.domain.freight.model.*;
import org.dromara.merchant.domain.marketing.model.Rule;
import org.dromara.merchant.infrastructure.freight.mapper.dataobject.FreightConfigDO;
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
    @Mapping(target = "deliveryConfig", ignore = true)
    // 忽略自动映射，使用AfterMapping手动处理
    FreightConfig toMerchantDeliveryConfigEntity(FreightConfigDO freightConfigDO);

    /**
     * Entity转DO
     */
    @Mapping(target = "deliveryConfig", ignore = true)
    // 忽略自动映射，使用AfterMapping手动处理
    FreightConfigDO toMerchantDeliveryConfigDO(FreightConfig freightConfig);

    /**
     * DO列表转Entity列表
     */
    List<FreightConfig> toMerchantDeliveryConfigListEntity(List<FreightConfigDO> freightConfigDOS);

    /**
     * cmd转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true)
    FreightConfig toMerchantDeliveryConfig(FreightConfigCreateCmd cmd);

    /**
     * cmd转Entity
     */
    @Mapping(target = "deliveryConfig", ignore = true)
    FreightConfig toMerchantDeliveryConfig(FreightConfigModifyCmd cmd);


    @AfterMapping
    default void afterMappingDOToEntity(FreightConfigDO source, @MappingTarget FreightConfig target) {
        DeliveryConfig config = convertConfigToEntity(source.getDeliveryConfig(), source.getDeliveryMethod());
        if (config != null) target.setDeliveryConfig(config);
    }

    @AfterMapping
    default void afterMappingEntityToDO(FreightConfig source, @MappingTarget FreightConfigDO target) {
        if (source.getDeliveryConfig() != null) {
            target.setDeliveryConfig(source.getDeliveryConfig().toJson());
        }
    }

    @AfterMapping
    default void afterMappingCmdToEntity(FreightConfigCreateCmd source, @MappingTarget FreightConfig target) {
        DeliveryConfig config = convertConfigToEntity(new JSONObject(source.getDeliveryConfig()).toString(), source.getDeliveryMethod());
        if (config != null) target.setDeliveryConfig(config);
    }

    /**
     * 从规则JSON字符串和类型转换为具体的规则实体
     *
     * @param configJson 规则JSON字符串
     * @param type       营销类型
     * @return 具体的规则实体
     */
    default DeliveryConfig convertConfigToEntity(String configJson, String type) {
        if (configJson == null) return null;

        DeliveryMethod method = DeliveryMethod.getByCode(type);
        if (method == null) return null;

        // 使用JSON序列化/反序列化来转换客户端对象到领域对象
        JSONObject jsonObject = new JSONObject(configJson);

        return switch (method) {
            case EXPRESS_DELIVERY -> jsonObject.toBean(DeliveryConfigExpress.class);
            case LOCAL_DELIVERY -> jsonObject.toBean(DeliveryConfigLocal.class);
            case PICKUP_DELIVERY -> jsonObject.toBean(DeliveryConfigPickup.class);
            case NONE_DELIVERY -> jsonObject.toBean(DeliveryConfigNone.class);
        };

    }

}
