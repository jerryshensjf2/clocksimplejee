package test.javaforever.clocksimplejee4.servicetest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import test.javaforever.clocksimplejee4.test.DataSafeTestCase;
import junit.framework.Assert;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.ClockRecordService;
import com.javaforever.clocksimplejee4.serviceimpl.ClockRecordServiceImpl;

public class ClockRecordServiceTest extends DataSafeTestCase{
	
	private ClockRecordService clockRecordService = new ClockRecordServiceImpl();
	
	public ClockRecordService getClockRecordService() {
		return clockRecordService;
	}

	public void setClockRecordService(ClockRecordService clockRecordService) {
		this.clockRecordService = clockRecordService;
	}
	
	public void testGetLastMonthSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,2,0,0,0,0);

		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-07-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-07-01 16:00:00','My description.');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<BigDecimal> worktimeList2 = getClockRecordService().getLastMonthSummary(stamp, user);
		Assert.assertEquals(31,worktimeList2.size());
		Assert.assertEquals(7.00D,worktimeList2.get(0).doubleValue(),0.01D);
		
		for (BigDecimal workTime : worktimeList2){
			System.out.println("=============JerryDebug:"+ workTime);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
	}
	
	public void testGetThisMonthSummary() throws Exception{
		// prepare data
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,5,0,0,0,0);

		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<BigDecimal> worktimeList2 = getClockRecordService().getThisMonthSummary(stamp, user);
		Assert.assertEquals(5,worktimeList2.size());
		Assert.assertEquals(7.00D,worktimeList2.get(0).doubleValue(),0.01D);
		
		for (BigDecimal workTime : worktimeList2){
			System.out.println("=============JerryDebug:"+ workTime);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();		
	}
	
	public void testGetWholeLastYearSummary() throws Exception{
		// prepare data
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,5,0,0,0,0);

		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2010-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2010-08-01 16:00:00','My description.');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<BigDecimal> worktimeList2 = getClockRecordService().getWholeLastYearSummary(stamp, user);
		Assert.assertEquals(365,worktimeList2.size());
		int augCount = 31+28+31+30+31+30+31;
		Assert.assertEquals(7.00D,worktimeList2.get(augCount).doubleValue(),0.01D);
		
		for (BigDecimal workTime : worktimeList2){
			System.out.println("=============JerryDebug:"+ workTime);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();		
	}
	
	public void testGetWholeThisYearSummary() throws Exception{
		// prepare data
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,5,0,0,0,0);

		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<BigDecimal> worktimeList2 = getClockRecordService().getWholeThisYearSummary(stamp, user);

		int augCount = 31+28+31+30+31+30+31;
		Assert.assertEquals(augCount+5,worktimeList2.size());
		Assert.assertEquals(7.00D,worktimeList2.get(augCount).doubleValue(),0.01D);
		
		for (BigDecimal workTime : worktimeList2){
			System.out.println("=============JerryDebug:"+ workTime);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();		
	}
	
	public void testGetWholeLastYearWorkStatusSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,5,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2010-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2010-08-01 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		int augCount = 31+28+31+30+31+30+31;
		
		List<AttendanceStatus> list2 =  getClockRecordService().getWholeLastYearWorkStatusSummary(stamp, user);
		Assert.assertEquals(365,list2.size());
		Assert.assertEquals(7.00D,list2.get(augCount).getDuration().doubleValue(),0.01D);
		
		for (AttendanceStatus status : list2){
			System.out.println("=============JerryDebug:"+ status.getUsername());
			System.out.println("=============JerryDebug:"+ status.getDuration());
			System.out.println("=============JerryDebug:"+ status.getAttendanceStatus());
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		
	}
	

	public void testGetWholeThisYearWorkStatusSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,5,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		int augCount = 31+28+31+30+31+30+31;
		
		List<AttendanceStatus> list2 =  getClockRecordService().getWholeThisYearWorkStatusSummary(stamp, user);
		Assert.assertEquals(augCount + 5,list2.size());
		Assert.assertEquals(7.00D,list2.get(augCount).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("normal", list2.get(augCount).getAttendanceStatus());
		
		for (AttendanceStatus status : list2){
			System.out.println("=============JerryDebug:"+ status.getUsername());
			System.out.println("=============JerryDebug:"+ status.getDuration());
			System.out.println("=============JerryDebug:"+ status.getAttendanceStatus());
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		
	}
	
	public void testGetLastWeekSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,12,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-31 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-31 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);		
	
		List<BigDecimal> list2 =  getClockRecordService().getLastWeekSummary(stamp, user);
		Assert.assertEquals(7,list2.size());
		Assert.assertEquals(7.00D,list2.get(0).doubleValue(),0.01D);
		Assert.assertEquals(7.00D,list2.get(6).doubleValue(),0.01D);
		
		for (BigDecimal workDuration : list2){
			System.out.println("=============JerryDebug:"+ workDuration);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
	}
	
	public void testGetThisWeekSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-31 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-31 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);		
	
		List<BigDecimal> list2 =  getClockRecordService().getThisWeekSummary(stamp, user);
		
		for (BigDecimal workDuration : list2){
			System.out.println("=============JerryDebug:"+ workDuration);
		}
		
		Assert.assertEquals(7,list2.size());
		Assert.assertEquals(7.00D,list2.get(0).doubleValue(),0.01D);
		Assert.assertEquals(7.00D,list2.get(6).doubleValue(),0.01D);
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
	}
	

	public void testGetLastWeekWorkStatusSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,12,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-31 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-31 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);		
	
		List<AttendanceStatus> list2 =  getClockRecordService().getLastWeekWorkStatusSummary(stamp, user);
		Assert.assertEquals(7,list2.size());
		Assert.assertEquals(7.00D,list2.get(0).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("normal",list2.get(0).getAttendanceStatus());
		Assert.assertEquals(0.00D,list2.get(3).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("absent",list2.get(3).getAttendanceStatus());
		Assert.assertEquals(7.00D,list2.get(6).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("normal",list2.get(6).getAttendanceStatus());
		
		for (AttendanceStatus workStatus : list2){
			System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
			System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
			System.out.println("=============JerryDebug:"+ workStatus.getDescription());
			System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
			System.out.println("=============JerryDebug:"+ workStatus.getFullName());
			System.out.println("=============JerryDebug:"+ workStatus.getUserId());
			System.out.println("=============JerryDebug:"+ workStatus.getUsername());
			System.out.println("=============JerryDebug:"+ workStatus.getUserId());
			System.out.println("=============JerryDebug:"+ workStatus.getDate());
			System.out.println("=============JerryDebug:"+ workStatus.getDuration());
		}
	}
	
	public void testGetThisWeekWorkStatusSummary() throws Exception{
		Connection con = DBConf.initDB();
		Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-31 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-31 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);		
	
		List<AttendanceStatus> list2 =  getClockRecordService().getThisWeekWorkStatusSummary(stamp, user);
		Assert.assertEquals(7,list2.size());
		Assert.assertEquals(7.00D,list2.get(0).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("normal",list2.get(0).getAttendanceStatus());
		Assert.assertEquals(0.00D,list2.get(3).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("absent",list2.get(3).getAttendanceStatus());
		Assert.assertEquals(7.00D,list2.get(6).getDuration().doubleValue(),0.01D);
		Assert.assertEquals("normal",list2.get(6).getAttendanceStatus());
		
		for (AttendanceStatus workStatus : list2){
			System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
			System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
			System.out.println("=============JerryDebug:"+ workStatus.getDescription());
			System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
			System.out.println("=============JerryDebug:"+ workStatus.getFullName());
			System.out.println("=============JerryDebug:"+ workStatus.getUserId());
			System.out.println("=============JerryDebug:"+ workStatus.getUsername());
			System.out.println("=============JerryDebug:"+ workStatus.getUserId());
			System.out.println("=============JerryDebug:"+ workStatus.getDate());
			System.out.println("=============JerryDebug:"+ workStatus.getDuration());
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
	}
	
		public void testGetThisMonthWorkStatusSummary() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			List<AttendanceStatus> workList2 = getClockRecordService().getThisMonthWorkStatusSummary(stamp, user);
			Assert.assertEquals(6,workList2.size());
			Assert.assertEquals(7.00D,workList2.get(0).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(0).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(5).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(5).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(3).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(3).getAttendanceStatus());
			
			for (AttendanceStatus workStatus : workList2){
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
				System.out.println("=============JerryDebug:"+ workStatus.getDescription());
				System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
				System.out.println("=============JerryDebug:"+ workStatus.getFullName());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getUsername());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getDate());
				System.out.println("=============JerryDebug:"+ workStatus.getDuration());
			}
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testGetLastMonthWorkStatusSummary() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-07-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-07-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			List<AttendanceStatus> workList2 = getClockRecordService().getLastMonthWorkStatusSummary(stamp, user);
			Assert.assertEquals(31,workList2.size());
			Assert.assertEquals(7.00D,workList2.get(0).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(0).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(5).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(5).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(3).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(3).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(30).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(30).getAttendanceStatus());
			
			for (AttendanceStatus workStatus : workList2){
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
				System.out.println("=============JerryDebug:"+ workStatus.getDescription());
				System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
				System.out.println("=============JerryDebug:"+ workStatus.getFullName());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getUsername());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getDate());
				System.out.println("=============JerryDebug:"+ workStatus.getDuration());
			}
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testGetMonthWorkStatusSummary() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,6,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-07-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-07-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-07-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-07-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			List<AttendanceStatus> workList2 = getClockRecordService().getMonthWorkStatusSummary(stamp, user);
			Assert.assertEquals(31,workList2.size());
			Assert.assertEquals(7.00D,workList2.get(0).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(0).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(5).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(5).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(3).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(3).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(30).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(30).getAttendanceStatus());
			
			for (AttendanceStatus workStatus : workList2){
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
				System.out.println("=============JerryDebug:"+ workStatus.getDescription());
				System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
				System.out.println("=============JerryDebug:"+ workStatus.getFullName());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getUsername());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getDate());
				System.out.println("=============JerryDebug:"+ workStatus.getDuration());
			}
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testGetWeekWorkStatusSummary() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			List<AttendanceStatus> workList2 = getClockRecordService().getWeekWorkStatusSummary(stamp, user);
			Assert.assertEquals(7,workList2.size());
			Assert.assertEquals(0.00D,workList2.get(0).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(0).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(1).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(1).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(6).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(6).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(3).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(3).getAttendanceStatus());
			
			for (AttendanceStatus workStatus : workList2){
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
				System.out.println("=============JerryDebug:"+ workStatus.getDescription());
				System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
				System.out.println("=============JerryDebug:"+ workStatus.getFullName());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getUsername());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getDate());
				System.out.println("=============JerryDebug:"+ workStatus.getDuration());
			}
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testGetYearWorkStatusSummary() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			int augCount = 31+28+31+30+31+30+31;
			
			List<AttendanceStatus> workList2 = getClockRecordService().getYearWorkStatusSummary(stamp, user);
			Assert.assertEquals(365,workList2.size());
			Assert.assertEquals(0.00D,workList2.get(0).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(0).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(augCount).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(augCount).getAttendanceStatus());
			Assert.assertEquals(7.00D,workList2.get(augCount + 6-1).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("normal",workList2.get(augCount + 6-1).getAttendanceStatus());
			Assert.assertEquals(0.00D,workList2.get(364).getDuration().doubleValue(),0.01D);
			Assert.assertEquals("absent",workList2.get(364).getAttendanceStatus());
			
			for (AttendanceStatus workStatus : workList2){
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceCount());
				System.out.println("=============JerryDebug:"+ workStatus.getAttendanceStatus());
				System.out.println("=============JerryDebug:"+ workStatus.getDescription());
				System.out.println("=============JerryDebug:"+ workStatus.getEmpId());
				System.out.println("=============JerryDebug:"+ workStatus.getFullName());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getUsername());
				System.out.println("=============JerryDebug:"+ workStatus.getUserId());
				System.out.println("=============JerryDebug:"+ workStatus.getDate());
				System.out.println("=============JerryDebug:"+ workStatus.getDuration());
			}
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
	
		
		public void testGetThisMonthWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);

			Assert.assertEquals(14.00D, (getClockRecordService().getThisMonthWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testGetLastMonthWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,8,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			Assert.assertEquals(14.00D, (getClockRecordService().getLastMonthWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testMonthWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			Assert.assertEquals(14.00D, (getClockRecordService().getMonthWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testThisWeekWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			Assert.assertEquals(14.00D, (getClockRecordService().getThisWeekWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}

		public void testLastWeekWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,12,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			Assert.assertEquals(14.00D, (getClockRecordService().getLastWeekWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
		
		public void testWeekWorkSum() throws Exception{
			// prepare data
			Connection con = DBConf.initDB();
			Timestamp stamp = new Timestamp(111,7,6,0,0,0,0);
		
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('3','4','160208','2011-08-06 9:00:00','My description.');").executeUpdate();
			con.prepareStatement("insert into clockRecord values ('4','4','160208','2011-08-06 16:00:00','My description.');").executeUpdate();		
			con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
					 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
					 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
			
			// Test
			User user = new User();
			user.setId(4L);
			user.setEmpid(160208L);
			
			Assert.assertEquals(14.00D, (getClockRecordService().getWeekWorkSum(stamp, user).doubleValue()),0.01D);
			
			// Clean
			con.prepareStatement("delete from clockrecord;").executeUpdate();
			con.prepareStatement("delete from users;").executeUpdate();	
		}
}
