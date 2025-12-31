ALTER TABLE `merchant_nearby`.`tb_merchant_certify` DROP INDEX `uk_merchant_code`;

ALTER TABLE `merchant_nearby`.`tb_merchant_certify` MODIFY COLUMN `approval_status` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '审批状态: PENDING待审批 CANCEL审核取消 APPROVED审批通过 REJECTED审批拒绝' AFTER `certified_type`;