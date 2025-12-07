package org.dromara.merchant.infrastructure.freight.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_merchant_delivery_config")
public class FreightConfigDO extends BaseEntity {
    /**
     * 主键ID
     */
    @TableId
    private Long deliveryId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 配送方式（EXPRESS_DELIVERY:快递配送,LOCAL_DELIVERY:同城配送,PICKUP_DELIVERY:自提,NONE_DELIVERY:无配送）
     */
    private String deliveryMethod;

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
    private String deliveryConfig;

    /**
     * 租户ID
     */
    private String tenantId;
}
