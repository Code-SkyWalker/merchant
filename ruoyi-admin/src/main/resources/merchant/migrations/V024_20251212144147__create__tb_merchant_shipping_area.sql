DROP TABLE IF EXISTS `tb_merchant_shipping_area`;
CREATE TABLE `tb_merchant_shipping_area` (
  `area_id` bigint NOT NULL COMMENT '配送区域ID',
  `template_id` bigint DEFAULT NULL COMMENT '运费模板ID',
  `tenant_id` varchar(128) DEFAULT NULL COMMENT '租户ID',
  `adcode` mediumtext COMMENT '区划代码集合',
  `base_weight_quantity_volume` decimal(10,2) DEFAULT NULL COMMENT '首重/首件/首体积 (单位: kg/件/m³)',
  `base_fee` decimal(10,2) DEFAULT NULL COMMENT '首费 (单位: 元)',
  `additional_weight_quantity_volume` decimal(10,2) DEFAULT NULL COMMENT '续重/续件/续体积 (单位: kg/件/m³)',
  `additional_fee` decimal(10,2) DEFAULT NULL COMMENT '续费 (单位: 元)',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\r\n',
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '快递运费模板表';