DROP TABLE IF EXISTS `tb_pref`;
CREATE TABLE `tb_pref` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `buy_money` int DEFAULT NULL COMMENT '消费金额',
  `pre_money` int DEFAULT NULL COMMENT '优惠金额',
  `start_time` date DEFAULT NULL COMMENT '活动开始日期',
  `end_time` date DEFAULT NULL COMMENT '活动截至日期',
  `type` char(1) DEFAULT NULL COMMENT '类型,1:普通订单，2：限时活动',
  `state` char(1) DEFAULT NULL COMMENT '状态,1:有效，0：无效',
  `merchant_id` bigint DEFAULT null COMMENT '商家Id',
  `tenant_id` varchar(128) NOT NULL COMMENT '租户ID',
  `join_time` datetime DEFAULT NULL COMMENT '入驻时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT '优惠规则表';
