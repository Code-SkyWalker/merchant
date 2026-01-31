package org.dromara.merchant.client.freight.dto.data.clientobject;

import lombok.Data;

import java.util.List;

/**
 * @Description 商户运费模板客户端对象
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class ExpressTemplateCO {

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
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 是否包邮 (0:不包邮, 1:包邮)
     */
    private Boolean freeShipping;

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
    private List<ExpressAreaCO> expressAreas;

}
