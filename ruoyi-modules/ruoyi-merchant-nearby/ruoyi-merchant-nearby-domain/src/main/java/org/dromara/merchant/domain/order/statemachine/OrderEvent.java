package org.dromara.merchant.domain.order.statemachine;


/**
 * @Description 订单事件枚举
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
public enum OrderEvent {
    /** 支付事件 */
    PAY,

    /** 取消事件 */
    CANCEL,

    /** 发货事件 */
    DELIVER,

    /** 确认收货事件 */
    CONFIRM_RECEIPT,

    /** 完成事件 */
    COMPLETE,

    /** 申请退款事件 */
    APPLY_REFUND,

    /** 同意退款事件 */
    APPROVE_REFUND,

    /** 拒绝退款事件 */
    REJECT_REFUND;
}
