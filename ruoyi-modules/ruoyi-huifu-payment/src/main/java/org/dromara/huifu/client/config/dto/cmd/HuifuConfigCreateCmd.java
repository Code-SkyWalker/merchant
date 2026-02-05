package org.dromara.huifu.client.config.dto.cmd;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/11/13 17:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuifuConfigCreateCmd {

    /** 汇付商户ID */
    @NotNull
    private Long huifuId;

    /** product_id 如果是运营平台（租户），则不填 */
    @NotBlank
    private String productId;

    /** 商户私钥 如果是运营平台（租户），则不填 */
    private String privateKey;

    /** 汇付公钥 如果是运营平台（租户），则不填 */
    private String publicKey;

    /** 商家ID */
    private Long merchantId;

    /** 配置类别：0：普通商家 1：运营平台（租户） 2：汇付渠道商（益巨科技） */
    @NotNull
    private Integer type;

}
