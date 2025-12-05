package org.dromara.merchant.client.freight.dto.data.command.query;

import lombok.Data;

/**
 * @Description 商户运费模板分页查询命令
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class ExpressTemplatePageQry {

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 模板状态
     */
    private String status;

}
