package org.dromara.merchant.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 发票信息实体
 * @Author 订单体系设计
 * @Date 2026-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class InvoiceInfo {

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