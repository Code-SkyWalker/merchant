package org.dromara.merchant.domain.freight.model;

/**
 * @Description 配送方式枚举
 * @Author Code Skywalker
 * @Date 2025/12/4 14:43
 */
public enum DeliveryMethod {

    /**
     * 快递配送
     */
    EXPRESS_DELIVERY("EXPRESS_DELIVERY", "快递配送"),

    /**
     * 同城配送
     */
    LOCAL_DELIVERY("LOCAL_DELIVERY", "同城配送"),

    /**
     * 自提
     */
    PICKUP_DELIVERY("PICKUP_DELIVERY", "自提"),

    /**
     * 无配送
     */
    NONE_DELIVERY("NONE_DELIVERY", "无配送");

    private final String code;
    private final String info;

    DeliveryMethod(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static DeliveryMethod getByCode(String code) {
        for (DeliveryMethod method : values()) {
            if (method.getCode().equals(code)) {
                return method;
            }
        }
        return null;
    }
}
