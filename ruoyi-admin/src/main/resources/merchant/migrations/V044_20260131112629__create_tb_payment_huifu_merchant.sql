DROP TABLE IF EXISTS tb_payment_huifu_merchant;
CREATE TABLE `tb_payment_huifu_merchant` (
  `huifu_id` BIGINT NOT NULL COMMENT '商户号',
  `product_id` VARCHAR (64) DEFAULT NULL COMMENT '产品ID',
  `private_key` MEDIUMTEXT COMMENT '商户私钥',
  `public_key` MEDIUMTEXT COMMENT '汇付公钥',
  `merchant_id` BIGINT DEFAULT NULL COMMENT '商家ID',
  `type` CHAR(1) DEFAULT '0' COMMENT '类别：0：普通商家 1：运营平台（租户） 2：汇付渠道商（益巨科技）',
  `tenant_id` VARCHAR (128) NOT NULL COMMENT '租户ID',
  `create_dept` BIGINT DEFAULT NULL COMMENT '创建部门',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` BIGINT DEFAULT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`huifu_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '汇付支付商户表';
