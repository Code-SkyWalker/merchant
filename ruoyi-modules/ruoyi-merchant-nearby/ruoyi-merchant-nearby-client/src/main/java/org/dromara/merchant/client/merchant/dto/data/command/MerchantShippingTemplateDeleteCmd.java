package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.Data;

/**
 * @Description 删除商户运费模板命令
 * @Author Code Skywalker
 * @Date 2025/12/3 15:30
 */
@Data
public class MerchantShippingTemplateDeleteCmd {

    /**
     * 运费模板ID
     */
    private Long templateId;

}