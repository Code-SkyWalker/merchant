package org.dromara.merchant.client.commodity.dto.data.command;

import lombok.Data;

/**
 * @Description 商品参数模板修改参数
 * @Author Code Skywalker
 * @Date 2025/12/8 14:05
 */
@Data
public class ParaModifyCmd {

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

}
