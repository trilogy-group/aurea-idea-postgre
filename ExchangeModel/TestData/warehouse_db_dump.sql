/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50514
Source Host           : localhost:3306
Source Database       : warehouse_db

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2013-05-23 19:03:59
*/

DROP DATABASE IF EXISTS WAREHOUSE_DB;
CREATE DATABASE WAREHOUSE_DB;

use WAREHOUSE_DB ;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_identifier` int(10) NOT NULL,
  `street1` varchar(100) DEFAULT NULL,
  `street2` varchar(100) DEFAULT NULL,
  `street3` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`address_identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '209 Oxbow Road', null, null, 'Wayland', 'MA', 'US', '01778');
INSERT INTO `address` VALUES ('2', '5 test Road', null, null, 'Needham', 'MA', 'US', '66778');
INSERT INTO `address` VALUES ('3', '5 Somewhere Drive', null, null, 'Waltham', 'MA', 'US', '01997');
INSERT INTO `address` VALUES ('4', '343 Dunbury Circle', null, null, 'Dunbury', 'CT', 'US', '01554');
INSERT INTO `address` VALUES ('5', '5 Dan Circle', null, null, 'Boston', 'MA', 'US', '08897');
INSERT INTO `address` VALUES ('6', '564 3rd Ave.', null, null, 'New York', 'NY', 'US', '10011');
INSERT INTO `address` VALUES ('7', '209 Oxbow Road', null, null, 'Wayland', 'MA', 'US', '01778');
INSERT INTO `address` VALUES ('8', '5 test Road', null, null, 'Needham', 'MA', 'US', '66778');
INSERT INTO `address` VALUES ('9', '5 Somewhere Drive', null, null, 'Waltham', 'MA', 'US', '01997');
INSERT INTO `address` VALUES ('10', '5 Dan Circle', null, null, 'Boston', 'MA', 'US', '08897');
INSERT INTO `address` VALUES ('11', '343 Dunbury Circle', null, null, 'Dunbury', 'CT', 'US', '01554');
INSERT INTO `address` VALUES ('12', '564 3rd Ave.', null, null, 'New York', 'NY', 'US', '10011');
INSERT INTO `address` VALUES ('13', '209 Oxbow Road', null, null, 'Wayland', 'MA', 'US', '01778');
INSERT INTO `address` VALUES ('14', '5 test Road', null, null, 'Needham', 'MA', 'US', '66778');
INSERT INTO `address` VALUES ('15', '5 Somewhere Drive', null, null, 'Waltham', 'MA', 'US', '01997');
INSERT INTO `address` VALUES ('16', '343 Dunbury Circle', null, null, 'Dunbury', 'CT', 'US', '01554');
INSERT INTO `address` VALUES ('17', '5 Dan Circle', null, null, 'Boston', 'MA', 'US', '08897');
INSERT INTO `address` VALUES ('18', '564 3rd Ave.', null, null, 'New York', 'NY', 'US', '10011');
INSERT INTO `address` VALUES ('19', '212 Oxbow Road', null, null, 'Wayland', 'MA', 'US', '01778');
INSERT INTO `address` VALUES ('20', '5 test Road', null, null, 'Needham', 'MA', 'US', '66778');
INSERT INTO `address` VALUES ('21', '5 Somewhere Drive', null, null, 'Waltham', 'MA', 'US', '01997');
INSERT INTO `address` VALUES ('22', '13 Dan Circle', null, null, 'Boston', 'MA', 'US', '08897');
INSERT INTO `address` VALUES ('23', '343 Abc Circle', null, null, 'Dunbury', 'CT', 'US', '01554');
INSERT INTO `address` VALUES ('24', '564 3rd Ave.', null, null, 'New York', 'NY', 'US', '10011');

