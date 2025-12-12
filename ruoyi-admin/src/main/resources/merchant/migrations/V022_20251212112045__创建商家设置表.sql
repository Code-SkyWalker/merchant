DROP TABLE IF EXISTS `tb_merchant_config`;
CREATE TABLE `tb_merchant_config` (
  `id` bigint NOT NULL COMMENT '商户主键',
  `stock_mode` char(1) DEFAULT NULL COMMENT '库存模式：0下单减库存 1付款减库存',
  `open_mode` char(1) DEFAULT NULL COMMENT '营业时间：024小时 1自定义',
  `tenant_id` varchar(128) NOT NULL COMMENT '租户ID',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '商家设置';