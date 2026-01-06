-- 订单表
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`
(
    `order_id`         bigint         NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `order_no`         varchar(64)    NOT NULL COMMENT '订单编号',
    `user_id`          bigint         NOT NULL COMMENT '用户ID',
    `user_name`        varchar(100)            DEFAULT NULL COMMENT '用户昵称',
    `merchant_id`      bigint         NOT NULL COMMENT '商户ID',
    `merchant_name`    varchar(200)            DEFAULT NULL COMMENT '商户名称',
    `status`           varchar(32)    NOT NULL COMMENT '订单状态',
    `type`             varchar(32)             DEFAULT NULL COMMENT '订单类型',
    `source`           varchar(32)             DEFAULT NULL COMMENT '订单来源',
    `goods_amount`     decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '商品总金额',
    `freight_amount`   decimal(10, 2)          DEFAULT '0.00' COMMENT '运费金额',
    `discount_amount`  decimal(10, 2)          DEFAULT '0.00' COMMENT '优惠金额',
    `coupon_amount`    decimal(10, 2)          DEFAULT '0.00' COMMENT '优惠券抵扣金额',
    `point_amount`     decimal(10, 2)          DEFAULT '0.00' COMMENT '积分抵扣金额',
    `payable_amount`   decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '应付金额',
    `paid_amount`      decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '实付金额',
    `payment_method`   varchar(32)             DEFAULT NULL COMMENT '支付方式',
    `payment_time`     datetime                DEFAULT NULL COMMENT '支付时间',
    `payment_order_no` varchar(64)             DEFAULT NULL COMMENT '支付订单号',
    `refund_order_no`  varchar(64)             DEFAULT NULL COMMENT '退款订单号',
    `refund_time`      datetime                DEFAULT NULL COMMENT '退款时间',
    `receiver_name`    varchar(50)             DEFAULT NULL COMMENT '收货人姓名',
    `receiver_phone`   varchar(20)             DEFAULT NULL COMMENT '收货人电话',
    `receiver_address` varchar(500)            DEFAULT NULL COMMENT '收货人地址',
    `delivery_method`  varchar(32)             DEFAULT NULL COMMENT '配送方式',
    `remark`           varchar(500)            DEFAULT NULL COMMENT '订单备注',
    `invoice_info`     json                    DEFAULT NULL COMMENT '发票信息',
    `ext_info`         json                    DEFAULT NULL COMMENT '扩展信息',
    `version`          int            NOT NULL DEFAULT '0' COMMENT '版本号',
    `tenant_id`        varchar(20)    NOT NULL DEFAULT '000000' COMMENT '租户编号',
    `create_dept`      bigint                  DEFAULT NULL COMMENT '创建部门',
    `create_by`        bigint                  DEFAULT NULL COMMENT '创建人',
    `create_time`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`        bigint                  DEFAULT NULL COMMENT '修改人',
    `update_time`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`         int                     DEFAULT '0' COMMENT '删除标志',
    PRIMARY KEY (`order_id`) USING BTREE,
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表';



-- 订单状态索引说明
-- PENDING_PAYMENT: 待付款
-- PENDING_DELIVERY: 待发货
-- PENDING_RECEIPT: 待收货
-- COMPLETED: 已完成
-- CANCELLED: 已取消
-- CLOSED: 已关闭
-- REFUNDING: 退款中
-- REFUNDED: 已退款
