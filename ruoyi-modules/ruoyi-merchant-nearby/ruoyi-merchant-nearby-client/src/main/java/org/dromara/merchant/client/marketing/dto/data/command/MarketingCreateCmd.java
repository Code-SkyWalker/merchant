package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 营销活动创建命令
 * @Author Code Skywalker
 * @Date 2025/12/26 14:49
 */
@Data
public class MarketingCreateCmd {

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
     * 活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减, BULK:n元n件，SECONDKILL:秒杀
     */
    private String type;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 营销活动与商品关联
     */
    private List<MarketingSpu> marketingSpus;

}
