package org.dromara.merchant.infrastructure.freight.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.tenant.core.TenantEntity;

import java.math.BigDecimal;

/**
 * @Description 商户运费模板DO对象
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_merchant_shipping_template")
public class ExpressTemplateDO extends TenantEntity {

    /**
     * 运费模板ID
     */
    @TableId
    private Long templateId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 计费方式 (WEIGHT:按重量计费, QUANTITY:按数量计费, VOLUME:按体积计费)
     */
    private String billingMethod;

    /**
     * 是否默认模板 (0:否, 1:是)
     */
    private Boolean isDefault;

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

}
