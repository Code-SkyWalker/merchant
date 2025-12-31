DROP TABLE IF EXISTS `tb_coupon_spu`;
CREATE TABLE `tb_coupon_spu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联主键',
  `coupon_id` bigint NOT NULL COMMENT '活动主键',
  `spu_id` bigint NOT NULL COMMENT 'spuid',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uq_rule_id_spu_id` (`coupon_id`,`spu_id`) USING BTREE COMMENT '优惠券和商品唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=1133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券与商品关联表'