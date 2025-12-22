package org.dromara.merchant.domain.merchant.model;

/**
 * @Description 商户审批状态枚举
 * @Author Code Skywalker
 * @Date 2025/12/1 16:30
 */
public enum CertifyStatus {

    /**
     * 待审批
     */
    PENDING("PENDING", "待审批"),

    /**
     * 取消审批
     */
    CANCEL("CANCEL", "取消审批"),

    /**
     * 审批通过
     */
    APPROVED("APPROVED", "审批通过"),

    /**
     * 审批拒绝
     */
    REJECTED("REJECTED", "审批拒绝");

    private final String code;
    private final String description;

    CertifyStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code;
    }
}
