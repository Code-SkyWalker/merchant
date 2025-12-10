package org.dromara.merchant.client.commodity.dto.data.command.query;

import lombok.Data;

/**
 * @Description 模板条件查询参数
 * @Author Code Skywalker
 * @Date 2025/12/10 14:20
 */
@Data
public class TemplateQry {

    /**
     * 分类Id
     */
    private Integer categoryId;

    /**
     * 模板名称
     */
    private String templateName;

}
