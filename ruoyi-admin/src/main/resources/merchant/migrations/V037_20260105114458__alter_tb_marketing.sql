alter table tb_marketing
    modify type varchar(128) default '0' null comment '活动类型：QUANTITY:x件x折, MULTIUNIT:满折满减, BULK:n元n件，SECONDKILL:秒杀';

