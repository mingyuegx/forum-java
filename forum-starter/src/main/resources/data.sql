-- ----------------------------
-- Records of forum_article_type
-- ----------------------------
BEGIN;

INSERT INTO `forum_article_type` VALUES (1, 'PASS', '融创东南海', '融创东南海', 0, 'USER', 16, 0, '2020-11-10 12:03:01', '2020-11-10 12:03:01');
INSERT INTO `forum_article_type` VALUES (2, 'PASS', '郡望府', '郡望府', 0, 'USER', 16, 0, '2020-11-10 12:03:01', '2020-11-10 12:03:01');
INSERT INTO `forum_article_type` VALUES (3, 'PASS', '蚂蚁空间', '蚂蚁空间', 0, 'USER', 16, 0, '2020-11-10 12:03:01', '2020-11-10 12:03:01');
COMMIT;

-- ----------------------------
-- Records of forum_tag
-- ----------------------------
BEGIN;
INSERT INTO `forum_tag` VALUES (188, 'PASS', '户外运动', '篮球', ' 户外运动篮球', 0, 25, 0, '2020-11-10 12:03:01', '2020-11-10 12:03:01');
COMMIT;


BEGIN;
INSERT INTO `forum_user`(`id`, `email`, `nickname`, `password`, `role`, `state`, `sex`, `avatar`, `signature`, `last_login_time`, `is_delete`, `create_at`, `update_at`) VALUES (1, 'admin@developers.pub', '管理员-0', '711475695fde28fc68fb2547cd2cf2ef', 'ADMIN', 'ENABLE', 'UNKNOWN', '', '开发者客栈管理员', '2020-10-01 14:00:01', 0, '2020-10-01 08:00:01', '2020-10-01 08:00:01');
INSERT INTO `forum_user`(`id`, `email`, `nickname`, `password`, `role`, `state`, `sex`, `avatar`, `signature`, `last_login_time`, `is_delete`, `create_at`, `update_at`) VALUES (2, 'superadmin@developers.pub', '开发者客栈', '711475695fde28fc68fb2547cd2cf2ef', 'SUPER_ADMIN', 'ENABLE', 'UNKNOWN', '', '开发者客栈官方超级管理员', '2020-10-01 14:00:01', 0, '2020-10-01 08:00:01', '2020-10-01 08:00:01');
COMMIT;
