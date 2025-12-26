DROP TABLE IF EXISTS `tb_marketing`;
CREATE TABLE `tb_marketing` (
  `id` bigint NOT NULL COMMENT '活动主键',
  `name` varchar(128) DEFAULT NULL COMMENT '活动名称',
  `receive_begin` datetime DEFAULT NULL COMMENT '活动时间（开始）',
  `receive_end` datetime DEFAULT NULL COMMENT '活动时间（结束）',
  `rules` mediumtext COMMENT '活动规则',
  `type` char(1) DEFAULT '0' COMMENT '活动类型：0:x件x折, 1:满折满减 2:n元n件',
  `merchant_id` bigint DEFAULT NULL COMMENT '商家Id',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`) USING BTREE COMMENT '活动类型索引',
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE COMMENT '店铺id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='营销活动'