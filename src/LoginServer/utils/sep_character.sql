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

 Date: 01/13/2017 01:30:04 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sep_character`
-- ----------------------------
DROP TABLE IF EXISTS `sep_character`;
CREATE TABLE `sep_character` (
  `id` int(11) NOT NULL,
  `playName` varchar(32) NOT NULL,
  `raceName` varchar(32) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `raceType` tinyint(4) NOT NULL,
  `profType` tinyint(4) NOT NULL,
  `jobName` varchar(32) NOT NULL,
  `castleName` varchar(32) DEFAULT NULL,
  `clanName` varchar(32) DEFAULT NULL,
  `clanTitle` varchar(32) DEFAULT NULL,
  `hairName` varchar(16) DEFAULT NULL,
  `faceName` varchar(32) DEFAULT NULL,
  `bodyName` varchar(32) DEFAULT NULL,
  `level` int(16) DEFAULT NULL,
  `alignment` int(16) DEFAULT NULL,
  `money` int(32) DEFAULT NULL,
  `strength` int(16) DEFAULT NULL,
  `dex` int(16) DEFAULT NULL,
  `vigor` int(16) DEFAULT NULL,
  `white` int(11) DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `blue` int(11) DEFAULT NULL,
  `yellow` int(11) DEFAULT NULL,
  `black` int(11) DEFAULT NULL,
  `health` int(11) DEFAULT NULL,
  `maxHealth` int(11) DEFAULT NULL,
  `mana` int(11) DEFAULT NULL,
  `maxMana` int(11) DEFAULT NULL,
  `stamina` float(16,0) DEFAULT NULL,
  `maxStamina` float(16,0) DEFAULT NULL,
  `armState` tinyint(4) DEFAULT NULL,
  `runState` tinyint(4) DEFAULT NULL,
  `pkState` tinyint(4) DEFAULT NULL,
  `lifeState` tinyint(4) DEFAULT '0' COMMENT '服务器状态，Die,Walk,Down',
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
