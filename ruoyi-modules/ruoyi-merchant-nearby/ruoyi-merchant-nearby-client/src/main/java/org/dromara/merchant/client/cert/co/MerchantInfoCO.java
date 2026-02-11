package org.dromara.merchant.client.cert.co;

import lombok.Data;

/**
 * 商家信息CO
 *
 * @author Code Skywalker
 */
@Data
public class MerchantInfoCO {

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户logo
     */
    private String logo;

    /**
     * 商户描述
     */
    private String description;
}