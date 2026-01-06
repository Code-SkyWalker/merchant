-- 订单项表
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item`
(
    `order_item_id`     bigint         NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
    `merchant_id`       bigint         NOT NULL COMMENT '商户ID',
    `merchant_name`     varchar(200)   NOT NULL COMMENT '商户名称',
    `order_id`          bigint         NOT NULL COMMENT '订单ID',
    `category_id1`      bigint         DEFAULT NULL COMMENT '一级分类ID',
    `category_id2`      bigint         DEFAULT NULL COMMENT '二级分类ID',
    `category_id3`      bigint         DEFAULT NULL COMMENT '三级分类ID',
    `spu_id`            bigint         DEFAULT NULL COMMENT 'SPU ID',
    `sku_id`            bigint         NOT NULL COMMENT 'SKU ID',
    `sku_name`          varchar(200)   NOT NULL COMMENT 'SKU名称',
    `sku_pic`           varchar(500)   DEFAULT NULL COMMENT 'SKU图片',
    `sku_spec`          varchar(500)   DEFAULT NULL COMMENT 'SKU规格',
    `price`             decimal(10, 2) NOT NULL COMMENT '商品单价',
    `quantity`          int            NOT NULL COMMENT '购买数量',
    `subtotal`          decimal(10, 2) NOT NULL COMMENT '小计金额',
    `discount_amount`   decimal(10, 2) DEFAULT '0.00' COMMENT '优惠金额',
    `final_amount`      decimal(10, 2) NOT NULL COMMENT '优惠后金额',
    `commission_rate`   decimal(5, 2)  DEFAULT NULL COMMENT '佣金比例',
    `commission_amount` decimal(10, 2) DEFAULT NULL COMMENT '佣金金额',
    `post_fee`          decimal(10, 2) DEFAULT '0.00' COMMENT '邮费金额',
    `ext_info`          json           DEFAULT NULL COMMENT '扩展信息',
    PRIMARY KEY (`order_item_id`) USING BTREE,
    KEY `idx_order_id` (`order_id`),
    KEY `idx_sku_id` (`sku_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单项表';
