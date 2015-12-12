package test.javaforever.clocksimplejee4.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveTypeDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveTypeDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveType;

import junit.framework.Assert;

/**
 * LeaveTypeDaoImplTest 
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
public class LeaveTypeDaoImplTest extends DataSafeTestCase {

	private static LeaveTypeDao leavetypeDao = new LeaveTypeDaoImpl();

	public static LeaveTypeDao getLeaveTypeDao() {
		return leavetypeDao;
	}

	public static void setLeaveTypeDaoImpl(LeaveTypeDao leavetypeDao) {
		LeaveTypeDaoImplTest.leavetypeDao = leavetypeDao;
	}

	public void testlistAllLeaveType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		con.prepareStatement("insert into leavetypes values ('1','AnnualLeave','0','My description.');").executeUpdate();

		// Test
        List<LeaveType> leavetypeList = getLeaveTypeDao().listAllLeaveTypes(con);
        Assert.assertEquals(1,leavetypeList.size());
        LeaveType leavetype = leavetypeList.get(0);  
        
        Assert.assertEquals("My description.", leavetype.getDescription());
        
		// Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		}
	}
		
	public void testUpdateLeaveType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		con.prepareStatement("insert into leavetypes values ('1','AnnualLeave','0','My description.');").executeUpdate();

		// Test
        LeaveType leavetype = getLeaveTypeDao().findLeaveTypeByLeaveTypeName(con,"AnnualLeave"); 
        Assert.assertEquals("My description.", leavetype.getDescription());
        
        leavetype.setLeaveTypeName("SickLeave");
        leavetype.setUnitFine(new BigDecimal(22));
        leavetype.setDescription("Jerry description");
        
        getLeaveTypeDao().updateLeaveType(con,leavetype);
        
        LeaveType leavetype2 = getLeaveTypeDao().findLeaveTypeById(con,1);
        
        Assert.assertEquals("SickLeave", leavetype2.getLeaveTypeName());
        Assert.assertEquals("Jerry description", leavetype2.getDescription());
        Assert.assertEquals(22, Double.parseDouble(leavetype.getUnitFine().toPlainString()),0.001);

        // Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		}
	}
	

	public void testFindLeaveTypeById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		con.prepareStatement("insert into leavetypes values ('1','AnnualLeave','0','My description.');").executeUpdate();
		
		// Test
        LeaveType leavetype = getLeaveTypeDao().findLeaveTypeById(con,1);
        
        Assert.assertEquals(1,leavetype.getId());
        Assert.assertEquals("AnnualLeave", leavetype.getLeaveTypeName());
        Assert.assertEquals("My description.", leavetype.getDescription());
        Assert.assertEquals(0, Double.parseDouble(leavetype.getUnitFine().toPlainString()),0.001);
		// Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		}
	}
	

	public void testFindLeaveTypeByLeaveTypeName() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		con.prepareStatement("insert into leavetypes values ('1','AnnualLeave','0','My description.');").executeUpdate();
		
		// Test
        LeaveType leavetype = getLeaveTypeDao().findLeaveTypeByLeaveTypeName(con,"AnnualLeave");

        Assert.assertEquals(1,leavetype.getId());
        Assert.assertEquals("AnnualLeave", leavetype.getLeaveTypeName());
        Assert.assertEquals("My description.", leavetype.getDescription());
        Assert.assertEquals(0, Double.parseDouble(leavetype.getUnitFine().toPlainString()),0.001);
        
		// Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		}
	}
	
	
	public void testCreateLeaveType() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
			
		// Test
        LeaveType leavetype = new LeaveType();
        leavetype.setLeaveTypeName("SickLeave");
        leavetype.setUnitFine(new BigDecimal(22));
        leavetype.setDescription("Jerry description");
        
        getLeaveTypeDao().createLeaveType(con,leavetype);
        
        LeaveType leavetype1 = getLeaveTypeDao().listAllLeaveTypes(con).get(0);
        
        Assert.assertEquals("SickLeave", leavetype1.getLeaveTypeName());
        Assert.assertEquals("Jerry description", leavetype1.getDescription());
        Assert.assertEquals(22, Double.parseDouble(leavetype1.getUnitFine().toPlainString()),0.001);    
 
		// Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		}
	}
	

	public void testDeleteLeaveType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from leavetypes;").executeUpdate();
		con.prepareStatement("insert into leavetypes values ('1','AnnualLeave','0','My description.');").executeUpdate();
		// Test
        List<LeaveType> leavetypeList = getLeaveTypeDao().listAllLeaveTypes(con);
        Assert.assertEquals(1,leavetypeList.size());
        
        getLeaveTypeDao().deleteLeaveType(con,1L);
        
        List<LeaveType> leavetypeList1 = getLeaveTypeDao().listAllLeaveTypes(con);
        Assert.assertEquals(0,leavetypeList1.size());      
 
		// Clean
		con.prepareStatement("delete from leavetypes;").executeUpdate();		
		}
	}
}
