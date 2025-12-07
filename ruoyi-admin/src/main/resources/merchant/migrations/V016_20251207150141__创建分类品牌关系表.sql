drop TABLE if exists `tb_category_brand`;
CREATE TABLE `tb_category_brand` (
  `category_id` int NOT NULL COMMENT '分类ID',
  `brand_id` int NOT NULL COMMENT '品牌ID',
  PRIMARY KEY (`category_id`,`brand_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT '分类品牌关系表';