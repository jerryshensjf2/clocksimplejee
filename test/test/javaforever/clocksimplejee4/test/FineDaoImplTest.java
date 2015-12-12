package test.javaforever.clocksimplejee4.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.FineDao;
import com.javaforever.clocksimplejee4.daoimpl.FineDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Fine;

import junit.framework.Assert;

/**
 * FineDaoImplTest 
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
public class FineDaoImplTest extends DataSafeTestCase {

	private static FineDao fineDao = new FineDaoImpl();

	public static FineDao getFineDao() {
		return fineDao;
	}

	public static void setFineDaoImpl(FineDao fineDao) {
		FineDaoImplTest.fineDao = fineDao;
	}
	
	public void testlistAllFine() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();

		// Test
        List<Fine> fineList = getFineDao().listAllFine(con);
        Assert.assertEquals(1,fineList.size());
        Fine fine = fineList.get(0);
        Assert.assertEquals("Monthly", fine.getReason());    
        Assert.assertEquals(160208L, fine.getEmpId());

		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	
	public void testGetFineListByEmpid() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();

		// Test
        List<Fine> fineList = getFineDao().getFineListByEmpid(con,160208L);
        Assert.assertEquals(1,fineList.size());
        Fine fine = fineList.get(0);
        Assert.assertEquals("Monthly", fine.getReason());       
        Assert.assertEquals(160208L, fine.getEmpId());
        
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testFindFineById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();
		
		// Test
        Fine fine = getFineDao().findFineById(con,1);
        
        Assert.assertEquals(1,fine.getId());
        Assert.assertEquals(160208L, fine.getEmpId());
        
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testFindFineByReason() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();
		
		// Test
        Fine fine = getFineDao().findFineByReason(con,"Monthly");
        
        Assert.assertEquals(1,fine.getId());
        Assert.assertEquals(160208L, fine.getEmpId());
        
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testUpateFine() throws Exception{		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();
		
		// Test
        Fine fine = getFineDao().findFineById(con,1);
        
        Assert.assertEquals(1,fine.getId());
        Assert.assertEquals(160208L, fine.getEmpId());
        
        fine.setEmpId(40000L);
        fine.setUserId(4L);
        fine.setReason("Yearly");
        fine.setDescription("My second fine");
        fine.setFineBalance(new BigDecimal(3000.50));

        getFineDao().updateFine(con,fine);
        
        Fine fine1 = getFineDao().findFineById(con,1);
        

        Assert.assertEquals(1,fine1.getId());
        Assert.assertEquals(40000L, fine1.getEmpId());
        Assert.assertEquals("Yearly", fine1.getReason());
        
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testAddFineBalance() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();
		
		// Test
        Fine fine = getFineDao().findFineById(con,1);
        
        Assert.assertEquals(1,fine.getId());
        Assert.assertEquals(160208L, fine.getEmpId());        
        
        Fine fine1 = getFineDao().findFineByReason(con,"Monthly");
        getFineDao().addFineBalance(con,fine1,new BigDecimal(3000));
        Fine fine2 = getFineDao().findFineByReason(con,"Monthly");

        Assert.assertEquals(1,fine2.getId());
        Assert.assertEquals(160208L, fine2.getEmpId());
        Assert.assertEquals("Monthly", fine2.getReason());
        Assert.assertEquals(new BigDecimal("3200.0045"), fine2.getFineBalance());
        
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testCreateFine() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
			
		// Test
        Fine fine = new Fine();
        fine.setUserId(1L);
        fine.setEmpId(160208L);
        fine.setReason("Monthly");
        fine.setFineBalance(new BigDecimal("200.0045"));
        fine.setDescription("My first fine");    
        
        getFineDao().createFine(con,fine);
        
        Fine fine1 = getFineDao().findFineByReason(con,"Monthly");
        
        Assert.assertEquals("Monthly",fine1.getReason());
        Assert.assertEquals(160208L, fine1.getEmpId());        
 
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();
		}
	}
	
	public void testDeleteFine() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from fine;").executeUpdate();
		con.prepareStatement("insert into fine values ('1','1','160208','Monthly','200.0045','My first fine');").executeUpdate();
		// Test
        List<Fine> fineList = getFineDao().listAllFine(con);
        Assert.assertEquals(1,fineList.size());
        
        getFineDao().deleteFine(con,1L);
        
        List<Fine> fineList1 = getFineDao().listAllFine(con);
        Assert.assertEquals(0,fineList1.size());      
 
		// Clean
		con.prepareStatement("delete from fine;").executeUpdate();				
		}
	}

}
