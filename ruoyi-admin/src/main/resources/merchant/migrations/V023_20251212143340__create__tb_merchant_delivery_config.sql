DROP TABLE IF EXISTS `tb_merchant_delivery_config`;
CREATE TABLE `tb_merchant_delivery_config` (
  `delivery_id` bigint NOT NULL COMMENT '主键ID',
  `merchant_id` bigint DEFAULT NULL COMMENT '商户ID',
  `delivery_method` varchar(20) DEFAULT NULL COMMENT '配送方式（EXPRESS_DELIVERY:快递配送,LOCAL_DELIVERY:同城配送,PICKUP_DELIVERY:自提,NONE_DELIVERY:无配送）',
  `name` varchar(64) DEFAULT NULL COMMENT '自定义名称',
  `relation_id` varchar(255) DEFAULT NULL COMMENT '相关联表的主键',
  `delivery_config` mediumtext COMMENT '配送的相关配置',
  `default_user` char(1) DEFAULT NULL COMMENT '是否默认配送方式 0否 1是',
  `tenant_id` varchar(128) DEFAULT NULL COMMENT '租户ID',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\r\n\r\n',
  PRIMARY KEY (`delivery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '商户配送模板配置表';