/*
 Navicat Premium Data Transfer

 Source Server         : 132
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.138.132:3306
 Source Schema         : hotel-server

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 01/04/2020 18:02:18
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'johnson', '$2a$10$ABDa3d/oNyqDfDJtA5MrWeHK3oVdiiudluK.lHkfeyHCbC17OYTbe', 'super_user', 1, 'http://qiniuyun.colablog.cn/%E7%B4%A2%E9%9A%86.jpg');
INSERT INTO `admin` VALUES (2, 'john', '$2a$10$HxI9V79bW/CYqbUflovmuO9.pGJnbGWyn8rETFxhm2Yi5czNKEdh.', 'ColaAdmin', 1, 'http://localhost:8080/file/2020/03/31/59188bfc-7da1-49ec-a1ee-f1ae13b57475.jpg');
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (17, 6, 2);
INSERT INTO `admin_role` VALUES (54, 7, 2);
INSERT INTO `admin_role` VALUES (55, 2, 1);
INSERT INTO `admin_role` VALUES (60, 1, 1);

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
INSERT INTO `role` VALUES (1, 'root', '超级系统管理员', NULL);
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

SET FOREIGN_KEY_CHECKS = 1;