-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `contact_identifier` int(10) NOT NULL,
  `person_identifier` int(10) DEFAULT NULL,
  `address_identifier` int(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `cell` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contact_identifier`),
  KEY `address_identifier` (`address_identifier`),
  KEY `person_identifier` (`person_identifier`),
  CONSTRAINT `0_14097` FOREIGN KEY (`address_identifier`) REFERENCES `address` (`address_identifier`),
  CONSTRAINT `0_14099` FOREIGN KEY (`person_identifier`) REFERENCES `person` (`person_identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('1', '1', '1', 'abc.eee@testmail.com', '508-333-3333', '508-333-3335', '508-333-3334');
INSERT INTO `contacts` VALUES ('2', '2', '2', 'abc.test@moremail.com', '781-333-3333', '781-333-3335', '781-333-3334');
INSERT INTO `contacts` VALUES ('3', '3', '3', 'abc.test@gmail1.com', '781-555-3333', '781-555-3335', '781-555-3334');
INSERT INTO `contacts` VALUES ('4', '4', '4', 'abc.koh@moremail.com', '304-333-3333', '304-333-3335', '304-333-3334');
INSERT INTO `contacts` VALUES ('5', '5', '5', 'abc.test@abemail.com', '781-666-3333', '781-666-3335', '781-666-3334');
INSERT INTO `contacts` VALUES ('6', '6', '6', 'abc.cba@something.com', '201-555-3333', '201-555-3335', '201-555-3334');
INSERT INTO `contacts` VALUES ('7', '7', '7', 'abc.eee@testmail.com', '508-333-3333', '508-333-3335', '508-333-3334');
INSERT INTO `contacts` VALUES ('8', '8', '8', 'abc.test@moremail.com', '781-333-3333', '781-333-3335', '781-333-3334');
INSERT INTO `contacts` VALUES ('9', '9', '9', 'abc.test@gmail1.com', '781-555-3333', '781-555-3335', '781-555-3334');
INSERT INTO `contacts` VALUES ('10', '10', '10', 'abc.test@abemail.com', '781-666-3333', '781-666-3335', '781-666-3334');
INSERT INTO `contacts` VALUES ('11', '11', '11', 'abc.koh@moremail.com', '304-333-3333', '304-333-3335', '304-333-3334');
INSERT INTO `contacts` VALUES ('12', '12', '12', 'abc.cba@something.com', '201-555-3333', '201-555-3335', '201-555-3334');
INSERT INTO `contacts` VALUES ('13', '13', '13', 'abc.eee@testmail.com', '508-333-3333', '508-333-3335', '508-333-3334');
INSERT INTO `contacts` VALUES ('14', '14', '14', 'abc.test@moremail.com', '781-333-3333', '781-333-3335', '781-333-3334');
INSERT INTO `contacts` VALUES ('15', '15', '15', 'abc.test@gmail1.com', '781-555-3333', '781-555-3335', '781-555-3334');
INSERT INTO `contacts` VALUES ('16', '16', '16', 'abc.koh@moremail.com', '304-333-3333', '304-333-3335', '304-333-3334');
INSERT INTO `contacts` VALUES ('17', '17', '17', 'abc.test@abemail.com', '781-666-3333', '781-666-3335', '781-666-3334');
INSERT INTO `contacts` VALUES ('18', '18', '18', 'abc.cba@something.com', '201-555-3333', '201-555-3335', '201-555-3334');
INSERT INTO `contacts` VALUES ('19', '19', '19', 'abc.eee@testmail.com', '508-333-3333', '508-333-3335', '508-333-3334');
INSERT INTO `contacts` VALUES ('20', '20', '20', 'abc.test@moremail.com', '781-333-3333', '781-333-3335', '781-333-3334');
INSERT INTO `contacts` VALUES ('21', '21', '21', 'abc.test@gmail1.com', '781-555-3333', '781-555-3335', '781-555-3334');
INSERT INTO `contacts` VALUES ('22', '22', '22', 'abc.test@abemail.com', '781-666-3333', '781-666-3335', '781-666-3334');
INSERT INTO `contacts` VALUES ('23', '23', '23', 'abc.koh@moremail.com', '304-333-3333', '304-333-3335', '304-333-3334');
INSERT INTO `contacts` VALUES ('24', '24', '24', 'abc.cba@something.com', '201-555-3333', '201-555-3335', '201-555-3334');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `person_identifier` int(10) NOT NULL,
  `ssn` varchar(100) DEFAULT NULL,
  `prefix` varchar(100) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `mname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `suffix` varchar(100) DEFAULT NULL,
  `preferred_name` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `marital_status` varchar(100) DEFAULT NULL,
  `person_role` varchar(100) DEFAULT NULL,
  `status_identifier` int(10) DEFAULT NULL,
  `spouse_fname` varchar(100) DEFAULT NULL,
  `spouse_lname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`person_identifier`),
  KEY `status_identifier` (`status_identifier`),
  CONSTRAINT `0_14139` FOREIGN KEY (`status_identifier`) REFERENCES `status` (`status_identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '987654321', 'Mrs', 'Maya', 'Aliza', 'Dahan', 'Dr.', 'Maya Aliza Dahan', 'Female', '2013-09-14', 'ISRAEL', 'Married', 'EMPLOYEE', '1', 'David', 'Dahan');
INSERT INTO `person` VALUES ('2', '999988776', 'Mrs', 'Esther', 'Caroline', 'Jesurum', 'Dr.', 'c. Esther Jesurum', 'Female', '2000-09-03', 'US', 'Married', 'EMPLOYEE', '2', 'Jeff', 'Brown');
INSERT INTO `person` VALUES ('3', '669988723', 'Mrs', 'Rommie', 'J.', 'Jason', 'Rk.', 'Rommie B. Jason', 'Female', '1976-09-10', 'US', 'Married', 'EMPLOYEE', '3', 'Jeff', 'John');
INSERT INTO `person` VALUES ('4', '999988709', 'Mr', 'Frank', 'N/A', 'Koh', 'N/A', 'Frank Koh', 'Male', '1965-09-03', 'US', 'Married', 'EMPLOYEE', '4', 'Ronit', 'Koh');
INSERT INTO `person` VALUES ('5', '994568000', 'Mrs', 'Karen', 'D.', 'Smith', '.', 'Karen D. Smith', 'Female', '1995-12-03', 'US', 'Single', 'EMPLOYEE', '5', null, null);
INSERT INTO `person` VALUES ('6', '665578967', 'Mr', 'Zack', 'H.', 'Smith', 'N/A', 'Zack H. Smith', 'Male', '1978-09-12', 'Italy', 'Single', 'EMPLOYEE', '6', null, null);
INSERT INTO `person` VALUES ('7', '889977909', 'Mrs', 'Maya', 'Aliza', 'Dahan', 'Dr.', 'Maya Aliza Dahan', 'Female', '2013-09-14', 'ISRAEL', 'Married', 'EMPLOYEE', '7', 'David', 'Dahan');
INSERT INTO `person` VALUES ('8', '878654309', 'Mrs', 'Esther', 'Caroline', 'Jesurum', 'Dr.', 'c. Esther Jesurum', 'Female', '2000-09-03', 'US', 'Married', 'EMPLOYEE', '8', 'Jeff', 'Brown');
INSERT INTO `person` VALUES ('9', '656543223', 'Mrs', 'Rommie', 'J.', 'Jason', 'Rk.', 'Rommie B. Jason', 'Female', '1976-09-10', 'US', 'Married', 'EMPLOYEE', '9', 'Jeff', 'John');
INSERT INTO `person` VALUES ('10', '987987987', 'Mrs', 'Karen', 'D.', 'Smith', '.', 'Karen D. Smith', 'Female', '1995-12-03', 'US', 'Single', 'EMPLOYEE', '10', null, null);
INSERT INTO `person` VALUES ('11', '654554456', 'Mr', 'Frank', 'N/A', 'Koh', 'N/A', 'Frank Koh', 'Male', '1965-09-03', 'US', 'Married', 'EMPLOYEE', '11', 'Ronit', 'Koh');
INSERT INTO `person` VALUES ('12', '675654398', 'Mr', 'Zack', 'H.', 'Smith', 'N/A', 'Zack H. Smith', 'Male', '1978-09-12', 'Italy', 'Single', 'EMPLOYEE', '12', null, null);
INSERT INTO `person` VALUES ('13', '987654309', 'Mrs', 'Maya', 'Aliza', 'Dahan', 'Dr.', 'Maya Aliza Dahan', 'Female', '2013-09-14', 'ISRAEL', 'Married', 'EMPLOYEE', '13', 'David', 'Dahan');
INSERT INTO `person` VALUES ('14', '999988708', 'Mrs', 'Esther', 'Caroline', 'Jesurum', 'Dr.', 'c. Esther Jesurum', 'Female', '2000-09-03', 'US', 'Married', 'EMPLOYEE', '14', 'Jeff', 'Brown');
INSERT INTO `person` VALUES ('15', '669988776', 'Mrs', 'Rommie', 'J.', 'Jason', 'Rk.', 'Rommie B. Jason', 'Female', '1976-09-10', 'US', 'Married', 'EMPLOYEE', '15', 'Jeff', 'John');
INSERT INTO `person` VALUES ('16', '999988787', 'Mr', 'Frank', 'N/A', 'Koh', 'N/A', 'Frank Koh', 'Male', '1965-09-03', 'US', 'Married', 'EMPLOYEE', '16', 'Ronit', 'Koh');
INSERT INTO `person` VALUES ('17', '994568009', 'Mrs', 'Karen', 'D.', 'Smith', '.', 'Karen D. Smith', 'Female', '1995-12-03', 'US', 'Single', 'EMPLOYEE', '17', null, null);
INSERT INTO `person` VALUES ('18', '665578943', 'Mr', 'Zack', 'H.', 'Smith', 'N/A', 'Zack H. Smith', 'Male', '1978-09-12', 'Italy', 'Single', 'EMPLOYEE', '18', null, null);
INSERT INTO `person` VALUES ('19', '889977996', 'Mrs', 'Maya', 'Aliza', 'Dahan', 'Dr.', 'Maya Aliza Dahan', 'Female', '2013-09-14', 'ISRAEL', 'Married', 'EMPLOYEE', '19', 'David', 'Dahan');
INSERT INTO `person` VALUES ('20', '878654328', 'Mrs', 'Esther', 'Caroline', 'Jesurum', 'Dr.', 'c. Esther Jesurum', 'Female', '2000-09-03', 'US', 'Married', 'EMPLOYEE', '20', 'Jeff', 'Brown');
INSERT INTO `person` VALUES ('21', '656543218', 'Mrs', 'Rommie', 'J.', 'Jason', 'Rk.', 'Rommie B. Jason', 'Female', '1976-09-10', 'US', 'Married', 'EMPLOYEE', '21', 'Jeff', 'John');
INSERT INTO `person` VALUES ('22', '987987909', 'Mrs', 'Karen', 'D.', 'Smith', '.', 'Karen D. Smith', 'Female', '1995-12-03', 'US', 'Single', 'EMPLOYEE', '22', null, null);
INSERT INTO `person` VALUES ('23', '654654456', 'Mr', 'Frank', 'N/A', 'Koh', 'N/A', 'Frank Koh', 'Male', '1965-09-03', 'US', 'Married', 'EMPLOYEE', '23', 'Ronit', 'Koh');
INSERT INTO `person` VALUES ('24', '675654343', 'Mr', 'Zack', 'H.', 'Smith', 'N/A', 'Zack H. Smith', 'Male', '1978-09-12', 'Italy', 'Single', 'EMPLOYEE', '24', null, null);

-- ----------------------------
-- Table structure for `status`
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `status_identifier` int(10) NOT NULL,
  `status_code` varchar(100) DEFAULT NULL,
  `status_reason` varchar(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`status_identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('2', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('3', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('4', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('5', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('6', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('7', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('8', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('9', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('10', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('11', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('12', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('13', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('14', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('15', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('16', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('17', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('18', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('19', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('20', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('21', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('22', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('23', 'Active', null, '2013-05-23', '2300-01-01');
INSERT INTO `status` VALUES ('24', 'Active', null, '2013-05-23', '2300-01-01');
