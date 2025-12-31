package org.dromara.merchant.client.freight.dto.data.clientobject;

import lombok.Data;
import org.dromara.merchant.client.freight.dto.data.command.DeliveryConfig;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/31 13:49
 */
@Data
public class FreightConfigCO {

    /**
     * 主键ID
     */
    private Long deliveryId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 配送方式
     */
    private String deliveryMethod;

    /**
     * 自定义名称
     */
    private String name;

    /**
     * 相关联表的主键(逗号隔开)
     */
    private String relationId;

    /**
     * 配送的相关配置
     */
    private DeliveryConfig deliveryConfig;

}
