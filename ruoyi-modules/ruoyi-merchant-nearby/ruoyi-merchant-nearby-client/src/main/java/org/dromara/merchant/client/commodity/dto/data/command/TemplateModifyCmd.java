package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

/**
 * @Description 模板修改参数
 * @Author Code Skywalker
 * @Date 2025/12/8 14:53
 */
@Data
public class TemplateModifyCmd {

    /**
     * 模板ID
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 规格数量
     */
    private Integer specNum;

    /**
     * 参数数量
     */
    private Integer paraNum;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 分类ID
     */
    private String categoryId;
}
