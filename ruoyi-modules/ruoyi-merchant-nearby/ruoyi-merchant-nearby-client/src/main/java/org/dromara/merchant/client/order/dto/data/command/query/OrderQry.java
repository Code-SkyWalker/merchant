package org.dromara.merchant.client.order.dto.data.command.query;

import lombok.Data;

/**
 * @Description 订单查询命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderQry {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订单来源
     */
    private String orderSource;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 关键词（用于模糊查询订单号、收货人姓名等）
     */
    private String keyword;
}