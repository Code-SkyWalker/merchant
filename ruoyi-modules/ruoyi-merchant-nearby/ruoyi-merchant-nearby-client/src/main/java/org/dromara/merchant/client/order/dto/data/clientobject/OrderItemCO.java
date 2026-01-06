package org.dromara.merchant.client.order.dto.data.clientobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 订单项客户端对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderItemCO {

    /**
     * 订单项ID
     */
    private Long orderItemId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * SPU ID
     */
    private Long spuId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SPU名称
     */
    private String spuName;

    /**
     * SKU名称
     */
    private String skuName;

    /**
     * SKU图片
     */
    private String skuPic;

    /**
     * SKU规格
     */
    private String skuSpec;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 小计金额
     */
    private BigDecimal subtotal;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 优惠后金额
     */
    private BigDecimal finalAmount;

    /**
     * 佣金比例
     */
    private BigDecimal commissionRate;

    /**
     * 佣金金额
     */
    private BigDecimal commissionAmount;

    /**
     * 扩展信息
     */
    private String extInfo;
}