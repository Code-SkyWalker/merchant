ALTER TABLE `merchant_nearby`.`tb_category` MODIFY COLUMN `is_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否显示' AFTER `goods_num`;

ALTER TABLE `merchant_nearby`.`tb_category` MODIFY COLUMN `is_menu` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否导航' AFTER `is_show`;

ALTER TABLE `merchant_nearby`.`tb_category` MODIFY COLUMN `seq` int NULL DEFAULT NULL COMMENT '排序' AFTER `is_menu`;

ALTER TABLE `merchant_nearby`.`tb_category` MODIFY COLUMN `parent_id` int NULL DEFAULT NULL COMMENT '上级ID' AFTER `seq`;

ALTER TABLE `merchant_nearby`.`tb_category` ADD COLUMN `template_id` int NULL DEFAULT NULL AFTER `parent_id`;