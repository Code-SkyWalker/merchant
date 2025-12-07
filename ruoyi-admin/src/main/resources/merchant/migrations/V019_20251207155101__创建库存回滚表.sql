DROP TABLE IF EXISTS `tb_stock_back`;
CREATE TABLE `tb_stock_back` (
  `order_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单id',
  `sku_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'SKU的id',
  `num` int DEFAULT NULL COMMENT '回滚数量',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '回滚状态',
  `back_time` datetime DEFAULT NULL COMMENT '回滚时间',
  `merchant_id` bigint DEFAULT null COMMENT '商家Id',
  `tenant_id` varchar(128) NOT NULL COMMENT '租户ID',
  `join_time` datetime DEFAULT NULL COMMENT '入驻时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`,`sku_id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT '库存回滚';