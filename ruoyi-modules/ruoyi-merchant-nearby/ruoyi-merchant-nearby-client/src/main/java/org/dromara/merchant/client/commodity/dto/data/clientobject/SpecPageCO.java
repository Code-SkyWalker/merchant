package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 规格模板分页数据CO
 * @Author Code Skywalker
 * @Date 2025/12/10 14:26
 */
@Data
public class SpecPageCO {

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
