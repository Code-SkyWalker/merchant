package org.dromara.merchant.infrastructure.order.converter;

import cn.hutool.json.JSONObject;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderCO;
import org.dromara.merchant.client.order.dto.data.clientobject.OrderItemCO;
import org.dromara.merchant.client.order.dto.data.command.OrderCancelCmd;
import org.dromara.merchant.client.order.dto.data.command.OrderCreateCmd;
import org.dromara.merchant.domain.freight.model.DeliveryMethod;
import org.dromara.merchant.domain.order.model.InvoiceInfo;
import org.dromara.merchant.domain.order.model.Order;
import org.dromara.merchant.domain.order.model.OrderItem;
import org.dromara.merchant.domain.order.model.OrderSource;
import org.dromara.merchant.domain.order.model.OrderStatus;
import org.dromara.merchant.domain.order.model.OrderType;
import org.dromara.merchant.infrastructure.order.mapper.dataobject.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * @Description 订单转换器
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderConvertor {

    /**
     * 将数据对象转换为订单实体
     *
     * @param orderDO 数据对象
     * @return 订单实体
     */
    @Mapping(target = "status", source = "status", qualifiedByName = "stringToOrderStatus")
    @Mapping(target = "type", source = "type", qualifiedByName = "stringToOrderType")
    @Mapping(target = "source", source = "source", qualifiedByName = "stringToOrderSource")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod", qualifiedByName = "stringToDeliveryMethod")
    @Mapping(target = "invoiceInfo", source = "invoiceInfo", qualifiedByName = "stringToInvoiceInfo")
    Order toEntity(OrderDO orderDO);

    /**
     * 将订单创建命令转换为订单实体
     *
     * @param cmd 订单创建命令
     * @return 订单实体
     */
    Order toEntity(OrderCreateCmd cmd);

    Order toEntity(OrderCancelCmd cmd);

    /**
     * 将订单实体转换为数据对象
     *
     * @param order 订单实体
     * @return 数据对象
     */
    @Mapping(target = "status", source = "status", qualifiedByName = "orderStatusToString")
    @Mapping(target = "type", source = "type", qualifiedByName = "orderTypeToString")
    @Mapping(target = "source", source = "source", qualifiedByName = "orderSourceToString")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod", qualifiedByName = "deliveryMethodToString")
    @Mapping(target = "invoiceInfo", source = "invoiceInfo", qualifiedByName = "invoiceInfoToString")
    OrderDO toDo(Order order);

    /**
     * 将订单实体转换为订单客户端对象
     *
     * @param order 订单实体
     * @return 订单客户端对象
     */
    OrderCO toOrderCO(Order order);

    /**
     * 将订单项实体转换为订单项客户端对象
     *
     * @param item 订单项实体
     * @return 订单项客户端对象
     */
    OrderItemCO toOrderItemCO(OrderItem item);

    /**
     * 将订单状态字符串转换为枚举
     *
     * @param status 状态字符串
     * @return 订单状态枚举
     */
    @Named("stringToOrderStatus")
    default OrderStatus stringToOrderStatus(String status) {
        return status != null ? OrderStatus.getByCode(status) : null;
    }

    /**
     * 将订单状态枚举转换为字符串
     *
     * @param status 订单状态枚举
     * @return 状态字符串
     */
    @Named("orderStatusToString")
    default String orderStatusToString(OrderStatus status) {
        return status != null ? status.getCode() : null;
    }

    /**
     * 将订单类型字符串转换为枚举
     *
     * @param type 类型字符串
     * @return 订单类型枚举
     */
    @Named("stringToOrderType")
    default OrderType stringToOrderType(String type) {
        return type != null ? OrderType.getByCode(type) : null;
    }

    /**
     * 将订单类型枚举转换为字符串
     *
     * @param type 订单类型枚举
     * @return 类型字符串
     */
    @Named("orderTypeToString")
    default String orderTypeToString(OrderType type) {
        return type != null ? type.getCode() : null;
    }

    /**
     * 将订单来源字符串转换为枚举
     *
     * @param source 来源字符串
     * @return 订单来源枚举
     */
    @Named("stringToOrderSource")
    default OrderSource stringToOrderSource(String source) {
        return source != null ? OrderSource.getByCode(source) : null;
    }

    /**
     * 将订单来源枚举转换为字符串
     *
     * @param source 订单来源枚举
     * @return 来源字符串
     */
    @Named("orderSourceToString")
    default String orderSourceToString(OrderSource source) {
        return source != null ? source.getCode() : null;
    }

    /**
     * 将配送方式字符串转换为枚举
     *
     * @param deliveryMethod 配送方式字符串
     * @return 配送方式枚举
     */
    @Named("stringToDeliveryMethod")
    default DeliveryMethod stringToDeliveryMethod(String deliveryMethod) {
        return deliveryMethod != null ? DeliveryMethod.getByCode(deliveryMethod) : null;
    }

    /**
     * 将配送方式枚举转换为字符串
     *
     * @param deliveryMethod 配送方式枚举
     * @return 配送方式字符串
     */
    @Named("deliveryMethodToString")
    default String deliveryMethodToString(DeliveryMethod deliveryMethod) {
        return deliveryMethod != null ? deliveryMethod.getCode() : null;
    }

    /**
     * 将发票信息json转换为发票信息对象
     *
     * @param info 发票信息JSON字符串
     * @return 发票信息对象
     */
    @Named("stringToInvoiceInfo")
    default InvoiceInfo stringToInvoiceInfo(String info) {
        if (info == null || info.isEmpty()) {
            return null;
        }
        return new JSONObject(info).toBean(InvoiceInfo.class);
    }

    /**
     * 将发票信息对象转换为json字符串
     *
     * @param info 发票信息对象
     * @return 发票信息JSON字符串
     */
    @Named("invoiceInfoToString")
    default String invoiceInfoToString(InvoiceInfo info) {
        if (info == null) {
            return null;
        }
        return new JSONObject(info).toString();
    }
}
