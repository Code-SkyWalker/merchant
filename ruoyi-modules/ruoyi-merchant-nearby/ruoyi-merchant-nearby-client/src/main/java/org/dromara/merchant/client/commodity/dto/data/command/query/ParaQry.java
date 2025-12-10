package org.dromara.merchant.client.commodity.dto.data.command.query;

import lombok.Data;

/**
 * @Description 参数条件查询参数
 * @Author Code Skywalker
 * @Date 2025/12/10 14:18
 */
@Data
public class ParaQry {

    /**
     * 模板Id
     */
    private Integer templateId;

    /**
     * 参数名称
     */
    private String paraName;

}
