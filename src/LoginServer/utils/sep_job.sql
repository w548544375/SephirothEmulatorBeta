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

 Date: 01/13/2017 01:30:19 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sep_job`
-- ----------------------------
DROP TABLE IF EXISTS `sep_job`;
CREATE TABLE `sep_job` (
  `id` tinyint(4) NOT NULL,
  `job` varchar(32) NOT NULL,
  `mask` tinyint(1) NOT NULL,
  `race_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `race_id` (`race_name`),
  KEY `race_id_2` (`race_name`),
  KEY `race_id_3` (`race_name`),
  CONSTRAINT `racenamereleated` FOREIGN KEY (`race_name`) REFERENCES `sep_race` (`race`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `sep_job`
-- ----------------------------
BEGIN;
INSERT INTO `sep_job` VALUES ('1', 'OneHand', '1', 'Human'), ('2', 'Bare', '2', 'Human'), ('3', 'Red', '3', 'Human'), ('4', 'Blue', '4', 'Nephilim'), ('5', 'Bow', '5', 'Nephilim');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
