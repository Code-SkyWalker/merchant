package org.dromara.merchant.domain.freight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * @Description 商户运费模板实体
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ExpressTemplate extends BaseEntity {

    /**
     * 运费模板ID
     */
    private Long templateId = SnowflakeIdGenerator.generateId();

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 计费方式
     */
    private BillingMethod billingMethod;

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
     * 是否默认模板 (0:否, 1:是)
     */
    private Boolean isDefault;

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

}
