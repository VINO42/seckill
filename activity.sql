/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : activity

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 25/09/2021 01:11:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_good
-- ----------------------------
DROP TABLE IF EXISTS `activity_good`;
CREATE TABLE `activity_good`  (
  `id` bigint(0) NOT NULL COMMENT '秒杀商品id',
  `goods_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id 对应真实商品的id',
  `seckill_price` int(0) NULL DEFAULT NULL COMMENT '秒杀商品价格 分单位',
  `store` int(0) NULL DEFAULT NULL COMMENT '库存',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者id',
  `creator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '更新者id',
  `updator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_good
-- ----------------------------

-- ----------------------------
-- Table structure for activity_info
-- ----------------------------
DROP TABLE IF EXISTS `activity_info`;
CREATE TABLE `activity_info`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `channel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动渠道',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动对象',
  `front_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端路径',
  `template` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `activity_good_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `sec_kill_remain_time` int(0) NULL DEFAULT NULL COMMENT '秒杀保留时间',
  `buy_times` int(0) NULL DEFAULT NULL COMMENT '每用户可购买次数',
  `limit_count` int(0) NULL DEFAULT NULL COMMENT '每用户可购买数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者id',
  `creator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名称',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '更新者id',
  `updator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_goods_id`(`activity_good_id`) USING BTREE COMMENT '商品id唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀活动信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_info
-- ----------------------------

-- ----------------------------
-- Table structure for seckill_record
-- ----------------------------
DROP TABLE IF EXISTS `seckill_record`;
CREATE TABLE `seckill_record`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `activity_id` bigint(0) NULL DEFAULT NULL COMMENT '秒杀活动表',
  `goods_id` bigint(0) NULL DEFAULT NULL COMMENT '秒杀商品id',
  `status` int(0) NULL DEFAULT NULL COMMENT '秒杀状态 0-无效 1-已付款 2-未付款',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE COMMENT '用户id索引',
  INDEX `index_sku_id`(`goods_id`) USING BTREE COMMENT 'sku索引',
  INDEX `index_act_id`(`activity_id`) USING BTREE COMMENT 'act_id索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seckill_record
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(0) NOT NULL COMMENT '用户表',
  `phone` int(0) NULL DEFAULT NULL COMMENT '手机号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
