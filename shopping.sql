/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : shopping

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2023-07-25 22:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `p_name` char(255) COLLATE utf8_unicode_ci NOT NULL,
  `p_price` float(10,2) NOT NULL,
  `p_stock` int(11) NOT NULL,
  `p_category` char(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`p_id`),
  UNIQUE KEY `p_id` (`p_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', '1001', 'Java语言设计', '34.00', '197', '书本');
INSERT INTO `products` VALUES ('2', '1002', 'SpringBoot3.0', '53.50', '499', '书本');
INSERT INTO `products` VALUES ('3', '1003', 'C语言从入门到精通', '69.80', '98', '书本');
INSERT INTO `products` VALUES ('8', '1004', '数据结构', '63.10', '100', '书本');
INSERT INTO `products` VALUES ('12', '1006', '书包', '132.00', '54', '实用');
INSERT INTO `products` VALUES ('13', '1007', '电脑', '6589.00', '30', '电子产品');
INSERT INTO `products` VALUES ('14', '1008', '苹果', '3.00', '693', '水果');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '普通员工');

-- ----------------------------
-- Table structure for temporary
-- ----------------------------
DROP TABLE IF EXISTS `temporary`;
CREATE TABLE `temporary` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `p_price` float(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of temporary
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` char(255) CHARACTER SET utf8 NOT NULL,
  `password` char(255) CHARACTER SET utf8 NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '112', '123', '1');
INSERT INTO `user` VALUES ('2', 'admin', '123', '1');
INSERT INTO `user` VALUES ('3', 'tao', '1130', '2');
INSERT INTO `user` VALUES ('4', 'user', '1234', '2');
