-- ----------------------------
-- 商户运费模板表
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_shipping_template`;
CREATE TABLE `tb_merchant_shipping_template` (
  `template_id` bigint NOT NULL COMMENT '运费模板ID',
  `merchant_id` bigint NOT NULL COMMENT '商户ID',
  `tenantId` varchar(128) NOT NULL COMMENT '租户ID',
  `template_name` varchar(100) NOT NULL COMMENT '模板名称',
  `billing_method` varchar(20) NOT NULL COMMENT '计费方式 (WEIGHT:按重量计费, QUANTITY:按数量计费, VOLUME:按体积计费)',
  `is_default` char(1) NOT NULL DEFAULT '0' COMMENT '是否默认 (0:否, 1:是)',
  `free_shipping` tinyint(1) DEFAULT '0' COMMENT '是否包邮 (0:不包邮, 1:包邮)',
  `free_shipping_amount` decimal(10,2) DEFAULT NULL COMMENT '包邮条件金额 (单位: 元)',
  `free_shipping_quantity` int DEFAULT NULL COMMENT '包邮条件件数',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '模板状态 (ACTIVE:启用, DISABLED:禁用)',
  `sort` int DEFAULT '0' COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '模板描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`template_id`),
  KEY `idx_mst_merchant_id` (`merchant_id`),
  KEY `idx_mst_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商户运费模板表';
