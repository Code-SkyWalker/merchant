package org.dromara.huifu.infrastructure.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 汇付支付商户表
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_payment_huifu_merchant")
public class HuifuConfigDO extends BaseEntity {

    /**
     * 汇付商户号
     */
    @TableId(type = IdType.INPUT)
    private Long huifuId;

    /** product_id 如果是平台，则不填 */
    @NotBlank
    private String productId;

    /** 商户私钥 如果是平台，则不填 */
    private String privateKey;

    /** 汇付公钥 如果是平台，则不填 */
    private String publicKey;

    /** 商家ID 如果是平台，则不填 */
    private Long merchantId;

    /** 配置类型：0 商户 1 平台 2 渠道服务商 */
    @NotNull
    private Integer type;

}
