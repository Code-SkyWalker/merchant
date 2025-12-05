package org.dromara.merchant.client.freight.dto.data.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 创建商户运费模板命令
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class ExpressTemplateCreateCmd {

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

    /**
     * 配送区域
     */
    private List<ExpressAreaCreateCmd> areas;

}
