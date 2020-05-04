/*
 Navicat Premium Data Transfer

 Source Server         : 56.3
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.56.3:3306
 Source Schema         : hotel-server

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 04/05/2020 21:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `user_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_admin`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'johnson', '$2a$10$ABDa3d/oNyqDfDJtA5MrWeHK3oVdiiudluK.lHkfeyHCbC17OYTbe', 'super_user', 1, 'http://qiniuyun.colablog.cn/%E7%B4%A2%E9%9A%86.jpg');
INSERT INTO `admin` VALUES (2, 'john', '$2a$10$L2kypZBH8A0cSMor/vyRXu.BwsXTApvXCwE0gaRbYf0b4scGuK4.S', 'ColaAdmin', 1, 'http://localhost:8080/file/2020/03/31/59188bfc-7da1-49ec-a1ee-f1ae13b57475.jpg');
INSERT INTO `admin` VALUES (7, 'testtt', '$2a$10$Ul6ENVfO./AstE4mQd6IFur8Z6ac.KOd2lvbG2wqKCmqr6gQXuJg6', 'testtt', 1, NULL);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (17, 6, 2);
INSERT INTO `admin_role` VALUES (65, 2, 2);
INSERT INTO `admin_role` VALUES (66, 1, 1);
INSERT INTO `admin_role` VALUES (67, 1, 2);
INSERT INTO `admin_role` VALUES (68, 7, 2);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '父Id',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名',
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'icon',
  `enabled` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, NULL, '/', '', NULL, '顶级', NULL, 0);
INSERT INTO `menu` VALUES (2, 1, '/basic', 'Basic', 'layout', '基础管理', 'list', 1);
INSERT INTO `menu` VALUES (3, 2, '/basic/admin', 'Admin', '/basic/admin', '用户管理', 'people', 1);
INSERT INTO `menu` VALUES (4, 2, '/basic/role', 'Role', '/basic/role', '角色管理', 'peoples', 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `list_order` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, '基础模块', '/basic', 1, 999);
INSERT INTO `permission` VALUES (2, 1, '管理员模块', '/basic/admin', 1, 999);
INSERT INTO `permission` VALUES (3, 2, '查看管理员列表', '/basic/admin/table', 1, 999);
INSERT INTO `permission` VALUES (4, 2, '创建管理员', '/basic/admin/create', 1, 999);
INSERT INTO `permission` VALUES (5, 2, '更新管理员', '/basic/admin/update', 1, 999);
INSERT INTO `permission` VALUES (6, 2, '删除管理员', '/basic/admin/delete', 1, 999);
INSERT INTO `permission` VALUES (8, 1, '角色管理', '/basic/role', 1, 999);
INSERT INTO `permission` VALUES (9, 2, '启/禁用管理员', '/basic/admin/enabled', 1, 999);
INSERT INTO `permission` VALUES (10, 8, '添加角色', '/basic/role/create', 1, 999);
INSERT INTO `permission` VALUES (11, 8, '更新角色', '/basic/role/update', 1, 999);
INSERT INTO `permission` VALUES (12, 8, '删除角色', '/basic/role/delete', 1, 999);
INSERT INTO `permission` VALUES (13, 8, '修改角色权限', '/basic/role/editPermission', 1, 999);
INSERT INTO `permission` VALUES (14, 8, '查看角色列表', '/basic/role/table', 1, 998);
INSERT INTO `permission` VALUES (15, 1, '权限管理', '/basic/permission', 1, 999);
INSERT INTO `permission` VALUES (16, 15, '查看权限树', '/basic/permission/tree', 1, 999);
INSERT INTO `permission` VALUES (17, 15, '添加权限', '/basic/permission/create', 1, 999);
INSERT INTO `permission` VALUES (18, 15, '更新权限', '/basic/permission/update', 1, 999);
INSERT INTO `permission` VALUES (19, 15, '删除权限', '/basic/permission/delete', 1, 999);
INSERT INTO `permission` VALUES (21, 0, '客房管理', '/room', 1, 999);
INSERT INTO `permission` VALUES (22, 21, '房间管理', '/room/room', 1, 999);
INSERT INTO `permission` VALUES (23, 22, '添加房间', '/room/room/create', 1, 999);
INSERT INTO `permission` VALUES (24, 22, '更新房间', '/room/room/update', 1, 999);
INSERT INTO `permission` VALUES (28, 22, '查看房间数据', '/room/room/table', 1, 998);
INSERT INTO `permission` VALUES (29, 22, '删除房间', '/room/room/delete', 1, 999);
INSERT INTO `permission` VALUES (30, 21, '房型管理', '/room/type', 1, 999);
INSERT INTO `permission` VALUES (31, 30, '查看房型数据', '/room/type/table', 1, 999);
INSERT INTO `permission` VALUES (32, 30, '添加房型', '/room/type/create', 1, 999);
INSERT INTO `permission` VALUES (33, 30, '更新房型', '/room/type/update', 1, 999);
INSERT INTO `permission` VALUES (34, 30, '应用房型规格', '/room/type/applySpec', 1, 999);
INSERT INTO `permission` VALUES (35, 30, '删除房型', '/room/type/delete', 1, 999);
INSERT INTO `permission` VALUES (36, 21, '房型规格管理', '/room/spec', 1, 999);
INSERT INTO `permission` VALUES (37, 36, '查看房型规格数据', '/room/spec/table', 1, 999);
INSERT INTO `permission` VALUES (38, 36, '添加房型规格', '/room/spec/create', 1, 999);
INSERT INTO `permission` VALUES (39, 36, '更新房型规格', '/room/spec/update', 1, 999);
INSERT INTO `permission` VALUES (40, 36, '删除房型规格', '/room/spec/delete', 1, 999);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alias` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'root', '超级系统管理员', '666');
INSERT INTO `role` VALUES (2, 'admin', '普通管理员', '123');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 2, 2);
INSERT INTO `role_menu` VALUES (2, 2, 3);
INSERT INTO `role_menu` VALUES (3, 2, 4);
INSERT INTO `role_menu` VALUES (4, 1, 2);
INSERT INTO `role_menu` VALUES (5, 1, 3);
INSERT INTO `role_menu` VALUES (6, 1, 4);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (124, 2, 3);
INSERT INTO `role_permission` VALUES (125, 2, 4);
INSERT INTO `role_permission` VALUES (126, 2, 5);
INSERT INTO `role_permission` VALUES (127, 2, 8);
INSERT INTO `role_permission` VALUES (128, 2, 14);
INSERT INTO `role_permission` VALUES (129, 2, 10);
INSERT INTO `role_permission` VALUES (130, 2, 11);
INSERT INTO `role_permission` VALUES (131, 2, 12);
INSERT INTO `role_permission` VALUES (132, 2, 13);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间名称',
  `floor` int(255) NOT NULL COMMENT '楼层',
  `type_id` int(11) NOT NULL COMMENT '房型id',
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '101', 1, 1, 1);

-- ----------------------------
-- Table structure for room_spec
-- ----------------------------
DROP TABLE IF EXISTS `room_spec`;
CREATE TABLE `room_spec`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type_id` int(11) NOT NULL COMMENT '房型id',
  `price` json NULL COMMENT '价格配置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_spec
-- ----------------------------
INSERT INTO `room_spec` VALUES (1, '标准单人房（正常价）', 1, '{\"day\": 10000, \"hour\": 3000, \"deposit\": 200, \"dayContinueHour\": 1000}');
INSERT INTO `room_spec` VALUES (2, '1231', 1, '{\"day\": 20000, \"hour\": 2000, \"deposit\": 100, \"dayContinueHour\": 400}');

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房型名称',
  `number` int(11) NOT NULL COMMENT '床位',
  `spec_id` int(11) NULL DEFAULT NULL COMMENT '应用规格id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (1, '标准单人房', 1, 2);
INSERT INTO `room_type` VALUES (2, '标准双人房', 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
