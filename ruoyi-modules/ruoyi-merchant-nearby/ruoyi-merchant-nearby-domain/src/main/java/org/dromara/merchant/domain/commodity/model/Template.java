package org.dromara.merchant.domain.commodity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品模板
 * @Author Code Skywalker
 * @Date 2025/12/8 14:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template {

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
     * 租户ID
     */
    private String tenantId;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 添加规格数量
     */
    public void incrementSpecNum() {
        this.specNum++;
    }

    /**
     * 添加参数数量
     */
    public void incrementParaNum() {
        this.paraNum++;
    }

    /**
     * 减少规格数量
     */
    public void decrementSpecNum() {
        this.specNum--;
    }

    /**
     * 减少参数数量
     */
    public void decrementParaNum() {
        this.paraNum--;
    }

}
