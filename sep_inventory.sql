/*
Navicat MySQL Data Transfer

Source Server         : localhost_5060
Source Server Version : 50508
Source Host           : localhost:5060
Source Database       : sephiroth

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-01-16 17:08:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sep_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `sep_inventory`;
CREATE TABLE `sep_inventory` (
  `id` int(11) NOT NULL,
  `owerID` int(11) DEFAULT NULL,
  `owerName` varchar(128) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `character_inventory_asso` (`owerID`),
  KEY `character_name_of_inventory` (`owerName`),
  CONSTRAINT `character_name_of_inventory` FOREIGN KEY (`owerName`) REFERENCES `sep_character` (`playName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `character_inventory_asso` FOREIGN KEY (`owerID`) REFERENCES `sep_character` (`account_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sep_inventory
-- ----------------------------
