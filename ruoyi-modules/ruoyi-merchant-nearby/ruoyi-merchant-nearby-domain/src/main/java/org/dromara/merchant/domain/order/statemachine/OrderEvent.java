package org.dromara.merchant.domain.order.statemachine;


/**
 * @Description 订单事件枚举
 * @Author 订单体系改进
 * @Date 2026-01-05
 */
public enum OrderEvent {
    /** 支付事件 */
    PAY("PAY", "支付事件"),

    /** 取消事件 */
    CANCEL("CANCEL", "取消事件"),

    /** 发货事件 */
    DELIVER("DELIVER", "发货事件"),

    /** 确认收货事件 */
    CONFIRM_RECEIPT("CONFIRM_RECEIPT", "确认收货事件"),

    /** 申请退款事件 */
    APPLY_REFUND("APPLY_REFUND", "申请退款事件"),

    /** 同意退款事件 */
    APPROVE_REFUND("APPROVE_REFUND", "同意退款事件"),

    /** 拒绝退款事件 */
    REJECT_REFUND("REJECT_REFUND", "拒绝退款事件");

    private final String code;
    private final String desc;

    OrderEvent(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderEvent getByCode(String code) {
        for (OrderEvent event : values()) {
            if (event.getCode().equals(code)) {
                return event;
            }
        }
        return null;
    }
}
