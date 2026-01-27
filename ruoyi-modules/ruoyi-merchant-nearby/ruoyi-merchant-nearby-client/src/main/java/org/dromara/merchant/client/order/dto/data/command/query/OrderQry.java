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
     * 用户昵称
     */
    private String username;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 订单状态
     * PENDING_PAYMENT -> 待付款
     * PENDING_DELIVERY -> 待发货
     * PENDING_RECEIPT -> 待收货
     * COMPLETED -> 已完成
     * CANCELLED -> 已取消
     * CLOSED -> 已关闭
     * REFUNDING -> 退款中
     * REFUNDED -> 已退款
     */
    private String status;

    /**
     * 订单类型
     * NORMAL -> 普通订单
     * SECKILL -> 秒杀订单
     * GROUP_BUY -> 团购订单
     * PRE_SALE -> 预售订单
     * VIP -> 会员订单
     */
    private String type;

    /**
     * 订单来源
     * PC -> PC端
     * APP -> APP端
     * WECHAT_MINI_PROGRAM -> 微信小程序
     * ALIPAY_MINI_PROGRAM -> 支付宝小程序
     * H5 -> H5端
     */
    private String source;

    /**
     * 开始时间
     */
    private String orderStartTime;

    /**
     * 结束时间
     */
    private String orderEndTime;

    /**
     * 开始时间
     */
    private String payStartTime;

    /**
     * 结束时间
     */
    private String payEndTime;

    /**
     * 关键词（用于模糊查询订单号、收货人姓名等）
     */
    private String keyword;
}
