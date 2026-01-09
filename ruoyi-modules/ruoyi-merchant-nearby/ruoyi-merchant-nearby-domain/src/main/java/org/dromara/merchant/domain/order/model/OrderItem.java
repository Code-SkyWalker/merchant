package org.dromara.merchant.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;

import java.math.BigDecimal;

/**
 * @Description 订单项实体
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderItem {

    /**
     * 订单项ID
     */
    private Long orderItemId = SnowflakeIdGenerator.generateId();

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品分类ID1
     */
    private Long categoryId1;

    /**
     * 商品分类ID2
     */
    private Long categoryId2;

    /**
     * 商品分类ID3
     */
    private Long categoryId3;

    /**
     * SPU ID
     */
    private Long spuId;

    /**
     * SKU ID
     */
    private Long skuId;

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
    private BigDecimal unitPrice;

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
     * 邮费
     */
    private BigDecimal postFee;

    /**
     * 扩展信息
     */
    private String extInfo;
}
