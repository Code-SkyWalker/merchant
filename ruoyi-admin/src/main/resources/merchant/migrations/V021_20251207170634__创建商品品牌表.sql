DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(100) NOT NULL COMMENT '品牌名称',
  `image` varchar(1000) NULL DEFAULT '' COMMENT '品牌图片地址',
  `letter` char(1) NULL DEFAULT '' COMMENT '品牌的首字母',
  `seq` int(11) NULL DEFAULT NULL COMMENT '排序',
  `merchant_id` bigint DEFAULT null COMMENT '商家Id',
  `tenant_id` varchar(128) NOT NULL COMMENT '租户ID',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 325417 CHARACTER SET = utf8mb4 COMMENT = '品牌表' ROW_FORMAT = Dynamic;
