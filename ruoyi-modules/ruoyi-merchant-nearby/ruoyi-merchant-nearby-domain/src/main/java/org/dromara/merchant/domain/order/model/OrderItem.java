package org.dromara.merchant.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.core.utils.SnowflakeIdGenerator;
import org.dromara.huifu.app.payment.executor.CommodityDetail;

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
     * 小计金额
     */
    private BigDecimal subtotal;

    /**
     * 扩展信息
     */
    private String extInfo;

    public CommodityDetail toCommodityDetail() {
        return new CommodityDetail(
            this.skuId,
            this.skuName,
            this.finalAmount,
            this.quantity
            );
    }
}
