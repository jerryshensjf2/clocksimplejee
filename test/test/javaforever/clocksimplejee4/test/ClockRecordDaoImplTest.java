package test.javaforever.clocksimplejee4.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.dao.ClockRecordDao;
import com.javaforever.clocksimplejee4.daoimpl.ClockRecordDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.domain.User;

import junit.framework.Assert;

/**
 * ClockRecordDaoImplTest 
 * 
 * For developer:
 * Do not add setUp and tearDown method
 * Do not use DBConf.switchToTest()
 * Do not use DBConf.switchToProduction()
 * Must extends DataSafeTestCase to protect data safe
 * @author Jerry Shen
 * @email jerry_shen_sjf@qq.com
 * 
 * @version 1.0
 *
 */
public class ClockRecordDaoImplTest extends DataSafeTestCase {

	private static ClockRecordDao clockRecordDao = new ClockRecordDaoImpl();

	public static ClockRecordDao getClockRecordDao() {
		return clockRecordDao;
	}

	public static void setClockRecordDaoImpl(ClockRecordDao clockRecordDao) {
		ClockRecordDaoImplTest.clockRecordDao = clockRecordDao;
	}
	
	public void testlistAllClockRecord() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','1','160208','1972-11-01 14:00:00','My description.');").executeUpdate();

		// Test
        List<ClockRecord> clockRecordList = getClockRecordDao().listAllClockRecord(con);
        Assert.assertEquals(1,clockRecordList.size());
        ClockRecord clockRecord = clockRecordList.get(0);   
        Assert.assertEquals(160208L, clockRecord.getEmpId());
        
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
	
	
	public void testUpdateClockRecord() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','1','160208','1972-11-01 14:00:00','My description.');").executeUpdate();

		// Test
        List<ClockRecord> clockRecordList = getClockRecordDao().getClockRecordListByEmpid(con,160208L);
        Assert.assertEquals(1,clockRecordList.size());
        ClockRecord clockRecord = clockRecordList.get(0);     
        Assert.assertEquals(160208L, clockRecord.getEmpId());
        
        clockRecord.setDescription("My second description.");
        clockRecord.setEmpId(40000L);
        clockRecord.setTimeStamp(Timestamp.valueOf("2010-07-03 12:05:30.0"));
        clockRecord.setUserId(4L);

        getClockRecordDao().updateClockRecord(con,clockRecord);
        
        ClockRecord clockRecord2 = getClockRecordDao().findClockRecordById(con,1);
        
        Assert.assertEquals(40000L, clockRecord2.getEmpId());
        Assert.assertEquals("My second description.", clockRecord2.getDescription());
        Assert.assertEquals("2010-07-03 12:05:30.0", clockRecord2.getTimeStamp().toString());
        Assert.assertEquals(4L, clockRecord2.getUserId());

		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
	

	public void testFindClockRecordById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','1','160208','1972-11-01 14:00:00','My description.');").executeUpdate();
		
		// Test
        ClockRecord clockRecord = getClockRecordDao().findClockRecordById(con,1);
        
        Assert.assertEquals(1,clockRecord.getId());
        Assert.assertEquals(160208L, clockRecord.getEmpId());
        
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
	
	public void testGetClockRecordListByEmpid() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','1','160208','1972-11-01 14:00:00','My description.');").executeUpdate();
		
		// Test
        List<ClockRecord> clockRecordList = getClockRecordDao().getClockRecordListByEmpid(con,160208L);
        
        Assert.assertEquals(1, clockRecordList.size());
        ClockRecord clockRecord = clockRecordList.get(0);
        
        Assert.assertEquals(1,clockRecord.getId());
        Assert.assertEquals(160208L, clockRecord.getEmpId());
        
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
		
	public void testCreateClockRecord() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
			
		// Test
        ClockRecord clockRecord = new ClockRecord();
        clockRecord.setUserId(1L);
        clockRecord.setEmpId(160208L);
        clockRecord.setTimeStamp(Timestamp.valueOf("1972-11-01 14:00:00"));
        clockRecord.setDescription("My first clockRecord");    
        
        getClockRecordDao().createClockRecord(con,clockRecord);
        
        ClockRecord clockRecord1 = getClockRecordDao().listAllClockRecord(con).get(0);
        
        Assert.assertEquals(160208L, clockRecord1.getEmpId());        
 
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
	

	public void testDeleteClockRecord() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','1','160208','1972-11-01 14:00:00','My description.');").executeUpdate();
		// Test
        List<ClockRecord> clockRecordList = getClockRecordDao().listAllClockRecord(con);
        Assert.assertEquals(1,clockRecordList.size());
        
        getClockRecordDao().deleteClockRecord(con,1L);
        
        List<ClockRecord> clockRecordList1 = getClockRecordDao().listAllClockRecord(con);
        Assert.assertEquals(0,clockRecordList1.size());      
 
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
		
	}
	
	public void testClock() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(2L);
		user.setEmpid(160208L);
		
		String description = "Jerry description";
		
		getClockRecordDao().clock(con,user, description);
        List<ClockRecord> clockRecordList = getClockRecordDao().listAllClockRecord(con);
        Assert.assertEquals(1,clockRecordList.size());
        System.out.println("Jerry Description:" + clockRecordList.get(0).getTimeStamp());
		
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}
	
	public void testTodayDuration() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		Date nowdate = new Date(System.currentTimeMillis());
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','"+(nowdate.getYear()+1900)+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate()+" 9:00:00','My description.');").executeUpdate();
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		String description = "Jerry description";
		
//		getClockRecordDao().clock(user, description);
//		Thread.sleep(4000);
		
        BigDecimal bDuration = getClockRecordDao().todayDuration(con,user);
        Assert.assertEquals(0,bDuration.floatValue(), 0.01);
        
