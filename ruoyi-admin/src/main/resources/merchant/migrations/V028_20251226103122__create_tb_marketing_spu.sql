DROP TABLE IF EXISTS `tb_marketing_spu`;
CREATE TABLE `tb_marketing_spu` (
  `id` bigint NOT NULL COMMENT '关联主键',
  `rule_id` bigint NOT NULL COMMENT '活动主键',
  `spu_id` bigint NOT NULL COMMENT 'spuid',
  `rule_type` char(1) NOT NULL COMMENT '活动类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uq_rule_id_spu_id` (`rule_id`,`spu_id`) USING BTREE COMMENT '规则和商品唯一索引',
  KEY `idx_rule_type` (`rule_type`) USING BTREE COMMENT '规则类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '营销活动与商品关联表'