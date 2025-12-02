DROP TABLE IF EXISTS `tb_merchant_category`;
CREATE TABLE `tb_merchant_category` (
  `merchant_id` bigint NOT NULL COMMENT '商户ID',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  UNIQUE KEY `idx_unique_merchant_id_category_id` (`merchant_id`,`category_id`) USING BTREE COMMENT '商户ID和分类ID联合唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '商户分类关联表';