package org.dromara.huifu.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 16:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuifuConfig {

    public static final int CHANNEL_TYPE = 2;
    public static final int PLATFORM_TYPE = 1;
    public static final int MERCHANT_TYPE = 0;

    /**
     * 汇付商户ID
     */
    @NotNull
    private Long huifuId;

    /**
     * product_id 如果是平台，则不填
     */
    private String productId;

    /**
     * 商户私钥 如果是平台，则不填
     */
    private String privateKey;

    /**
     * 汇付公钥 如果是平台，则不填
     */
    private String publicKey;

    /**
     * 商家ID 如果是平台，则不填
     */
    private Long merchantId;

    /**
     * 配置类型：0 商户 1 平台 2 渠道服务商
     */
    @NotNull
    private Integer type;

    /**
     * 租户ID
     */
    private String tenantId;
}
