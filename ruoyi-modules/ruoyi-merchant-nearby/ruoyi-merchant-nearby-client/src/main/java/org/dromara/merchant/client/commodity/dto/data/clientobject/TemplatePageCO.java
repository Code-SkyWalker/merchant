package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 模板分页数据CO
 * @Author Code Skywalker
 * @Date 2025/12/10 14:29
 */
@Data
public class TemplatePageCO {

    /**
     * ID
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
     * 分类ID
     */
    private String categoryId;

}
