package org.dromara.merchant.domain.merchant.model;

/**
 * @Description 计费方式枚举
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
public enum BillingMethod {
    
    /**
     * 按重量计费
     */
    WEIGHT("WEIGHT", "按重量计费"),
    
    /**
     * 按数量计费
     */
    QUANTITY("QUANTITY", "按数量计费"),
    
    /**
     * 按体积计费
     */
    VOLUME("VOLUME", "按体积计费");
    
    private final String code;
    private final String info;
    
    BillingMethod(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
    
    public static BillingMethod getByCode(String code) {
        for (BillingMethod method : values()) {
            if (method.getCode().equals(code)) {
                return method;
            }
        }
        return null;
    }
}