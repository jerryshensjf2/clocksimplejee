package test.javaforever.clocksimplejee4.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.BonusDao;
import com.javaforever.clocksimplejee4.daoimpl.BonusDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Bonus;

import junit.framework.Assert;

/**
 * BonusDaoImplTest 
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
public class BonusDaoImplTest extends DataSafeTestCase {

	private static BonusDao bonusDao = new BonusDaoImpl();

	public static BonusDao getBonusDao() {
		return bonusDao;
	}

	public static void setBonusDaoImpl(BonusDao bonusDao) {
		BonusDaoImplTest.bonusDao = bonusDao;
	}
	
	public void testlistAllBonus() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
			con.prepareStatement("delete from bonus;").executeUpdate();
			con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
	
			// Test
	        List<Bonus> bonusList = getBonusDao().listAllBonus(con);
	        Assert.assertEquals(1,bonusList.size());
	        Bonus bonus = bonusList.get(0);
	        Assert.assertEquals("Monthly", bonus.getReason());    
	        Assert.assertEquals(160208L, bonus.getEmpId());
	        
			// Clean
			con.prepareStatement("delete from bonus;").executeUpdate();
		}
	}
	
	
	public void testGetBonusListByEmpid() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();

		// Test
        List<Bonus> bonusList = getBonusDao().getBonusListByEmpid(con,160208L);
        Assert.assertEquals(1,bonusList.size());
        Bonus bonus = bonusList.get(0);
        Assert.assertEquals("Monthly", bonus.getReason());       
        Assert.assertEquals(160208L, bonus.getEmpId());
        
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();
		}
	}
	
	public void testFindBonusById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
		
		// Test
        Bonus bonus = getBonusDao().findBonusById(con,1);
        
        Assert.assertEquals(1,bonus.getId());
        Assert.assertEquals(160208L, bonus.getEmpId());
        
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();		
		}
	}
	
	public void testFindBonusByReason() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
		
		// Test
        Bonus bonus = getBonusDao().findBonusByReason(con,"Monthly");
        
        Assert.assertEquals(1,bonus.getId());
        Assert.assertEquals(160208L, bonus.getEmpId());
        
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();	
		}
	}
	
	public void testUpateBonus() throws Exception{		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
		
		// Test
        Bonus bonus = getBonusDao().findBonusById(con,1);
        
        Assert.assertEquals(1,bonus.getId());
        Assert.assertEquals(160208L, bonus.getEmpId());
        
        bonus.setEmpId(40000L);
        bonus.setUserId(4L);
        bonus.setReason("Yearly");
        bonus.setDescription("My second bonus");
        bonus.setBonusBalance(new BigDecimal(3000.50));

        getBonusDao().updateBonus(con,bonus);
        
        Bonus bonus1 = getBonusDao().findBonusById(con,1);
        

        Assert.assertEquals(1,bonus1.getId());
        Assert.assertEquals(40000L, bonus1.getEmpId());
        Assert.assertEquals("Yearly", bonus1.getReason());
        
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();		
		}
	}
	
	public void testAddBonusBalance() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
		
		// Test
        Bonus bonus = getBonusDao().findBonusById(con,1);
        
        Assert.assertEquals(1,bonus.getId());
        Assert.assertEquals(160208L, bonus.getEmpId());        
        
        Bonus bonus1 = getBonusDao().findBonusByReason(con,"Monthly");
        getBonusDao().addBonusBalance(con,bonus1,new BigDecimal(3000));
        Bonus bonus2 = getBonusDao().findBonusByReason(con,"Monthly");

        Assert.assertEquals(1,bonus2.getId());
        Assert.assertEquals(160208L, bonus2.getEmpId());
        Assert.assertEquals("Monthly", bonus2.getReason());
        Assert.assertEquals(new BigDecimal("3200.0045"), bonus2.getBonusBalance());
        
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();	
		}
	}
	
	public void testCreateBonus() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
			
		// Test
        Bonus bonus = new Bonus();
        bonus.setUserId(1L);
        bonus.setEmpId(160208L);
        bonus.setReason("Monthly");
        bonus.setBonusBalance(new BigDecimal("200.0045"));
        bonus.setDescription("My first bonus");    
        
        getBonusDao().createBonus(con,bonus);
        
        Bonus bonus1 = getBonusDao().findBonusByReason(con,"Monthly");
        
        Assert.assertEquals("Monthly",bonus1.getReason());
        Assert.assertEquals(160208L, bonus1.getEmpId());        
 
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();	
		}
	}
	
	public void testDeleteBonus() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from bonus;").executeUpdate();
		con.prepareStatement("insert into bonus values ('1','1','160208','Monthly','200.0045','My first bonus');").executeUpdate();
		// Test
        List<Bonus> bonusList = getBonusDao().listAllBonus(con);
        Assert.assertEquals(1,bonusList.size());
        
        getBonusDao().deleteBonus(con,1L);
        
        List<Bonus> bonusList1 = getBonusDao().listAllBonus(con);
        Assert.assertEquals(0,bonusList1.size());      
 
		// Clean
		con.prepareStatement("delete from bonus;").executeUpdate();	
		}
	
	}

}
