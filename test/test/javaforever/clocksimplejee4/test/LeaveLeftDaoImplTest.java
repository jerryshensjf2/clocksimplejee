package test.javaforever.clocksimplejee4.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveLeftDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveLeftDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLeft;

import junit.framework.Assert;

/**
 * LeaveLeftDaoImplTest 
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
public class LeaveLeftDaoImplTest extends DataSafeTestCase {

	private static LeaveLeftDao leaveLeftDao = new LeaveLeftDaoImpl();

	public static LeaveLeftDao getLeaveLeftDao() {
		return leaveLeftDao;
	}

	public static void setLeaveLeftDaoImpl(LeaveLeftDao leaveLeftDao) {
		LeaveLeftDaoImplTest.leaveLeftDao = leaveLeftDao;
	}

	public void testlistAllLeaveLeft() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		con.prepareStatement("insert into leaveLefts values ('1','1','160208','10','20','30','40','2012','My description.');").executeUpdate();

		// Test
        List<LeaveLeft> leaveLeftList = getLeaveLeftDao().listAllLeaveLefts(con);
        Assert.assertEquals(1,leaveLeftList.size());
        LeaveLeft leaveLeft = leaveLeftList.get(0);  
        
        Assert.assertEquals(160208L, leaveLeft.getEmpId());
        Assert.assertEquals("My description.", leaveLeft.getDescription());
        
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		}
	}
	
	public void testUpdateLeaveLeft() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		con.prepareStatement("insert into leaveLefts values ('1','1','160208','10','20','30','40','2012','My description.');").executeUpdate();

		// Test
        List<LeaveLeft> leaveLeftList = getLeaveLeftDao().getLeaveLeftListByEmpid(con,160208L);
        Assert.assertEquals(1,leaveLeftList.size());
        LeaveLeft leaveLeft = leaveLeftList.get(0);     
        Assert.assertEquals(160208L, leaveLeft.getEmpId());
        
        leaveLeft.setDescription("My second description.");
        leaveLeft.setEmpId(40000L);
        leaveLeft.setYear(2010);
        leaveLeft.setUserId(4L);
        leaveLeft.setAnnualLeaveLeft(11);
        leaveLeft.setSickLeaveLeft(22);
        leaveLeft.setPrivateLeaveLeft(33);
        leaveLeft.setOtherLeaveLeft(44);
        
        getLeaveLeftDao().updateLeaveLeft(con,leaveLeft);
        
        LeaveLeft leaveLeft2 = getLeaveLeftDao().findLeaveLeftById(con,1);
        
        Assert.assertEquals(40000L, leaveLeft2.getEmpId());
        Assert.assertEquals("My second description.", leaveLeft2.getDescription());
        Assert.assertEquals(2010, leaveLeft2.getYear());
        Assert.assertEquals(4L, leaveLeft2.getUserId());
        Assert.assertEquals(11, leaveLeft2.getAnnualLeaveLeft());
        Assert.assertEquals(22, leaveLeft2.getSickLeaveLeft());
        Assert.assertEquals(33, leaveLeft2.getPrivateLeaveLeft());
        Assert.assertEquals(44, leaveLeft2.getOtherLeaveLeft());
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		}
	}
	

	public void testFindLeaveLeftById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		con.prepareStatement("insert into leaveLefts values ('1','1','160208','10','20','30','40','2012','My description.');").executeUpdate();
		
		// Test
        LeaveLeft leaveLeft = getLeaveLeftDao().findLeaveLeftById(con,1);
        
        Assert.assertEquals(1,leaveLeft.getId());
        Assert.assertEquals(160208L, leaveLeft.getEmpId());
        
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		}
	}
	

	public void testGetLeaveLeftListByEmpid() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		con.prepareStatement("insert into leaveLefts values ('1','1','160208','10','20','30','40','2012','My description.');").executeUpdate();
		
		// Test
        List<LeaveLeft> leaveLeftList = getLeaveLeftDao().getLeaveLeftListByEmpid(con,160208L);
        
        Assert.assertEquals(1, leaveLeftList.size());
        LeaveLeft leaveLeft = leaveLeftList.get(0);
        
        Assert.assertEquals(1,leaveLeft.getId());
        Assert.assertEquals(160208L, leaveLeft.getEmpId());
        
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		}
	}
	
	public void testCreateLeaveLeft() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
			
		// Test
        LeaveLeft leaveLeft = new LeaveLeft();
        leaveLeft.setDescription("My second description.");
        leaveLeft.setEmpId(40000L);
        leaveLeft.setYear(2010);
        leaveLeft.setUserId(4L);
        leaveLeft.setAnnualLeaveLeft(11);
        leaveLeft.setSickLeaveLeft(22);
        leaveLeft.setPrivateLeaveLeft(33);
        leaveLeft.setOtherLeaveLeft(44); 
        
        getLeaveLeftDao().createLeaveLeft(con,leaveLeft);
        
        LeaveLeft leaveLeft1 = getLeaveLeftDao().listAllLeaveLefts(con).get(0);
        
        Assert.assertEquals(40000L, leaveLeft1.getEmpId());
        Assert.assertEquals("My second description.", leaveLeft1.getDescription());
        Assert.assertEquals(2010, leaveLeft1.getYear());
        Assert.assertEquals(4L, leaveLeft1.getUserId());
        Assert.assertEquals(11, leaveLeft1.getAnnualLeaveLeft());
        Assert.assertEquals(22, leaveLeft1.getSickLeaveLeft());
        Assert.assertEquals(33, leaveLeft1.getPrivateLeaveLeft());
        Assert.assertEquals(44, leaveLeft1.getOtherLeaveLeft());      
 
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		}
	}
	

	public void testDeleteLeaveLeft() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leaveLefts;").executeUpdate();
		con.prepareStatement("insert into leaveLefts values ('1','1','160208','10','20','30','40','2012','My description.');").executeUpdate();
		// Test
        List<LeaveLeft> leaveLeftList = getLeaveLeftDao().listAllLeaveLefts(con);
        Assert.assertEquals(1,leaveLeftList.size());
        
        getLeaveLeftDao().deleteLeaveLeft(con,1L);
        
        List<LeaveLeft> leaveLeftList1 = getLeaveLeftDao().listAllLeaveLefts(con);
        Assert.assertEquals(0,leaveLeftList1.size());      
 
		// Clean
		con.prepareStatement("delete from leaveLefts;").executeUpdate();				
		}
	}

}
