package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单营销信息命令
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
@Data
public class OrderMarketingInfoCmd {

    /**
     * 优惠券ID列表
     */
    private List<Long> couponIds;

    /**
     * 优惠券金额
     */
    private BigDecimal couponAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal pointAmount;

    /**
     * 营销活动ID
     */
    private Long marketingActivityId;

    /**
     * 营销活动折扣
     */
    private BigDecimal marketingDiscount;

    /**
     * 扩展信息
     */
    private String extInfo;
}