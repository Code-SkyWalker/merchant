package org.dromara.merchant.domain.freight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.common.tenant.core.TenantEntity;
import org.springframework.util.StringUtils;

/**
 * @Description 商户配送配置实体
 * @Author Code Skywalker
 * @Date 2025/12/4 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FreightConfig extends TenantEntity {

    /**
     * 主键ID
     */
    private Long deliveryId = SnowflakeIdGenerator.generateId();

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 配送方式
     */
    private DeliveryMethod deliveryMethod;

    /**
     * 自定义名称
     */
    private String name;

    /**
     * 相关联表的主键
     */
    private String relationId;

    /**
     * 配送的相关配置
     */
    private DeliveryConfig deliveryConfig;


    public void setRelationId(String relationId) {
        if (StringUtils.hasText(this.relationId)) {
            this.relationId = this.relationId + "," + relationId;
        }
        else this.relationId = relationId;
    }
}
