DROP TABLE IF EXISTS tb_shop_category_default;
CREATE TABLE `tb_shop_category_default` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `parent_id` bigint DEFAULT '0' COMMENT '上级id',
  `category_image` varchar(255) DEFAULT NULL COMMENT '分类图片',
  `category_name` varchar(16) DEFAULT NULL COMMENT '分类名称',
  `category_sort` int DEFAULT NULL COMMENT '分类排序',
  `state` char(1) DEFAULT '1' COMMENT '0:禁用 1:启用',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9994 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商家统一分类表';

INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9921, 0, NULL, '美食', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9922, 0, NULL, '按摩足疗', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9923, 0, NULL, '生活服务', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9924, 0, NULL, '美发', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9925, 0, NULL, '美容', 5, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9926, 0, NULL, '服装服饰', 6, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9927, 0, NULL, '水果蔬菜', 7, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9928, 0, NULL, '亲子乐园', 8, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9929, 0, NULL, '健身运动', 9, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9930, 0, NULL, '日用百货', 10, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9931, 0, NULL, '浪漫鲜花', 11, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9932, 0, NULL, '酒吧', 12, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9933, 0, NULL, '美甲美睫', 13, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9934, 0, NULL, '瑜伽舞蹈', 14, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9935, 0, NULL, '宠物', 15, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9936, 0, NULL, '拍照写真', 16, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9937, 9921, NULL, '火锅', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9938, 9921, NULL, '烧烤烤肉', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9939, 9921, NULL, '小龙虾', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9940, 9921, NULL, '米饭面馆', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9941, 9921, NULL, '包子粥点', 5, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9942, 9921, NULL, '炸鸡炸串', 6, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9943, 9921, NULL, '奶茶咖啡', 7, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9944, 9921, NULL, '蛋糕甜品', 8, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9945, 9922, NULL, '推拿', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9946, 9922, NULL, '精油SPA', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9947, 9922, NULL, '足部护理', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9948, 9922, NULL, '采耳', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9949, 9923, NULL, '家政保洁', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9950, 9923, NULL, '家电维修', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9951, 9923, NULL, '开锁换锁', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9952, 9923, NULL, '管道疏通', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9953, 9923, NULL, '房屋维修', 5, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9954, 9923, NULL, '家电清洗', 6, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9956, 9925, NULL, '美睫', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9957, 9925, NULL, '脱毛', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9958, 9925, NULL, '祛痘', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9959, 9925, NULL, '纹眉', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9960, 9926, NULL, '男装', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9961, 9926, NULL, '女装', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9962, 9926, NULL, '童装', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9964, 9928, NULL, '儿童乐园', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9965, 9928, NULL, '体育培训', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9966, 9928, NULL, '早教', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9967, 9928, NULL, '儿童摄影', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9968, 9929, NULL, '健身馆', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9969, 9929, NULL, '篮球', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9970, 9929, NULL, '羽毛球', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9971, 9929, NULL, '游泳', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9972, 9929, NULL, '乒乓球', 5, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9973, 9929, NULL, '瑜伽', 6, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9974, 9929, NULL, '舞蹈', 7, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9975, 9930, NULL, '运动户外', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9976, 9930, NULL, '手机数码', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9977, 9930, NULL, '服饰箱包', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9978, 9930, NULL, '家用电器', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9979, 9930, NULL, '玩具', 5, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9983, 9934, NULL, '瑜伽', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9984, 9934, NULL, '舞蹈', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9985, 9935, NULL, '购宠', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9986, 9935, NULL, '洗澡', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9987, 9935, NULL, '宠物医院', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9988, 9935, NULL, '宠物用品', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9989, 9936, NULL, '证件照', 1, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9990, 9936, NULL, '婚纱摄影', 2, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9991, 9936, NULL, '亲子照', 3, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9992, 9936, NULL, '孕妇照', 4, '1');
INSERT INTO `tb_shop_category_default` (`category_id`, `parent_id`, `category_image`, `category_name`, `category_sort`, `state`) VALUES (9993, 9936, NULL, '儿童摄影', 5, '1');
