package org.dromara.merchant.domain.order.model;

/**
 * @Description 订单来源枚举
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
public enum OrderSource {
    /**
     * PC端
     */
    PC("PC", "PC端"),

    /**
     * APP端
     */
    APP("APP", "APP端"),

    /**
     * 微信小程序
     */
    WECHAT_MINI_PROGRAM("WECHAT_MINI_PROGRAM", "微信小程序"),

    /**
     * 支付宝小程序
     */
    ALIPAY_MINI_PROGRAM("ALIPAY_MINI_PROGRAM", "支付宝小程序"),

    /**
     * H5端
     */
    H5("H5", "H5端");

    private final String code;
    private final String desc;

    OrderSource(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderSource getByCode(String code) {
        for (OrderSource source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }
}