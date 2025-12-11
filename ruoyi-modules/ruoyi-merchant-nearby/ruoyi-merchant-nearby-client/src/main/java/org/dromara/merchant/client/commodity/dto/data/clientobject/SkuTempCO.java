package org.dromara.merchant.client.commodity.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 参数模板分页数据CO
 * @Author Code Skywalker
 * @Date 2025/12/10 14:25
 */
@Data
public class SkuTempCO {

    /**
     * 名称
     */
    private String name;

    /**
     * 选项
     */
    private String options;

    /**
     * 模板类型 para：参数 spec：规格
     */
    private String type;

}
