package org.dromara.huifu.client.feerate.dto.client;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/2/2 10:31
 */
@Data
public class RateCO {

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商家logo
     */
    private String merchantLogo;

    /**
     * 商家比例
     */
    private BigDecimal merchantRate;

    /**
     * 平台比例
     */
    private BigDecimal platformRate;

    /**
     * 渠道比例
     */
    private BigDecimal channelRate;

}
