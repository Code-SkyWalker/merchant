-- ----------------------------
-- 用户地址表
-- ----------------------------
DROP TABLE IF EXISTS tb_user_address;
CREATE TABLE tb_user_address (
    address_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '地址ID',
    user_id              BIGINT(20)      NOT NULL                   COMMENT '用户ID',
    receiver_name        VARCHAR(50)     NOT NULL                   COMMENT '收货人姓名',
    receiver_phone       VARCHAR(20)     NOT NULL                   COMMENT '收货人手机号',
    province             VARCHAR(50)     DEFAULT NULL                 COMMENT '省份',
    province_code        VARCHAR(20)     DEFAULT NULL                 COMMENT '省份代码',
    city                 VARCHAR(50)     DEFAULT NULL                 COMMENT '城市',
    city_code            VARCHAR(20)     DEFAULT NULL                 COMMENT '城市代码',
    district             VARCHAR(50)     DEFAULT NULL                 COMMENT '区县',
    district_code        VARCHAR(20)     DEFAULT NULL                 COMMENT '区县代码',
    detail_address       VARCHAR(200)    DEFAULT NULL                 COMMENT '详细地址',
    postal_code          VARCHAR(10)     DEFAULT NULL                 COMMENT '邮政编码',
    is_default           TINYINT(1)      DEFAULT 0                  COMMENT '是否默认地址 (0-否, 1-是)',
    address_label        VARCHAR(20)     DEFAULT NULL                 COMMENT '地址标签 (如：家、公司等)',
    latitude             VARCHAR(20)     DEFAULT NULL                 COMMENT '纬度坐标',
    longitude            VARCHAR(20)     DEFAULT NULL                 COMMENT '经度坐标',
    tenant_id            VARCHAR(64)     DEFAULT NULL               COMMENT '租户ID',
    create_dept          BIGINT(20)      DEFAULT NULL               COMMENT '创建部门',
    create_by            BIGINT(20)      DEFAULT NULL               COMMENT '创建者',
    create_time          DATETIME                                   COMMENT '创建时间',
    update_by            BIGINT(20)      DEFAULT NULL               COMMENT '更新者',
    update_time          DATETIME                                   COMMENT '更新时间',
    PRIMARY KEY (address_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '用户地址表';

-- ----------------------------
-- 索引
-- ----------------------------
CREATE INDEX idx_tb_user_address_user_id ON tb_user_address(user_id);
CREATE INDEX idx_tb_user_address_is_default ON tb_user_address(is_default);
