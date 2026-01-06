package org.dromara.merchant.domain.order.model;

/**
 * @Description 订单类型枚举
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public enum OrderType {
    /**
     * 普通订单
     */
    NORMAL("NORMAL", "普通订单"),

    /**
     * 秒杀订单
     */
    SECKILL("SECKILL", "秒杀订单"),

    /**
     * 团购订单
     */
    GROUP_BUY("GROUP_BUY", "团购订单"),

    /**
     * 预售订单
     */
    PRE_SALE("PRE_SALE", "预售订单"),

    /**
     * 会员订单
     */
    VIP("VIP", "会员订单");

    private final String code;
    private final String desc;

    OrderType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderType getByCode(String code) {
        for (OrderType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}