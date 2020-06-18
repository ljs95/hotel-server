SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 入住账单表
-- ----------------------------
CREATE TABLE `housing_bill`  (
  `serial` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账单号',
  `room_id` int(11) NOT NULL COMMENT '房间id',
  `room_schedule_id` int(11) NOT NULL COMMENT '房间排期id',
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

-- ----------------------------
-- 入住操作表
-- ----------------------------
CREATE TABLE `hotel-server`.`housing_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_serial` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入住账单号',
  `type` int(255) NOT NULL COMMENT '操作类型：开房、续住',
  `mode` int(255) NOT NULL COMMENT '入住模式：全日房、钟点房',
  `time` int(11) NOT NULL COMMENT '入住天数/小时',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间',
  `end_time` bigint(20) NOT NULL COMMENT '结束时间',
  `snap` json NULL COMMENT '快照信息',
  PRIMARY KEY (`id`) USING BTREE,

  INDEX `index_bill_serial_and_mode`(`bill_serial`, `mode`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 入住账单表
-- ----------------------------
CREATE TABLE `hotel-server`.`housing_price`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入住账单',
  `deposit` int(11) NOT NULL COMMENT '押金',
  `room_price` int(11) NOT NULL COMMENT '房间入住费用',
  `other_price` json NULL COMMENT '其余费用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_bill_serial`(`bill_serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;