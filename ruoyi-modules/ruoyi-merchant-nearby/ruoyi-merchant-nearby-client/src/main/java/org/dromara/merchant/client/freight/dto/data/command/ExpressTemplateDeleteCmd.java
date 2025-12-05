package org.dromara.merchant.client.freight.dto.data.command;

import lombok.Data;

/**
 * @Description 删除商户运费模板命令
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class ExpressTemplateDeleteCmd {

    /**
     * 运费模板ID
     */
    private Long templateId;

}
