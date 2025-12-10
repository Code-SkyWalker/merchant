package org.dromara.merchant.client.commodity.dto.data.command.query;

import lombok.Data;

/**
 * @Description 规格条件查询参数
 * @Author Code Skywalker
 * @Date 2025/12/10 14:19
 */
@Data
public class SpecQry {

    /**
     * 模板Id
     */
    private Integer templateId;

    /**
     * 规格名称
     */
    private String specName;

}
