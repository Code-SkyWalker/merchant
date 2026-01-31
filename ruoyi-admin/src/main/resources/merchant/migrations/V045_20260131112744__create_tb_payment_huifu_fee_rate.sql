DROP TABLE IF EXISTS tb_payment_huifu_fee_rate;
CREATE TABLE `tb_payment_huifu_fee_rate` (
  `rate_id` BIGINT NOT NULL COMMENT '分账比例id',
  `fee_rate` DECIMAL (10, 2) DEFAULT '0.00' COMMENT '分账比例（百分比值）',
  `type` CHAR(2) DEFAULT 0 COMMENT '比例类别：0：普通商家 1：运营平台（租户） 2：汇付渠道商（益巨科技）',
  `merchant_id` BIGINT NOT NULL COMMENT '商家id',
  `note` VARCHAR (128) DEFAULT NULL COMMENT '备注',
  `tenant_id` VARCHAR (128) NOT NULL COMMENT '租户ID',
  `create_dept` BIGINT DEFAULT NULL COMMENT '创建部门',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` BIGINT DEFAULT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`rate_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '汇付分账比例';