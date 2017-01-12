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

 Date: 01/13/2017 01:30:27 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sep_race`
-- ----------------------------
DROP TABLE IF EXISTS `sep_race`;
CREATE TABLE `sep_race` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `race` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `race` (`race`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `sep_race`
-- ----------------------------
BEGIN;
INSERT INTO `sep_race` VALUES ('1', 'Human'), ('2', 'Nephilim'), ('3', 'Titaan');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
