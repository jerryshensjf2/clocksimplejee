-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.32 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 clock 的数据库结构
DROP DATABASE IF EXISTS `clock_test`;
CREATE DATABASE IF NOT EXISTS `clock_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `clock_test`;


-- 导出  表 clock.bonus 结构
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE IF NOT EXISTS `bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `reason` varchar(400) NOT NULL,
  `bonus_balance` decimal(14,4) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.clockrecord 结构
DROP TABLE IF EXISTS `clockrecord`;
CREATE TABLE IF NOT EXISTS `clockrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.employeetypes 结构
DROP TABLE IF EXISTS `employeetypes`;
CREATE TABLE IF NOT EXISTS `employeetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeetypename` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.fine 结构
DROP TABLE IF EXISTS `fine`;
CREATE TABLE IF NOT EXISTS `fine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `reason` varchar(400) NOT NULL,
  `fine_balance` decimal(14,4) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.leavelefts 结构
DROP TABLE IF EXISTS `leavelefts`;
CREATE TABLE IF NOT EXISTS `leavelefts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `annualLeaveLeft` int(11) NOT NULL,
  `sickLeaveLeft` int(11) NOT NULL,
  `privateLeaveLeft` int(11) NOT NULL,
  `otherLeaveLeft` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.leavelimits 结构
DROP TABLE IF EXISTS `leavelimits`;
CREATE TABLE IF NOT EXISTS `leavelimits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeetypeid` int(11) NOT NULL,
  `annualleavelimit` int(11) NOT NULL,
  `sickleavelimit` int(11) NOT NULL,
  `privateleavelimit` int(11) NOT NULL,
  `otherleavelimit` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.leaves 结构
DROP TABLE IF EXISTS `leaves`;
CREATE TABLE IF NOT EXISTS `leaves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `day` datetime NOT NULL,
  `leaveTypeId` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.leavetypes 结构
DROP TABLE IF EXISTS `leavetypes`;
CREATE TABLE IF NOT EXISTS `leavetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leavetypename` varchar(50) NOT NULL,
  `unitfine` decimal(14,4) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.privilege 结构
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isadmin` varchar(50) NOT NULL,
  `candelete` varchar(50) NOT NULL,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 clock.users 结构
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `name_c` varchar(60) DEFAULT NULL,
  `name_j` varchar(60) DEFAULT NULL,
  `sex` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `pin` varchar(512) NOT NULL,
  `isadmin` char(1) NOT NULL DEFAULT 'N',
  `isactive` char(1) NOT NULL DEFAULT 'Y',
  `address` varchar(2000) DEFAULT NULL,
  `address1` varchar(2000) DEFAULT NULL,
  `phone` varchar(400) DEFAULT NULL,
  `mobile` varchar(400) DEFAULT NULL,
  `login_failure` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
