DROP TABLE IF EXISTS `tb_album`;
CREATE TABLE `tb_album` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) default 0 COMMENT '文件类型: 0文件夹 1文件',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `parent_id` bigint default NULL COMMENT '父级Id',
  `merchant_id` bigint DEFAULT null COMMENT '商家Id',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT '相册';