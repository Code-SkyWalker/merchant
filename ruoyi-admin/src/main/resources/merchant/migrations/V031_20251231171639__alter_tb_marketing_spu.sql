ALTER TABLE `merchant_nearby`.`tb_marketing_spu` DROP COLUMN `rule_id`;

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` DROP COLUMN `rule_type`;

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` DROP INDEX `idx_uq_rule_id_spu_id`;

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` MODIFY COLUMN `id` bigint NOT NULL COMMENT '关联主键' FIRST;

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` ADD COLUMN `marketing_id` bigint NOT NULL COMMENT '活动主键' AFTER `id`;

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` ADD UNIQUE INDEX `idx_uq_rule_id_spu_id`(`marketing_id` ASC, `spu_id` ASC) USING BTREE COMMENT '规则和商品唯一索引';

ALTER TABLE `merchant_nearby`.`tb_marketing_spu` MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联主键';
