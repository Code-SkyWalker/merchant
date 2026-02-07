
alter table tb_order
    add split_time datetime null comment '确认分账时间' after split_confirm_no;
