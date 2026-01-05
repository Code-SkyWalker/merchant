package org.dromara.merchant.domain.marketing.model;

import lombok.Getter;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/26 14:38
 */
@Getter
public enum MarketingType {

    BULK("BULK", "N元N件模式"),
    MULTIUNIT("MULTIUNIT", "满折满减模式"),
    QUANTITY("QUANTITY", "x件x折模式"),
    SECONDKILL("SECONDKILL", "x件x折模式"),
    ;

    private final String code;
    private final String info;

    MarketingType(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static MarketingType getByCode(String code) {
        for (MarketingType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
