package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 分类树
 * @Author Code Skywalker
 * @Date 2025/12/10 14:36
 */
@Data
public class CategoryCascadeCO {

    /**
     * 分类ID
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 模板ID
     */
    private Integer templateId;

}
