package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 参数模板分页数据CO
 * @Author Code Skywalker
 * @Date 2025/12/10 14:25
 */
@Data
public class ParaPageCO {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 选项
     */
    private String options;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 模板ID
     */
    private Integer templateId;

}
