package org.dromara.merchant.client.marketing.dto.data.client;


import lombok.Data;

import java.time.LocalDateTime;

import static org.dromara.merchant.client.marketing.dto.data.client.CouponPageCO.*;

/**
 * @Description 营销活动分页查询结果
 * @Author Code Skywalker
 * @Date 2025/12/30 13:35
 */
@Data
public class MarketingPageCo {

    /**
     * 营销活动Id
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 营销活动商品数量
     */
    private Integer spuCount;

    /**
     * 活动开始状态：'NOT_START'未开始，'START' 开始，'END' 结束
     */
    private String activityStatus;

    /**
     * 活动时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减, BULK:n元n件
     */
    private String type;

    /**
     * 订单支付完成数
     */
    private Integer billPayedNum = 0;

    /**
     * 活动规则
     */
    public String getActivityStatus() {
        if (LocalDateTime.now().isAfter(this.receiveEnd)) {
            return ACTIVITY_STATUS_END;
        } else if (LocalDateTime.now().isBefore(this.receiveBegin)) {
            return ACTIVITY_STATUS_NOT_START;
        } else {
            return ACTIVITY_STATUS_START;
        }
    }

}
