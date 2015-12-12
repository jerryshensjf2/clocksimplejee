package test.javaforever.clocksimplejee4.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Leave;

import junit.framework.Assert;

/**
 * LeaveDaoImplTest 
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
public class LeaveDaoImplTest extends DataSafeTestCase {

	private static LeaveDao leaveDao = new LeaveDaoImpl();

	public static LeaveDao getLeaveDao() {
		return leaveDao;
	}

	public static void setLeaveDaoImpl(LeaveDao leaveDao) {
		LeaveDaoImplTest.leaveDao = leaveDao;
	}
	
	public void testlistAllLeave() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
		con.prepareStatement("insert into leaves values ('1','1','160208','1972-11-01','2','My description.');").executeUpdate();

		// Test
        List<Leave> leaveList = getLeaveDao().listAllLeaves(con);
        Assert.assertEquals(1,leaveList.size());
        Leave leave = leaveList.get(0);  
        
        Assert.assertEquals(160208L, leave.getEmpId());
        Assert.assertEquals("My description.", leave.getDescription());
        
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();
		}
	}
	
	
	public void testUpdateLeave() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
		con.prepareStatement("insert into leaves values ('1','1','160208','1972-11-01','2','My description.');").executeUpdate();

		// Test
        List<Leave> leaveList = getLeaveDao().getLeaveListByEmpid(con,160208L);
        Assert.assertEquals(1,leaveList.size());
        Leave leave = leaveList.get(0);     
        Assert.assertEquals(160208L, leave.getEmpId());
        
        leave.setDescription("My second description.");
        leave.setEmpId(40000L);
        leave.setDay(Date.valueOf("2010-07-03"));
        leave.setUserId(4L);
        leave.setLeaveTypeId(4L);
        
        getLeaveDao().updateLeave(con,leave);
        
        Leave leave2 = getLeaveDao().findLeaveById(con,1);
        
        Assert.assertEquals(40000L, leave2.getEmpId());
        Assert.assertEquals("My second description.", leave2.getDescription());
        Assert.assertEquals(Date.valueOf("2010-07-03"), leave2.getDay());
        Assert.assertEquals(4L, leave2.getUserId());
        Assert.assertEquals(4L, leave2.getLeaveTypeId());
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();
		}
	}
	

	public void testFindLeaveById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
		con.prepareStatement("insert into leaves values ('1','1','160208','1972-11-01','2','My description.');").executeUpdate();
		
		// Test
        Leave leave = getLeaveDao().findLeaveById(con,1);
        
        Assert.assertEquals(1,leave.getId());
        Assert.assertEquals(160208L, leave.getEmpId());
        
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();
		}
	}
	
	
	public void testGetLeaveListByEmpid() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
		con.prepareStatement("insert into leaves values ('1','1','160208','1972-11-01','2','My description.');").executeUpdate();
		
		// Test
        List<Leave> leaveList = getLeaveDao().getLeaveListByEmpid(con,160208L);
        
        Assert.assertEquals(1, leaveList.size());
        Leave leave = leaveList.get(0);
        
        Assert.assertEquals(1,leave.getId());
        Assert.assertEquals(160208L, leave.getEmpId());
        
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();
		}
	}
	
		
	public void testCreateLeave() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
			
		// Test
        Leave leave = new Leave();
        leave.setUserId(1L);
        leave.setEmpId(160208L);
        leave.setDay(Date.valueOf("1972-11-01"));
        leave.setDescription("My first leave");    
        
        getLeaveDao().createLeave(con,leave);
        
        Leave leave1 = getLeaveDao().listAllLeaves(con).get(0);
        
        Assert.assertEquals(1L, leave.getUserId());
        Assert.assertEquals(160208L, leave1.getEmpId());
        Assert.assertEquals(Date.valueOf("1972-11-01"), leave.getDay());
        Assert.assertEquals("My first leave", leave.getDescription());        
 
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();
		}
	}
	

	public void testDeleteLeave() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaves;").executeUpdate();
		con.prepareStatement("insert into leaves values ('1','1','160208','1972-11-01','2','My description.');").executeUpdate();
		// Test
        List<Leave> leaveList = getLeaveDao().listAllLeaves(con);
        Assert.assertEquals(1,leaveList.size());
        
        getLeaveDao().deleteLeave(con,1L);
        
        List<Leave> leaveList1 = getLeaveDao().listAllLeaves(con);
        Assert.assertEquals(0,leaveList1.size());      
 
		// Clean
		con.prepareStatement("delete from leaves;").executeUpdate();				
		}
	}

}
