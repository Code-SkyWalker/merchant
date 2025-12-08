package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

/**
 * @Description 商品规格模板修改参数
 * @Author Code Skywalker
 * @Date 2025/12/8 13:37
 */
@Data
public class SpecModifyCmd {

    /**
     * 规格ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 规格选项
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
