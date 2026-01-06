package org.dromara.merchant.infrastructure.order.mapper.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.tenant.core.TenantEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 订单数据对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_order")
public class OrderDO extends TenantEntity {

    /**
     * 订单ID
     */
    @TableId
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
     * 发票信息（JSON格式）
     */
    private String invoiceInfo;

    /**
     * 扩展信息（JSON格式）
     */
    private String extInfo;

    /**
     * 版本号，用于乐观锁
     */
    private Integer version = 0;
}
