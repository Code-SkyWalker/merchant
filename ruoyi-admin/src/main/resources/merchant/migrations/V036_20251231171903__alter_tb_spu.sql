ALTER TABLE `merchant_nearby`.`tb_spu` DROP INDEX `idx_merchant_id`;

ALTER TABLE `merchant_nearby`.`tb_spu` ADD INDEX `tenant_id_merchant_id`(`merchant_id` ASC, `tenant_id` ASC) USING BTREE;

ALTER TABLE `merchant_nearby`.`tb_spu` ADD INDEX `idx_id_create_time`(`create_time` DESC, `id` ASC) USING BTREE;