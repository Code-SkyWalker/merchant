package org.dromara.express.infrastructure.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2026/1/19 17:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_express_config")
public class ConfigDO extends TenantEntity {

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
