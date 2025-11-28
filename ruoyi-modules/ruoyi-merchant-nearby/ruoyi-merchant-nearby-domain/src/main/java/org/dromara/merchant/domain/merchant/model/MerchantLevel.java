package org.dromara.merchant.domain.merchant.model;

/**
 * @Description 商户等级枚举
 * @Author Code Skywalker
 * @Date 2025/11/24 17:43
 */
public enum MerchantLevel {

    /**
     * 普通商户
     */
    NORMAL("普通商户", 0),

    /**
     * 银牌商户
     */
    SILVER("银牌商户", 1),

    /**
     * 金牌商户
     */
    GOLD("金牌商户", 2),

    /**
     * 钻石商户
     */
    DIAMOND("钻石商户", 3);

    private final String description;
    private final int rank;

    MerchantLevel(String description, int rank) {
        this.description = description;
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public int getRank() {
        return rank;
    }
}
