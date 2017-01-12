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

 Date: 01/13/2017 01:29:55 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sep_account`
-- ----------------------------
DROP TABLE IF EXISTS `sep_account`;
CREATE TABLE `sep_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `lastlogintime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '??????',
  `createtime` datetime NOT NULL COMMENT '????',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '????',
  `email` varchar(64) DEFAULT NULL,
  `loginip` varchar(16) DEFAULT NULL,
  `key` varchar(32) DEFAULT NULL COMMENT '??',
  `privilege` smallint(4) NOT NULL DEFAULT '0' COMMENT '??',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
