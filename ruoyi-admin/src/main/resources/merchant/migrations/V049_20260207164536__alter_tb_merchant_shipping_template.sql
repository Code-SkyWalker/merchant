alter table tb_merchant_shipping_template
    add free_shipping_amount decimal(10, 2) default 0 null comment '包邮满足金额' after free_shipping;

