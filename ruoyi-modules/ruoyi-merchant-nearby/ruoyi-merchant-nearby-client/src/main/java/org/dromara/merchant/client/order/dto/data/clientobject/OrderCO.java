package org.dromara.merchant.client.order.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 订单客户端对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderCO {

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
    private String userName;

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
     * 订单状态描述
     */
    private String statusDesc;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 订单类型描述
     */
    private String typeDesc;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 订单来源描述
     */
    private String sourceDesc;

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
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 支付订单号
     */
    private String paymentOrderNo;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 配送方式
     */
    private String deliveryMethod;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 订单项列表
     */
    private List<OrderItemCO> orderItems;

    /**
     * 发票信息
     */
    private InvoiceInfoCO invoiceInfo;

    /**
     * 扩展信息
     */
    private String extInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}