package org.dromara.merchant.client.order.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单客户端对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderPageCO {

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
     * 商户名称
     */
    private String merchantName;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 商品总金额
     */
    private BigDecimal goodsAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal pointAmount;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

    /**
     * 实付金额
     */
    private BigDecimal paidAmount;

    /**
     * 订单项列表
     */
    private List<OrderItemCO> orderItems;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
