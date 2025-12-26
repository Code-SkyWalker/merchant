package org.dromara.merchant.domain.marketing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.time.LocalDateTime;

/**
 * @Description 营销活动规则
 * @Author Code Skywalker
 * @Date 2025/12/26 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marketing {

    /**
     * 活动主键
     */
    private Long id = SnowflakeIdGenerator.generateId();

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 活动时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 活动规则
     */
    private Rule rules;

    /**
     * 活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减 BULK:n元n件
     */
    private MarketingType type;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 租户编号
     */
    private String tenantId;

}
