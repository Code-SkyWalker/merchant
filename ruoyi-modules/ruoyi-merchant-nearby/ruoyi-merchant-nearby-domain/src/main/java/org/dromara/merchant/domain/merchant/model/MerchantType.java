package org.dromara.merchant.domain.merchant.model;

/**
 * @Description 商户类型枚举
 * @Author Code Skywalker
 * @Date 2025/11/24 17:45
 */
public enum MerchantType {

    /**
     * 个人商户
     */
    PERSONAL("个人商户"),

    /**
     * 个体工商户
     */
    INDIVIDUAL("个体工商户"),

    /**
     * 企业商户
     */
    ENTERPRISE("企业商户");

    private final String description;

    MerchantType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
