-- 快递100配置表
DROP TABLE IF EXISTS `tb_express_config`;
CREATE TABLE `tb_express_config` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `merchant_id` bigint DEFAULT NULL COMMENT '商家ID',
    `express_code` varchar(50) DEFAULT NULL COMMENT '快递公司编码',
    `express_name` varchar(100) DEFAULT NULL COMMENT '快递公司名称',
    `customer` varchar(100) DEFAULT NULL COMMENT '授权码',
    `key` varchar(100) DEFAULT NULL COMMENT '授权key',
    `secret` varchar(100) DEFAULT NULL COMMENT '授权secret',
    `status` tinyint DEFAULT '1' COMMENT '是否启用：0-禁用，1-启用',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `tenant_id`            VARCHAR(64)     DEFAULT NULL               COMMENT '租户ID',
    `create_dept`          BIGINT(20)      DEFAULT NULL               COMMENT '创建部门',
    `create_by`            BIGINT(20)      DEFAULT NULL               COMMENT '创建者',
    `create_time`          DATETIME                                   COMMENT '创建时间',
    `update_by`            BIGINT(20)      DEFAULT NULL               COMMENT '更新者',
    `update_time`          DATETIME                                   COMMENT '更新时间',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='快递100配置表';
