alter table tb_order
    add split_confirm_no varchar(64) null comment '分账后退款请求流水号' after payment_order_no;

alter table tb_order
    add refund_amount decimal(10, 2) default 0 null comment '累计退款金额' after refund_time;

