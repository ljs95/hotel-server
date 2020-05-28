SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 入住账单表
-- ----------------------------
CREATE TABLE `housing_bill`  (
  `serial` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账单号',
  `room_id` int(11) NOT NULL COMMENT '房间id',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间',
  `ent_time` bigint(20) NOT NULL COMMENT '结束时间',
  `snap` json NULL COMMENT '快照信息',
  `into_time` bigint(20) NULL DEFAULT NULL COMMENT '入住时间',
  `out_time` bigint(20) NULL DEFAULT NULL COMMENT '退房时间',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 房间排期表
-- ----------------------------
CREATE TABLE `room_schedule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL COMMENT '房间id',
  `start_time` bigint(11) NOT NULL COMMENT '开始时间',
  `end_time` bigint(11) NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
