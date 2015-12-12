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
DROP DATABASE IF EXISTS `clock`;
CREATE DATABASE IF NOT EXISTS `clock` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `clock`;


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

-- 正在导出表  clock.bonus 的数据：~0 rows (大约)
DELETE FROM `bonus`;
/*!40000 ALTER TABLE `bonus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bonus` ENABLE KEYS */;


-- 导出  表 clock.clockrecord 结构
DROP TABLE IF EXISTS `clockrecord`;
CREATE TABLE IF NOT EXISTS `clockrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- 正在导出表  clock.clockrecord 的数据：~17 rows (大约)
DELETE FROM `clockrecord`;
/*!40000 ALTER TABLE `clockrecord` DISABLE KEYS */;
INSERT INTO `clockrecord` (`id`, `userid`, `empid`, `timestamp`, `description`) VALUES
	(9, 4, 160208, '2012-10-19 16:32:10', ''),
	(10, 4, 160208, '2012-10-19 16:32:11', ''),
	(11, 4, 160208, '2012-12-24 20:45:54', ''),
	(12, 4, 160208, '2012-12-24 20:45:55', ''),
	(13, 4, 160208, '2013-03-08 15:14:53', ''),
	(14, 4, 160208, '2013-03-08 15:22:36', ''),
	(15, 4, 160208, '2013-06-01 18:54:33', ''),
	(16, 8, 40000, '2013-06-01 20:45:45', '上班了'),
	(17, 8, 40000, '2013-06-01 20:46:00', '现在下班'),
	(18, 8, 40000, '2013-06-01 20:46:36', '回来了'),
	(19, 4, 160208, '2013-06-01 22:06:20', ''),
	(20, 4, 160208, '2013-06-01 22:08:25', '下班了'),
	(21, 8, 40000, '2013-06-01 22:11:35', '回家'),
	(22, 4, 160208, '2013-09-19 17:55:50', ''),
	(23, 4, 160208, '2013-09-19 17:56:53', ''),
	(24, 4, 160208, '2014-07-06 14:06:13', ''),
	(25, 4, 160208, '2014-07-06 22:15:01', '');
/*!40000 ALTER TABLE `clockrecord` ENABLE KEYS */;


-- 导出  表 clock.employeetypes 结构
DROP TABLE IF EXISTS `employeetypes`;
CREATE TABLE IF NOT EXISTS `employeetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeetypename` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  clock.employeetypes 的数据：~0 rows (大约)
DELETE FROM `employeetypes`;
/*!40000 ALTER TABLE `employeetypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeetypes` ENABLE KEYS */;


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

-- 正在导出表  clock.fine 的数据：~0 rows (大约)
DELETE FROM `fine`;
/*!40000 ALTER TABLE `fine` DISABLE KEYS */;
/*!40000 ALTER TABLE `fine` ENABLE KEYS */;


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

-- 正在导出表  clock.leavelefts 的数据：~0 rows (大约)
DELETE FROM `leavelefts`;
/*!40000 ALTER TABLE `leavelefts` DISABLE KEYS */;
/*!40000 ALTER TABLE `leavelefts` ENABLE KEYS */;


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

-- 正在导出表  clock.leavelimits 的数据：~0 rows (大约)
DELETE FROM `leavelimits`;
/*!40000 ALTER TABLE `leavelimits` DISABLE KEYS */;
/*!40000 ALTER TABLE `leavelimits` ENABLE KEYS */;


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

-- 正在导出表  clock.leaves 的数据：~0 rows (大约)
DELETE FROM `leaves`;
/*!40000 ALTER TABLE `leaves` DISABLE KEYS */;
/*!40000 ALTER TABLE `leaves` ENABLE KEYS */;


-- 导出  表 clock.leavetypes 结构
DROP TABLE IF EXISTS `leavetypes`;
CREATE TABLE IF NOT EXISTS `leavetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leavetypename` varchar(50) NOT NULL,
  `unitfine` decimal(14,4) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  clock.leavetypes 的数据：~0 rows (大约)
DELETE FROM `leavetypes`;
/*!40000 ALTER TABLE `leavetypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `leavetypes` ENABLE KEYS */;


