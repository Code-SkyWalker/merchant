ALTER TABLE `merchant_nearby`.`tb_merchant_shipping_template` DROP COLUMN `tenantId`;

ALTER TABLE `merchant_nearby`.`tb_merchant_shipping_template` DROP COLUMN `free_shipping_amount`;

ALTER TABLE `merchant_nearby`.`tb_merchant_shipping_template` DROP COLUMN `free_shipping_quantity`;

ALTER TABLE `merchant_nearby`.`tb_merchant_shipping_template` ADD COLUMN `tenant_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID' AFTER `merchant_id`;