//        Thread.sleep(10000);
//		getClockRecordDao().clock(user, description);
        con.prepareStatement("insert into clockRecord values ('2','4','160208','"+(nowdate.getYear()+1900)+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate()+" 16:00:00','My description.');").executeUpdate();
		
		BigDecimal bDuration2 = getClockRecordDao().todayDuration(con,user);
		Assert.assertTrue("duration not correct", bDuration2.doubleValue() > 0);
		System.out.println(bDuration2.toPlainString());

		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		}
	}
	
	public void testListAllTodayClockRecord() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockRecord;").executeUpdate();
			
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		String description = "Jerry description";
		
		getClockRecordDao().clock(con,user, description);
		Thread.sleep(1000);
		getClockRecordDao().clock(con,user, description);
		Thread.sleep(1000);
		getClockRecordDao().clock(con,user, description);
		
        List<ClockRecord> clockRecordList = getClockRecordDao().listAllClockRecord(con);
        Assert.assertEquals(3,clockRecordList.size());
        System.out.println("Jerry Description:" + clockRecordList.get(0).getTimeStamp());
		
		// Clean
		con.prepareStatement("delete from clockRecord;").executeUpdate();
		}
	}

	
	public void testDayDuration() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-07-16 9:00:00','My description.');").executeUpdate();
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		String description = "Jerry description";
		
//		getClockRecordDao().clock(user, description);
//		Thread.sleep(4000);
		
        BigDecimal bDuration = getClockRecordDao().dayDuration(con,new Timestamp(111,6,16,0,0,0,0),user);
        Assert.assertEquals(0,bDuration.floatValue(), 0.01);
        
//        Thread.sleep(10000);
//		getClockRecordDao().clock(user, description);
        con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-07-16 16:00:00','My description.');").executeUpdate();
		
		BigDecimal bDuration2 = getClockRecordDao().dayDuration(con,new Timestamp(111,6,16,0,0,0,0),user);
		Assert.assertEquals(7, bDuration2.doubleValue(),0.01);
		System.out.println(bDuration2.toPlainString());

		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		}
	}
	
	public void testListAllSomeDayClockRecord() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		long millis = System.currentTimeMillis();
		Date nowdate = new Date(millis);
		Timestamp timestamp = new Timestamp(millis);
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','"+(nowdate.getYear()+1900)+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate()+" 9:00:00','My description.');").executeUpdate();
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		String description = "Jerry description";
		
//		getClockRecordDao().clock(user, description);
//		Thread.sleep(4000);
		
        List<ClockRecord> clockrecordList = getClockRecordDao().listAllSomeDayClockRecord(con,timestamp,user);
        Assert.assertEquals(1,clockrecordList.size());
        
//        Thread.sleep(10000);
//		getClockRecordDao().clock(user, description);
        con.prepareStatement("insert into clockRecord values ('2','4','160208','"+(nowdate.getYear()+1900)+"-"+(nowdate.getMonth()+1)+"-"+nowdate.getDate()+" 16:00:00','My description.');").executeUpdate();
		
        List<ClockRecord> clockrecordList2 = getClockRecordDao().listAllSomeDayClockRecord(con,timestamp,user);
		Assert.assertEquals(2,clockrecordList2.size());
				
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		}
	}
	
	public void testGetPeriodSummary() throws Exception{
		try(Connection con = DBConf.initDB()){
		Date nowdate = new Date(111,7,5);
		Timestamp timestamp = new Timestamp(nowdate.getTime());
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<BigDecimal> worktimeList2 = getClockRecordDao().getPeriodSummary(con,new Timestamp(111,7,1,0,0,0,0), new Timestamp(111,7,2,0,0,0,0), user);
		Assert.assertEquals(1,worktimeList2.size());
		Assert.assertEquals(7.00D,worktimeList2.get(0).doubleValue(),0.01D);
		
		for (BigDecimal workTime : worktimeList2){
			System.out.println("=============JerryDebug:"+ workTime);
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		}
	}
	
	public void testGetPeriodWorkStatusSummary() throws Exception{
		try(Connection con = DBConf.initDB()){
		Date nowdate = new Date(111,7,1);
		Timestamp timestamp = new Timestamp(nowdate.getTime());
		Date enddate = new Date(111,8,1);
		Timestamp timestamp2 = new Timestamp(enddate.getTime());
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('1','4','160208','2011-08-01 9:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into clockRecord values ('2','4','160208','2011-08-01 16:00:00','My description.');").executeUpdate();
		con.prepareStatement("insert into users (id,empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
							 " address, address1,phone, mobile,login_failure) values ('4','160208','jerry','Jerry','Shen','male', " +
							 " sha1('jerry19847327'),'19847327','Y','Y','sjf','sjf','li jin road','li jin road','888888','7777777','0');").executeUpdate();
		
		// Test
		User user = new User();
		user.setId(4L);
		user.setEmpid(160208L);
		
		List<AttendanceStatus> list2 = getClockRecordDao().getPeriodWorkStatusSummary(con,timestamp,timestamp2, user);
		Assert.assertEquals(31,list2.size());
		Assert.assertEquals(7.00D,list2.get(0).getDuration().doubleValue(),0.01D);
		
		for (AttendanceStatus status : list2){
			System.out.println("=============JerryDebug:"+ status.getUsername());
			System.out.println("=============JerryDebug:"+ status.getDuration());
			System.out.println("=============JerryDebug:"+ status.getAttendanceStatus());
		}
		
		// Clean
		con.prepareStatement("delete from clockrecord;").executeUpdate();
		con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	
}
