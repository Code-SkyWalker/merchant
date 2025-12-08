DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `goods_num` int DEFAULT '0' COMMENT '商品数量',
  `is_show` char(1) DEFAULT NULL COMMENT '是否显示',
  `is_menu` char(1) DEFAULT NULL COMMENT '是否导航',
  `seq` int DEFAULT NULL COMMENT '排序',
  `parent_id` int DEFAULT NULL COMMENT '上级ID',
  `merchant_id` bigint DEFAULT null COMMENT '商家Id',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11157 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='商品类目'
