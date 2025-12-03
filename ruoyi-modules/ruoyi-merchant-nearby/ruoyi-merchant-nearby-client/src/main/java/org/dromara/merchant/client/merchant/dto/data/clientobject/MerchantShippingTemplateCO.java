package org.dromara.merchant.client.merchant.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 商户运费模板客户端对象
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class MerchantShippingTemplateCO {

    /**
     * 运费模板ID
     */
    private Long templateId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 计费方式 (WEIGHT:按重量计费, QUANTITY:按数量计费, VOLUME:按体积计费)
     */
    private String billingMethod;

    /**
     * 首重/首件/首体积 (单位: kg/件/m³)
     */
    private BigDecimal baseWeightQuantityVolume;

    /**
     * 首费 (单位: 元)
     */
    private BigDecimal baseFee;

    /**
     * 续重/续件/续体积 (单位: kg/件/m³)
     */
    private BigDecimal additionalWeightQuantityVolume;

    /**
     * 续费 (单位: 元)
     */
    private BigDecimal additionalFee;

    /**
     * 是否包邮 (0:不包邮, 1:包邮)
     */
    private Boolean freeShipping;

    /**
     * 包邮条件金额 (单位: 元)
     */
    private BigDecimal freeShippingAmount;

    /**
     * 包邮条件件数
     */
    private Integer freeShippingQuantity;

    /**
     * 模板状态 (ACTIVE:启用, DISABLED:禁用)
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 模板描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}