package org.dromara.merchant.domain.marketing.model;

import lombok.Getter;

/**
 * @Description 优惠券作用范围
 * @Author Code Skywalker
 * @Date 2026/1/8 16:52
 */
@Getter
public enum CouponScope {

    ALL("ALL", "所有商品"),
    INCLUDE("INCLUDE", "指定商品"),
    EXCLUDE("EXCLUDE", "排除商品"),
    ;

    private final String code;
    private final String info;

    CouponScope(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static CouponScope getByCode(String code) {
        for (CouponScope type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }


}
