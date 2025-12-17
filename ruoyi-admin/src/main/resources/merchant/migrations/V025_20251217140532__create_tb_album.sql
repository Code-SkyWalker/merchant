DROP TABLE IF EXISTS `tb_album`;
CREATE TABLE `tb_album`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '编号',
    `type`        char(1)      DEFAULT '0' COMMENT '文件类型: 0文件夹 1文件',
    `name`        varchar(128) NOT NULL COMMENT '名称',
    `parent_id`   bigint       DEFAULT '0' COMMENT '父级Id',
    `merchant_id` bigint       DEFAULT NULL COMMENT '商家Id',
    `tenant_id`   varchar(20)  DEFAULT '000000' COMMENT '租户编号',
    `url`         varchar(500) DEFAULT NULL COMMENT '文件URL地址',
    `file_size`   bigint       DEFAULT NULL COMMENT '文件大小(字节)',
    `file_suffix` varchar(10)  DEFAULT NULL COMMENT '文件后缀名',
    `oss_id`      bigint       DEFAULT NULL COMMENT '关联的系统文件ID',
    `create_dept` bigint       DEFAULT NULL COMMENT '创建部门',
    `create_by`   bigint       DEFAULT NULL COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint       DEFAULT NULL COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uq_idx_name_type_merchant_id` (`type`, `name`, `merchant_id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='相册'
