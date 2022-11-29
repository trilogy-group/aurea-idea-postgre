DROP DATABASE IF EXISTS WAREHOUSE_DB;
CREATE DATABASE WAREHOUSE_DB;

use WAREHOUSE_DB ;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `person` (
  `person_identifier` int(10) NOT NULL,
  `ssn` varchar(100),
  `prefix` varchar(100),
  `fname` varchar(100),
  `mname` varchar(100),
  `lname` varchar(100),
  `suffix` varchar(100),
  `preferred_name` varchar(100),
  `gender` varchar(100),
  `birth_date` date,
  `nationality` varchar(100),
  `marital_status` varchar(100),
  `person_role` varchar(100),
  `status_identifier` int(10),
  `spouse_fname` varchar(100),
  `spouse_lname` varchar(100),
  PRIMARY KEY  (`person_identifier`),
  KEY `status_identifier` (`status_identifier`),
  CONSTRAINT `0_14139` FOREIGN KEY (`status_identifier`) REFERENCES `status` (`status_identifier`)
) ENGINE=InnoDB;

CREATE TABLE `address` (
  `address_identifier` int(10) NOT NULL,
  `street1` varchar(100),
  `street2` varchar(100),
  `street3` varchar(100),
  `city` varchar(100),
  `state` varchar(100),
  `country` varchar(100),
  `zip` varchar(100),
  PRIMARY KEY  (`address_identifier`)
) ENGINE=InnoDB;

CREATE TABLE `contacts` (
  `contact_identifier` int(10) NOT NULL,
  `person_identifier` int(10),
  `address_identifier` int(10),
  `email` varchar(100),
  `fax` varchar(100),
  `phone` varchar(100),
  `cell` varchar(100),
  PRIMARY KEY  (`contact_identifier`),
  KEY `address_identifier` (`address_identifier`),
  KEY `person_identifier` (`person_identifier`),
  CONSTRAINT `0_14097` FOREIGN KEY (`address_identifier`) REFERENCES `address` (`address_identifier`),
  CONSTRAINT `0_14099` FOREIGN KEY (`person_identifier`) REFERENCES `person` (`person_identifier`)
) ENGINE=InnoDB;

CREATE TABLE `status` (
  `status_identifier` int(10) NOT NULL,
  `status_code` varchar(100),
  `status_reason` varchar(100),
  `start_date` date,
  `end_date` date,
  PRIMARY KEY  (`status_identifier`)
) ENGINE=InnoDB;
