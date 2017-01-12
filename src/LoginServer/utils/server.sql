/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : sephiroth

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 01/13/2017 01:30:36 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `server`
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
  `id` int(10) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `maxPlayer` int(11) DEFAULT NULL,
  `line` int(11) DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET latin1 DEFAULT NULL,
  `port` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `server`
-- ----------------------------
BEGIN;
INSERT INTO `server` VALUES ('1', '麒麟', '2000', '1', '0.0.0.0', '2287', '1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
