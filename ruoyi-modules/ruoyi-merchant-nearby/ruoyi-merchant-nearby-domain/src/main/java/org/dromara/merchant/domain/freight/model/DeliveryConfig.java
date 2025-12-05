package org.dromara.merchant.domain.freight.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @Description 配送配置接口
 * @Author Code Skywalker
 * @Date 2025/12/4 16:18
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DeliveryConfigExpress.class, name = "express"),
    @JsonSubTypes.Type(value = DeliveryConfigLocal.class, name = "local"),
    @JsonSubTypes.Type(value = DeliveryConfigPickup.class, name = "pickup"),
    @JsonSubTypes.Type(value = DeliveryConfigNone.class, name = "none")
})
public interface DeliveryConfig {

    String toJson();
}
