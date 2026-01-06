package org.dromara.merchant.client.order.dto.data.command;

import lombok.Data;
import org.dromara.merchant.client.Command;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单创建命令
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class OrderCreateCmd implements Command {

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
     * 订单类型
     */
    private String orderType;

    /**
     * 订单来源
     */
    private String orderSource;

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
    private List<OrderItemCreateCmd> orderItems;

    /**
     * 发票信息
     */
    private InvoiceInfoCreateCmd invoiceInfo;

    /**
     * 营销信息
     */
    private OrderMarketingInfoCmd marketingInfo;

    /**
     * 扩展信息
     */
    private String extInfo;
}