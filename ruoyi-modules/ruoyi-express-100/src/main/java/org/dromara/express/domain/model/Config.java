package org.dromara.express.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 快递100配置实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends TenantEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 授权码
     */
    private String customer;

    /**
     * 授权key
     */
    private String key;

    /**
     * 授权key
     */
    private String secret;

    /**
     * 是否启用：0-禁用，1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
