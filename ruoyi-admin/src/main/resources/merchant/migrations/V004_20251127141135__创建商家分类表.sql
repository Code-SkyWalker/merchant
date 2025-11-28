DROP TABLE IF EXISTS tb_shop_category;
CREATE TABLE `tb_shop_category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `tenant_id` varchar(128) DEFAULT NULL COMMENT '租户Id',
  `parent_id` bigint DEFAULT '0' COMMENT '上级id',
  `category_name` varchar(16) DEFAULT NULL COMMENT '分类名称',
  `category_image` varchar(255) DEFAULT NULL COMMENT '分类图片',
  `category_sort` int DEFAULT NULL COMMENT '分类排序',
  `state` char(1) DEFAULT '1' COMMENT '上下架 0下架 1上架',
  `create_dept` bigint NULL COMMENT '创建部门',
  `create_by` bigint NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL COMMENT '修改人',
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9921 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商家分类表'
