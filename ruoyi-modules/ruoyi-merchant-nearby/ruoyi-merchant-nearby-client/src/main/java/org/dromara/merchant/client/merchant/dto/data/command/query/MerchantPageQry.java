package org.dromara.merchant.client.merchant.dto.data.command.query;

import lombok.Data;

/**
 * @Description 商户分页查询命令
 * @Author Code Skywalker
 * @Date 2025/12/1 16:00
 */
@Data
public class MerchantPageQry {

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户编码
     */
    private String merchantCode;

    /**
     * 商户状态
     */
    private String status;

    /**
     * 认证状态
     */
    private Boolean certified;

}
