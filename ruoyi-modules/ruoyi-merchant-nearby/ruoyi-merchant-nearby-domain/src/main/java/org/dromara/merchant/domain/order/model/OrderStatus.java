package org.dromara.merchant.domain.order.model;

/**
 * @Description 订单状态枚举
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public enum OrderStatus {
    /**
     * 待付款
     */
    PENDING_PAYMENT("PENDING_PAYMENT", "待付款"),

    /**
     * 待发货
     */
    PENDING_DELIVERY("PENDING_DELIVERY", "待发货"),

    /**
     * 待收货
     */
    PENDING_RECEIPT("PENDING_RECEIPT", "待收货"),

    /**
     * 已完成
     */
    COMPLETED("COMPLETED", "已完成"),

    /**
     * 已取消
     */
    CANCELLED("CANCELLED", "已取消"),

    /**
     * 已关闭
     */
    CLOSED("CLOSED", "已关闭"),

    /**
     * 退款中
     */
    REFUNDING("REFUNDING", "退款中"),

    /**
     * 已退款
     */
    REFUNDED("REFUNDED", "已退款");

    private final String code;
    private final String desc;

    OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatus getByCode(String code) {
        for (OrderStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}