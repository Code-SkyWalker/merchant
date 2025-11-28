package org.dromara.merchant.domain.merchant.model;

/**
 * @Description 商户状态枚举
 * @Author Code Skywalker
 * @Date 2025/11/24 17:44
 */
public enum MerchantStatus {

    /**
     * 待审核
     */
    PENDING("待审核"),

    /**
     * 激活
     */
    ACTIVE("激活"),

    /**
     * 休眠
     */
    REPOSE("休眠"),

    /**
     * 禁用
     */
    DISABLED("禁用"),

    /**
     * 冻结
     */
    FROZEN("冻结");

    private final String description;

    MerchantStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
