package org.dromara.merchant.domain.marketing.model.coupon;

import lombok.Getter;

/**
 * @Description 优惠券适用类型
 * @Author Code Skywalker
 * @Date 2026/1/8 16:46
 */
@Getter
public enum CouponType {

    ONLINE("ONLINE", "线上"),
    OFFLINE("OFFLINE", "线下"),
    NON_LIMIT("NON_LIMIT", "无限制"),
    ;

    private final String code;
    private final String info;

    CouponType(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static CouponType getByCode(String code) {
        for (CouponType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
