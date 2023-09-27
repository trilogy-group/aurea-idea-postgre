/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50514
Source Host           : localhost:3306
Source Database       : test_db

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2013-06-03 17:00:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fs_party`
-- ----------------------------
DROP TABLE IF EXISTS `fs_party`;
CREATE TABLE `fs_party` (
  `tril_gid` varchar(34) NOT NULL DEFAULT '',
  `dirtybyte` int(10) DEFAULT NULL,
  `fs_comments` varchar(34) DEFAULT NULL,
  `fs_crdnumber` varchar(255) DEFAULT NULL,
  `fs_dtccid` varchar(4) DEFAULT NULL,
  `fs_dtccid_upper` varchar(34) DEFAULT NULL,
  `fs_healthsavingsaccount` int(17) DEFAULT NULL,
  `fs_k401` int(17) DEFAULT NULL,
  `fs_party` varchar(34) DEFAULT NULL,
  `fs_primarycontactu` varchar(34) DEFAULT NULL,
  `fs_renewalfeepayee` int(10) DEFAULT NULL,
  `fs_savedwitherrors` int(10) DEFAULT NULL,
  `fs_scuser` varchar(34) DEFAULT NULL,
  `fs_syncpdb` int(5) DEFAULT NULL,
  `fs_unid` varchar(255) DEFAULT NULL,
  `fs_unidupper` varchar(255) DEFAULT NULL,
  `lcid` int(10) DEFAULT NULL,
  `modificationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `partyrole` int(10) DEFAULT NULL,
  `partytype` int(10) DEFAULT NULL,
  `realmid` int(10) DEFAULT NULL,
  `seqid` int(10) DEFAULT NULL,
  `serverseqid` int(10) DEFAULT NULL,
  PRIMARY KEY (`tril_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fs_party
-- ----------------------------
INSERT INTO `fs_party` VALUES ('1', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, '2013-06-03 16:59:00', null, null, null, null, null);
INSERT INTO `fs_party` VALUES ('2', null, null, null, '2', '2', null, null, null, null, null, null, null, null, null, null, null, '2013-06-03 16:59:01', null, null, null, null, null);
INSERT INTO `fs_party` VALUES ('3', null, null, null, '3', '3', null, null, null, null, null, null, null, null, null, null, null, '2013-06-03 16:59:01', null, null, null, null, null);
INSERT INTO `fs_party` VALUES ('4', null, null, null, '4', '4', null, null, null, null, null, null, null, null, null, null, null, '2013-06-03 16:59:02', null, null, null, null, null);

-- ----------------------------
-- Table structure for `sc_data_object`
-- ----------------------------
DROP TABLE IF EXISTS `sc_data_object`;
CREATE TABLE `sc_data_object` (
  `tril_gid` varchar(34) NOT NULL DEFAULT '',
  `attributes` varchar(34) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fs_nameupper` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tril_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_data_object
-- ----------------------------

-- ----------------------------
-- Table structure for `sc_object`
-- ----------------------------
DROP TABLE IF EXISTS `sc_object`;
CREATE TABLE `sc_object` (
  `tril_gid` varchar(34) NOT NULL DEFAULT '',
  `lcid` int(10) DEFAULT NULL,
  `modificationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `realmid` int(10) DEFAULT NULL,
  `seqid` int(10) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  PRIMARY KEY (`tril_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_object
-- ----------------------------

-- ----------------------------
-- Table structure for `sc_person`
-- ----------------------------
DROP TABLE IF EXISTS `sc_person`;
CREATE TABLE `sc_person` (
  `tril_gid` varchar(34) NOT NULL DEFAULT '',
  `address` varchar(34) DEFAULT NULL,
  `countryofbirth` int(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `eyecolor` int(10) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `fs_altlastname` varchar(64) DEFAULT NULL,
  `fs_birthdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fs_currentdetails` varchar(34) DEFAULT NULL,
  `fs_firstnameupper` varchar(50) DEFAULT NULL,
  `fs_gender` varchar(8) DEFAULT NULL,
  `fs_legalname` varchar(34) DEFAULT NULL,
  `fs_maritalstatus` varchar(16) DEFAULT NULL,
  `fs_natlorigin` varchar(32) DEFAULT NULL,
  `fs_officenumber` varchar(32) DEFAULT NULL,
  `fs_preferredname` varchar(64) DEFAULT NULL,
  `fs_publicityname` varchar(64) DEFAULT NULL,
  `fs_salutation` varchar(64) DEFAULT NULL,
  `fs_spousefirstname` varchar(64) DEFAULT NULL,
  `fs_spouselastname` varchar(64) DEFAULT NULL,
  `fs_suffix` varchar(64) DEFAULT NULL,
  `fs_taxid` varchar(64) DEFAULT NULL,
  `fs_taxidupper` varchar(32) DEFAULT NULL,
  `haircolor` int(10) DEFAULT NULL,
  `heightfeet` int(10) DEFAULT NULL,
  `heightinches` int(10) DEFAULT NULL,
  `iadualregistration` int(10) DEFAULT NULL,
  `ismarkdeleted` int(10) DEFAULT NULL,
  `joindate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastname` varchar(50) DEFAULT NULL,
  `lastnameupper` varchar(50) DEFAULT NULL,
  `middlename` varchar(50) DEFAULT NULL,
  `npn` varchar(50) DEFAULT NULL,
  `organization` varchar(50) DEFAULT NULL,
  `organizationtype` varchar(50) DEFAULT NULL,
  `phone1` varchar(50) DEFAULT NULL,
  `phone2` varchar(50) DEFAULT NULL,
  `salesitems` int(10) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `weig` int(10) DEFAULT NULL,
  PRIMARY KEY (`tril_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_person
-- ----------------------------
INSERT INTO `sc_person` VALUES ('1', null, null, 'test@email.com', null, '1234', 'Test', 'Test1', '2013-06-03 16:55:20', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', null, null, null, null, null, '0000-00-00 00:00:00', 'Test', 'TEST', null, null, null, null, null, null, null, null, null);
INSERT INTO `sc_person` VALUES ('2', null, null, 'more@email.com', null, '2233', 'More', 'More1', '2013-06-03 16:56:43', null, null, null, null, null, null, null, null, null, null, null, null, null, '2', '2', null, null, null, null, null, '0000-00-00 00:00:00', 'Room', 'ROOM', null, null, null, null, null, null, null, null, null);
INSERT INTO `sc_person` VALUES ('3', null, null, null, null, null, 'John', 'John', '2013-06-03 16:56:43', null, null, null, null, null, null, null, null, null, null, null, null, null, '3', '3', null, null, null, null, null, '0000-00-00 00:00:00', 'John', 'JOHN', null, null, null, null, null, null, null, null, null);
INSERT INTO `sc_person` VALUES ('4', null, null, null, null, null, 'Samm', 'Sam', '2013-06-03 16:56:44', null, null, null, null, null, null, null, null, null, null, null, null, null, '4', '4', null, null, null, null, null, '0000-00-00 00:00:00', 'Tested', 'TESTED', null, null, null, null, null, null, null, null, null);
