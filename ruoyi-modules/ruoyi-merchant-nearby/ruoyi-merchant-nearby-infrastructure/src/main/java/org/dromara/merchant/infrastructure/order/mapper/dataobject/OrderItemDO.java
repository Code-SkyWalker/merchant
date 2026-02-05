package org.dromara.merchant.infrastructure.order.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Description 订单项数据对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("tb_order_item")
public class OrderItemDO {

    /**
     * 订单项ID
     */
    @TableId(value = "order_item_id", type = IdType.ASSIGN_ID)
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
