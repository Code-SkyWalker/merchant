package org.dromara.merchant.client.merchant.dto.data.command;

import lombok.Data;

/**
 * @Description 商户配送配置创建命令
 * @Author Code Skywalker
 * @Date 2025/12/4 17:18
 */
@Data
public class MerchantDeliveryConfigCreateCmd {
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
     * 相关联表的主键
     */
    private String relationId;

    /**
     * 配送的相关配置
     */
    private DeliveryConfig deliveryConfig;
}
