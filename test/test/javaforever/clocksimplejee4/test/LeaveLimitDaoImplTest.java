package test.javaforever.clocksimplejee4.test;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveLimitDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveLimitDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLimit;

import junit.framework.Assert;

/**
 * LeaveLimitDaoImplTest 
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
public class LeaveLimitDaoImplTest extends DataSafeTestCase {

	private static LeaveLimitDao leavelimitDao = new LeaveLimitDaoImpl();

	public static LeaveLimitDao getLeaveLimitDao() {
		return leavelimitDao;
	}

	public static void setLeaveLimitDaoImpl(LeaveLimitDao leavelimitDao) {
		LeaveLimitDaoImplTest.leavelimitDao = leavelimitDao;
	}

	public void testlistAllLeaveLimit() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		con.prepareStatement("insert into leavelimits values ('1','1','10','20','30','40','My description.');").executeUpdate();

		// Test
        List<LeaveLimit> leavelimitList = getLeaveLimitDao().listAllLeaveLimits(con);
        Assert.assertEquals(1,leavelimitList.size());
        LeaveLimit leavelimit = leavelimitList.get(0);  
        
        Assert.assertEquals("My description.", leavelimit.getDescription());
        
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		}
	}
	
	
	public void testUpdateLeaveLimit() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		con.prepareStatement("insert into leavelimits values ('1','1','10','20','30','40','My description.');").executeUpdate();

		// Test
        List<LeaveLimit> leavelimitList = getLeaveLimitDao().getLeaveLimitListByEmployeeTypeId(con,1L);
        Assert.assertEquals(1,leavelimitList.size());
        LeaveLimit leavelimit = leavelimitList.get(0);     
        Assert.assertEquals("My description.", leavelimit.getDescription());
        
        leavelimit.setDescription("My second description.");
        leavelimit.setEmployeeTypeId(40000L);
        leavelimit.setAnnualLeaveLimit(11);
        leavelimit.setSickLeaveLimit(22);
        leavelimit.setPrivateLeaveLimit(33);
        leavelimit.setOtherLeaveLimit(44);
        
        getLeaveLimitDao().updateLeaveLimit(con,leavelimit);
        
        LeaveLimit leavelimit2 = getLeaveLimitDao().findLeaveLimitById(con,1);
        
        Assert.assertEquals(40000L, leavelimit2.getEmployeeTypeId());
        Assert.assertEquals("My second description.", leavelimit2.getDescription());
        Assert.assertEquals(11, leavelimit2.getAnnualLeaveLimit());
        Assert.assertEquals(22, leavelimit2.getSickLeaveLimit());
        Assert.assertEquals(33, leavelimit2.getPrivateLeaveLimit());
        Assert.assertEquals(44, leavelimit2.getOtherLeaveLimit());
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		}
	}
	

	public void testFindLeaveLimitById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		con.prepareStatement("insert into leavelimits values ('1','1','10','20','30','40','My description.');").executeUpdate();
		
		// Test
        LeaveLimit leavelimit = getLeaveLimitDao().findLeaveLimitById(con,1);
        
        Assert.assertEquals(1,leavelimit.getId());
        Assert.assertEquals(1L, leavelimit.getEmployeeTypeId());
        Assert.assertEquals("My description.", leavelimit.getDescription());
        
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		}
	}
	

	public void testGetLeaveLimitListByEmployeeTypeId() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		con.prepareStatement("insert into leavelimits values ('1','1','10','20','30','40','My description.');").executeUpdate();
		
		// Test
        List<LeaveLimit> leavelimitList = getLeaveLimitDao().getLeaveLimitListByEmployeeTypeId(con,1L);
        
        Assert.assertEquals(1, leavelimitList.size());
        LeaveLimit leavelimit = leavelimitList.get(0);
        
        Assert.assertEquals(1,leavelimit.getId());
        Assert.assertEquals(1L, leavelimit.getEmployeeTypeId());
        Assert.assertEquals("My description.", leavelimit.getDescription());
        
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		}
	}
	
	
	public void testCreateLeaveLimit() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
			
		// Test
        LeaveLimit leavelimit = new LeaveLimit();
        leavelimit.setDescription("My second description.");
        leavelimit.setEmployeeTypeId(40000L);
        leavelimit.setAnnualLeaveLimit(11);
        leavelimit.setSickLeaveLimit(22);
        leavelimit.setPrivateLeaveLimit(33);
        leavelimit.setOtherLeaveLimit(44); 
        
        getLeaveLimitDao().createLeaveLimit(con,leavelimit);
        
        LeaveLimit leavelimit1 = getLeaveLimitDao().listAllLeaveLimits(con).get(0);
        
        Assert.assertEquals(40000L, leavelimit1.getEmployeeTypeId());
        Assert.assertEquals("My second description.", leavelimit1.getDescription());
        Assert.assertEquals(11, leavelimit1.getAnnualLeaveLimit());
        Assert.assertEquals(22, leavelimit1.getSickLeaveLimit());
        Assert.assertEquals(33, leavelimit1.getPrivateLeaveLimit());
        Assert.assertEquals(44, leavelimit1.getOtherLeaveLimit());      
 
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		}
	}
	

	public void testDeleteLeaveLimit() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavelimits;").executeUpdate();
		con.prepareStatement("insert into leavelimits values ('1','1','10','20','30','40','My description.');").executeUpdate();
		// Test
        List<LeaveLimit> leavelimitList = getLeaveLimitDao().listAllLeaveLimits(con);
        Assert.assertEquals(1,leavelimitList.size());
        
        getLeaveLimitDao().deleteLeaveLimit(con,1L);
        
        List<LeaveLimit> leavelimitList1 = getLeaveLimitDao().listAllLeaveLimits(con);
        Assert.assertEquals(0,leavelimitList1.size());      
 
		// Clean
		con.prepareStatement("delete from leavelimits;").executeUpdate();				
		}
	}

}
