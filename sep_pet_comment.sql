/*
Navicat MySQL Data Transfer

Source Server         : localhost_5060
Source Server Version : 50508
Source Host           : localhost:5060
Source Database       : sephiroth

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-01-16 17:07:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sep_pet_comment`
-- ----------------------------
DROP TABLE IF EXISTS `sep_pet_comment`;
CREATE TABLE `sep_pet_comment` (
  `pet_id` int(11) NOT NULL,
  `owerWords` varchar(128) DEFAULT NULL,
  `petComment` varchar(128) DEFAULT NULL,
  KEY `assocated_pet_id` (`pet_id`),
  CONSTRAINT `assocated_pet_id` FOREIGN KEY (`pet_id`) REFERENCES `sep_pet` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sep_pet_comment
-- ----------------------------
