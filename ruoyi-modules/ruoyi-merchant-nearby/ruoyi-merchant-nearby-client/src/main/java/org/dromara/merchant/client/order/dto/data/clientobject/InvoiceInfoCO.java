package org.dromara.merchant.client.order.dto.data.clientobject;

import lombok.Data;

/**
 * @Description 发票信息客户端对象
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
public class InvoiceInfoCO {

    /**
     * 发票类型：0-不开发票，1-个人，2-企业
     */
    private Integer invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 纳税人识别号
     */
    private String taxNumber;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 收票人邮箱
     */
    private String receiveEmail;

    /**
     * 收票人电话
     */
    private String receivePhone;

    /**
     * 扩展信息
     */
    private String extInfo;
}