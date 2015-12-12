package test.javaforever.clocksimplejee4.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.EmployeeTypeDao;
import com.javaforever.clocksimplejee4.daoimpl.EmployeeTypeDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.EmployeeType;

import junit.framework.Assert;

/**
 * EmployeeTypeDaoImplTest 
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
public class EmployeeTypeDaoImplTest extends DataSafeTestCase {

	private static EmployeeTypeDao employeetypeDao = new EmployeeTypeDaoImpl();

	public static EmployeeTypeDao getEmployeeTypeDao() {
		return employeetypeDao;
	}

	public static void setEmployeeTypeDaoImpl(EmployeeTypeDao employeetypeDao) {
		EmployeeTypeDaoImplTest.employeetypeDao = employeetypeDao;
	}

	public void testlistAllEmployeeType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		con.prepareStatement("insert into employeetypes values ('1','SA','My description.');").executeUpdate();

		// Test
        List<EmployeeType> employeetypeList = getEmployeeTypeDao().listAllEmployeeTypes(con);
        Assert.assertEquals(1,employeetypeList.size());
        EmployeeType employeetype = employeetypeList.get(0);  
        
        Assert.assertEquals("My description.", employeetype.getDescription());
        
		// Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		}
	}
		
	public void testUpdateEmployeeType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		con.prepareStatement("insert into employeetypes values ('1','SA','My description.');").executeUpdate();

		// Test
        EmployeeType employeetype = getEmployeeTypeDao().findEmployeeTypeByEmployeeTypeName(con,"SA"); 
        Assert.assertEquals("My description.", employeetype.getDescription());
        
        employeetype.setEmployeeTypeName("A");
        employeetype.setDescription("Jerry description");

        getEmployeeTypeDao().updateEmployeeType(con,employeetype);
        
        EmployeeType employeetype2 = getEmployeeTypeDao().findEmployeeTypeById(con,1);
        
        Assert.assertEquals("A", employeetype2.getEmployeeTypeName());
        Assert.assertEquals("Jerry description", employeetype2.getDescription());

        // Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		}
		
	}
	

	public void testFindEmployeeTypeById() throws Exception{
		
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		con.prepareStatement("insert into employeetypes values ('1','SA','My description.');").executeUpdate();
		
		// Test
        EmployeeType employeetype = getEmployeeTypeDao().findEmployeeTypeById(con,1);
        
        Assert.assertEquals(1,employeetype.getId());
        Assert.assertEquals("SA", employeetype.getEmployeeTypeName());
        Assert.assertEquals("My description.", employeetype.getDescription());
		// Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();	
		}
	}
	

	public void testFindEmployeeTypeByEmployeeTypeName() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		con.prepareStatement("insert into employeetypes values ('1','SA','My description.');").executeUpdate();
		
		// Test
        EmployeeType employeetype = getEmployeeTypeDao().findEmployeeTypeByEmployeeTypeName(con,"SA");

        Assert.assertEquals(1,employeetype.getId());
        Assert.assertEquals("SA", employeetype.getEmployeeTypeName());
        Assert.assertEquals("My description.", employeetype.getDescription());
        
		// Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();	
		}
	}
	
	
	public void testCreateEmployeeType() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
			
		// Test
        EmployeeType employeetype = new EmployeeType();
        employeetype.setEmployeeTypeName("SA");
        employeetype.setDescription("Jerry description");
        
        getEmployeeTypeDao().createEmployeeType(con,employeetype);
        
        EmployeeType employeetype1 = getEmployeeTypeDao().listAllEmployeeTypes(con).get(0);
        
        Assert.assertEquals("SA", employeetype1.getEmployeeTypeName());
        Assert.assertEquals("Jerry description", employeetype1.getDescription());
 
		// Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		}
	}
	

	public void testDeleteEmployeeType() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from employeetypes;").executeUpdate();
		con.prepareStatement("insert into employeetypes values ('1','SA','My description.');").executeUpdate();
		// Test
        List<EmployeeType> employeetypeList = getEmployeeTypeDao().listAllEmployeeTypes(con);
        Assert.assertEquals(1,employeetypeList.size());
        
        getEmployeeTypeDao().deleteEmployeeType(con,1L);
        
        List<EmployeeType> employeetypeList1 = getEmployeeTypeDao().listAllEmployeeTypes(con);
        Assert.assertEquals(0,employeetypeList1.size());      
 
		// Clean
		con.prepareStatement("delete from employeetypes;").executeUpdate();	
		}
	}

}
