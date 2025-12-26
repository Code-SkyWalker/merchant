DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` bigint NOT NULL COMMENT '优惠券主键',
  `name` varchar(128) DEFAULT NULL COMMENT '优惠券名称',
  `receive_begin` datetime DEFAULT NULL COMMENT '领取时间（开始）',
  `receive_end` datetime DEFAULT NULL COMMENT '领取时间（结束）',
  `service_begin` datetime DEFAULT NULL COMMENT '券使用时间（开始）',
  `service_end` datetime DEFAULT NULL COMMENT '券使用时间（结束）',
  `actuating_range` enum('ONLINE','OFFLINE','NON_LIMIT') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'ONLINE' COMMENT '使用范围：''ONLINE''线上，''OFFLINE''线下，''NON_LIMIT''无限制',
  `actuating_threshold` decimal(10,2) DEFAULT '0.00' COMMENT '使用门槛：0无门槛',
  `discount_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '活动优惠类型：0满减元 1满打折',
  `discount` decimal(10,2) DEFAULT '0.00' COMMENT '活动优惠额度',
  `grant_total` int DEFAULT '0' COMMENT '发放张数',
  `receive_count` int DEFAULT '0' COMMENT '领取数',
  `goods_range` enum('ALL','INCLUDE','EXCLUDE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'ALL' COMMENT '商品作用范围：''ALL''所有商品,''INCLUDE''指定商品,''EXCLUDE''排除商品',
  `merchant_id` bigint DEFAULT NULL COMMENT '商家Id',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券'