-- 导出  表 clock.privilege 结构
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isadmin` varchar(50) NOT NULL,
  `candelete` varchar(50) NOT NULL,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- 正在导出表  clock.privilege 的数据：~34 rows (大约)
DELETE FROM `privilege`;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` (`id`, `isadmin`, `candelete`, `url`) VALUES
	(1, 'Y', 'N', '../controller/AdminAddUserController'),
	(2, 'Y', 'N', '../controller/AdminChangeUserPasswordController'),
	(3, 'Y', 'N', '../controller/AdminDeleteUserController'),
	(4, 'Y', 'N', '../controller/ToggleActiveController'),
	(5, 'Y', 'N', '../controller/ToggleAdminController'),
	(6, 'N', 'N', '../controller/ChangePasswordController'),
	(7, 'N', 'N', '../controller/EditUserController'),
	(8, 'Y', 'N', '../testcontroller/TestAdminAddUserController'),
	(9, 'Y', 'N', '../testcontroller/TestAdminChangeUserPasswordController'),
	(10, 'Y', 'N', '../testcontroller/TestAdminDeleteUserController'),
	(11, 'Y', 'N', '../testcontroller/TestToggleActiveController'),
	(12, 'Y', 'N', '../testcontroller/TestToggleAdminController'),
	(13, 'Y', 'N', '../testcontroller/TestChangePasswordController'),
	(14, 'Y', 'N', '../testcontroller/TestEditUserController'),
	(15, 'Y', 'N', '../jsp/adminadduser.jsp'),
	(16, 'Y', 'N', '../jsp/adminedituser.jsp'),
	(17, 'Y', 'N', '../jsp/adminhomepage.jsp'),
	(18, 'N', 'N', '../jsp/userhomepage.jsp'),
	(19, 'N', 'N', '../jsp/userinfo.jsp'),
	(20, 'N', 'N', '../jsp/changepassword.jsp'),
	(21, 'N', 'N', '../jsp/edituser.jsp'),
	(22, 'N', 'N', '../jsp/userfunctionadministration.jsp'),
	(23, 'N', 'N', '../jsp/userhomepage.jsp'),
	(24, 'Y', 'N', '../test/usergpinterface.jsp'),
	(25, 'Y', 'N', '../test/privilegegpinterface.jsp'),
	(26, 'Y', 'N', '../test/changepassword.jsp'),
	(27, 'Y', 'N', '../testcontroller/TestRegisterController'),
	(28, 'Y', 'N', '../testcontroller/TestToggleActiveController'),
	(29, 'Y', 'N', '../testcontroller/TestToggleAdminController'),
	(30, 'Y', 'N', '../testcontroller/TestTogglePrivilegeAdminController'),
	(31, 'Y', 'N', '../testcontroller/TestToggleCanDeleteController'),
	(32, 'Y', 'N', '../testcontroller/TestAdminAddPrivilegeController'),
	(33, 'Y', 'N', '../testcontroller/TestAdminEditPrivilegeController'),
	(34, 'Y', 'N', '../testcontroller/TestAdminDeletePrivilegeController');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  clock.users 的数据：~2 rows (大约)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `empid`, `firstname`, `lastname`, `username`, `name_c`, `name_j`, `sex`, `password`, `pin`, `isadmin`, `isactive`, `address`, `address1`, `phone`, `mobile`, `login_failure`) VALUES
	(4, 160208, 'jerry', 'shen', 'jerry', 'sjf', 'sjf', 'Male', '3256fab5a9fb28f5871da8d52d6553d2961d8420', '45516502', 'Y', 'Y', 'shanghai', 'shanghai', '5000000', '18220000', 0),
	(8, 40000, 'mala', 'la', 'mala', '马拉', '马拉', 'male', 'a64fec95d3a3d6b80f00c7cf0344239c6cb79b0a', '32406368', 'Y', 'Y', '马拉2', '马拉21', '8888', '9999', 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
