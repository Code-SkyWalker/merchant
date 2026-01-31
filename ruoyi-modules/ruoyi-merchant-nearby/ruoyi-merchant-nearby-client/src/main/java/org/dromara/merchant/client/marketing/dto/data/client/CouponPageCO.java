package org.dromara.merchant.client.marketing.dto.data.client;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 主键查询优惠券返回结果
 * @Author Code Skywalker
 * @Date 2025/12/30 10:30
 */
@Data
public class CouponPageCO {
    /**
     * 活动开始状态：'NOT_START'未开始，'START' 开始，'END' 结束
     */
    public static final String ACTIVITY_STATUS_NOT_START = "NOT_START";
    public static final String ACTIVITY_STATUS_START = "START";
    public static final String ACTIVITY_STATUS_END = "END";

    /**
     * 优惠券主键
     */
    private Long id;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 领取时间（开始）
     */
    private LocalDateTime receiveBegin;

    /**
     * 领取时间（结束）
     */
    private LocalDateTime receiveEnd;

    /**
     * 券使用时间（开始）
     */
    private LocalDateTime serviceBegin;

    /**
     * 券使用时间（结束）
     */
    private LocalDateTime serviceEnd;

    /**
     * 使用范围：'ONLINE'线上，'OFFLINE'线下，'NON_LIMIT'无限制
     */
    private String actuatingRange;

    /**
     * 发放张数
     */
    private Integer grantTotal;

    /**
     * 领取数
     */
    private Integer receiveCount;

    /**
     * 活动开始状态：'NOT_START'未开始，'START' 开始，'END' 结束
     */
    private String activityStatus;

    /**
     * 订单支付完成数
     */
    private Integer billPayedNum = 0;


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
