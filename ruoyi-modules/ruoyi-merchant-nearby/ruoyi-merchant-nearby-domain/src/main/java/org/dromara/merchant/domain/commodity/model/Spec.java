package org.dromara.merchant.domain.commodity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品规格模板
 * @Author Code Skywalker
 * @Date 2025/12/8 13:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spec {

    /**
     * ID
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
