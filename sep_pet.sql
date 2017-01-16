/*
Navicat MySQL Data Transfer

Source Server         : localhost_5060
Source Server Version : 50508
Source Host           : localhost:5060
Source Database       : sephiroth

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-01-16 17:07:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sep_pet`
-- ----------------------------
DROP TABLE IF EXISTS `sep_pet`;
CREATE TABLE `sep_pet` (
  `id` int(11) NOT NULL DEFAULT '0',
  `owerId` int(11) NOT NULL,
  `petName` varchar(32) NOT NULL,
  `petMeshName` varchar(32) NOT NULL,
  `petLv` int(11) NOT NULL DEFAULT '1',
  `PetPosition` int(11) DEFAULT NULL,
  `bNeedChange` tinyint(4) DEFAULT NULL,
  `petColor` int(11) DEFAULT NULL,
  `faceStats` int(11) DEFAULT '1' COMMENT '0 or 1',
  `LearnedTextNums` int(11) DEFAULT NULL,
  `LearnTextNumsMax` int(11) DEFAULT NULL,
  `foodGuage` int(11) DEFAULT NULL,
  `comment` varchar(64) DEFAULT NULL,
  `petPoint` int(11) DEFAULT NULL,
  `PointHP` int(11) DEFAULT NULL,
  `PointMP` int(11) DEFAULT NULL,
  `PointAttack` int(11) DEFAULT NULL,
  `PointInven` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pet_id` (`id`) USING HASH,
  KEY `character_id_of_pet` (`owerId`) USING BTREE,
  CONSTRAINT `character_id_of_pet` FOREIGN KEY (`owerId`) REFERENCES `sep_character` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sep_pet
-- ----------------------------
