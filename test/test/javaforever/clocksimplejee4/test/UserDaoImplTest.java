package test.javaforever.clocksimplejee4.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import junit.framework.Assert;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.daoimpl.UserDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;

/**
 * UserDaoImplTest 
 * 
 * For developer:
 * Do not add setUp and tearDown method
 * Do not use DBConf.switchToTest()
 * Do not use DBConf.switchToProduction()
 * Must extends DataSafeTestCase to protect data safe
 * 
 * @author Jerry Shen
 * @email jerry_shen_sjf@qq.com
 * 
 * @version 1.0
 * 
 */
public class UserDaoImplTest extends DataSafeTestCase {

	private static UserDao userDaoImpl = new UserDaoImpl();
     
	public void testListAllUsers() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
		User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
		
		
		// Test begin
		userDaoImpl.registerUser(con,user);	
		userDaoImpl.registerUser(con,user0);	
		
		List<User> users = userDaoImpl.listAllUsers(con);
		System.out.print(users.size());
		Assert.assertTrue(users.size() == 2);
		
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		}
		
	}
	
	public void testFindUserById() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
		User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
		
		
		// Test begin
		userDaoImpl.registerUser(con,user);	
		userDaoImpl.registerUser(con,user0);	
		
		User u = new User();
		u.setEmpid(9996L);
		long id = userDaoImpl.getUserByEmpid(con, u).getId();
		User userZ = userDaoImpl.findUserById(con, id);
		Assert.assertEquals("mala",userZ.getUsername());
		
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		}
		
	}

	public void testGetUser() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        userDaoImpl.registerUser(con,user);	
        
        // Test begin
        User user0 = userDaoImpl.getUserByUsername(con,user);
        Assert.assertTrue("ralerry".equals(user0.getFirstname()));
        
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
        
	}

	public void testEditUser() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        
        // Test
        userDaoImpl.registerUser(con,user);	
        user.setFirstname("jerry");
        userDaoImpl.editUser(con,user);
		Assert.assertEquals("jerry", user.getFirstname());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	

	public void testLogin() throws Exception 
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        
        // Test
        userDaoImpl.adminAddUser(con,user);
        boolean islogin = userDaoImpl.login(con,user);
		Assert.assertTrue(islogin);
		Assert.assertEquals("Y", user.getIsadmin());
		Assert.assertEquals("Y", user.getIsactive());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();	
		}
	}

	public void testAddLoginFailure() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        
        // Test
        userDaoImpl.adminAddUser(con,user);
        
        user.setPassword("wrongpassword");
        boolean islogin = userDaoImpl.login(con,user);
		Assert.assertTrue(!islogin);
		
		User user2 = userDaoImpl.getUserByUsername(con,user);
		Assert.assertEquals("Y", user2.getIsadmin());
		Assert.assertEquals("Y", user2.getIsactive());
		Assert.assertTrue(user2.getLoginfailure() > 0);
		System.out.println("Jerry Test Login Failure:"+ user2.getLoginfailure());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
		
	}

	public void testAddLoginFailureViaUsername() throws Exception 
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        
        // Test
        userDaoImpl.adminAddUser(con,user);
        
		userDaoImpl.addLoginFailureViaUsername(con,user);
		
		User user2 = userDaoImpl.getUserByUsername(con,user);
		Assert.assertEquals("Y", user2.getIsadmin());
		Assert.assertEquals("Y", user2.getIsactive());
		Assert.assertTrue(user2.getLoginfailure() > 0);
		System.out.println("Jerry Test Login Failure:"+ user2.getLoginfailure());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	public void testAddClearLoginFailure() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        userDaoImpl.adminAddUser(con,user);
        
        // Test
        user.setPassword("wrongpassword");
        boolean islogin = userDaoImpl.login(con,user);
		Assert.assertTrue(!islogin);
		
		User user2 = userDaoImpl.getUserByUsername(con,user);
		Assert.assertEquals("Y", user2.getIsadmin());
		Assert.assertEquals("Y", user2.getIsactive());
		Assert.assertTrue(user2.getLoginfailure() > 0);
		System.out.println("Jerry Test Clear Login Failure:"+ user2.getLoginfailure());
		
		userDaoImpl.clearLoginFailure(con,user2);
		user2 = userDaoImpl.getUserByUsername(con,user2);
		Assert.assertTrue(user2.getLoginfailure() == 0);
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	public void testLoginViaUserNamePassword() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        userDaoImpl.adminAddUser(con,user);
        
        // Test
        boolean islogin = userDaoImpl.loginViaUserNamePassword(con,user);
		Assert.assertTrue(islogin);
		
		user.setPassword("wrongpassword");
        islogin = userDaoImpl.loginViaUserNamePassword(con,user);
		Assert.assertTrue(!islogin);
		
		User user2 = userDaoImpl.getUserByUsername(con,user);
		Assert.assertEquals("Y", user2.getIsadmin());
		Assert.assertEquals("Y", user2.getIsactive());
		System.out.println("Jerry Test Clear Login Failure:"+ user2.getLoginfailure());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	public void testChangePassword() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        userDaoImpl.adminAddUser(con,user);
        
        // Test
        User user2 = userDaoImpl.getUserByUsername(con,user);
        user2.setPassword("ralerry");
        boolean isPasswordChanged = userDaoImpl.changePassword(con,user2,user2.getPassword(), "newpassword", "newpassword");		
		
        System.out.println(user2.getId());
        Assert.assertTrue(isPasswordChanged);    
        
        User user3 = userDaoImpl.getUserByUsername(con,user);
        String password3 = user3.getPassword();
        String pin = userDaoImpl.getPinbyUserID(con, user3.getId());
		
		ResultSet result = null;
		String password = "";
		String sql = "select sha1('newpassword"+pin+"') as mypass from users;";
		System.out.println(sql);
		result = con.prepareStatement(sql).executeQuery();
		while (result.next()){
			password = result.getString("mypass");
		}
		Assert.assertEquals("password changed",password3,password);
		System.out.println(user2.getPassword());
		System.out.println(password);
		System.out.println("Jerry Test Clear Login Failure:"+ user2.getLoginfailure());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	public void testRegisterUser() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
        // Test 
        
        userDaoImpl.registerUser(con,user);
        
        User user2 = userDaoImpl.getUserByUsername(con,user);
        
        Assert.assertEquals("ralerry", user2.getUsername());
        Assert.assertEquals("N", user2.getIsadmin());
        
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	public void testAdminChangeUserPassword() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        
        userDaoImpl.adminAddUser(con,user);
        
        // Test
        User user2 = userDaoImpl.getUserByUsername(con,user);
        user2.setPassword("jerry");
        boolean isPasswordChanged = userDaoImpl.adminChangeUserPassword(con,user2, "jerry");		
		
        System.out.println(user2.getId());
        Assert.assertTrue(isPasswordChanged);    
        
        User user3 = userDaoImpl.getUserByUsername(con,user);
        String password3 = user3.getPassword();
        String pin = userDaoImpl.getPinbyUserID(con, user3.getId());
        
		Assert.assertEquals("Y", user2.getIsadmin());
		Assert.assertEquals("Y", user2.getIsactive());
		
		ResultSet result = null;
		String password = "";
		String sql = "select sha1('jerry"+pin+"') as mypass from users;";
		System.out.println(sql);
		result = con.prepareStatement(sql).executeQuery();
		while (result.next()){
			password = result.getString("mypass");
		}
		Assert.assertEquals("password changed",password3,password);
		System.out.println(user2.getPassword());
		System.out.println(password);
		System.out.println("Jerry Test Clear Login Failure:"+ user2.getLoginfailure());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}

	public void testAdminDeleteUser() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
		User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
        
        userDaoImpl.adminAddUser(con,user);
        userDaoImpl.adminAddUser(con,user0);
        
        List<User> users = userDaoImpl.listAllUsers(con);
        User user1 = users.get(0);
        Assert.assertEquals(2,users.size());
        
        userDaoImpl.adminDeleteUser(con,user1.getId());
        System.out.println(user1.getId());
        
        List<User> users1 = userDaoImpl.listAllUsers(con);
        Assert.assertEquals(1,users1.size());
		
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	public void  testToggleAdmin() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
    	User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
        
        userDaoImpl.adminAddUser(con,user);
        userDaoImpl.adminAddUser(con,user0);
        
        // Test        
        User user1 = userDaoImpl.getUserByUsername(con,user);        
        Assert.assertEquals("Y", user1.getIsadmin());
        userDaoImpl.toggleAdmin(con,user1);
        User user2 = userDaoImpl.getUserByUsername(con,user1); 
        Assert.assertEquals("N", user2.getIsadmin());
        
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	public void testToggleActive() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
    	User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
        
        userDaoImpl.adminAddUser(con,user);
        userDaoImpl.adminAddUser(con,user0);
        
        // Test        
        User user1 = userDaoImpl.getUserByUsername(con,user);        
        Assert.assertEquals("Y", user1.getIsactive());
        userDaoImpl.toggleActive(con,user1);
        User user2 = userDaoImpl.getUserByUsernameIncludeUnactive(con,user1); 
        Assert.assertEquals("N", user2.getIsactive());
        
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	public void testAdminAddUser() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("ralerry");
        user.setFirstname("ralerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("ralerry");
        user.setConfirmpassword("ralerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("ralerry");
        user.setNamej("ralerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);
        
    	User user0 = new User();
		user0.setId(9996L);
        user0.setEmpid(9996L);
        user0.setUsername("mala");
        user0.setFirstname("mala");
        user0.setLastname("mala");
        user0.setSex("female");
        user0.setPassword("mala");
        user0.setConfirmpassword("mala");
        user0.setIsadmin("Y");
        user0.setIsactive("Y");
        user0.setAddress("Road 4");
        user0.setAddress1("Road 4");
        user0.setNamec("mala");
        user0.setNamej("mala");
        user0.setPhone("9996");
        user0.setMobile("9996");
        user0.setLoginfailure(0);
        
        
        userDaoImpl.adminAddUser(con,user);
        
        List<User> users = userDaoImpl.listAllUsers(con);
        Assert.assertEquals(1, users.size());
        userDaoImpl.adminAddUser(con,user0);
        
        List<User> users0 = userDaoImpl.listAllUsers(con);
        Assert.assertEquals(2, users0.size());
        
        // Clean
        con.prepareStatement("delete from users;").executeUpdate();
		}
	}
	
	public void testGeneratePinNum() throws Exception{
		try(Connection con = DBConf.initDB()){
		System.out.println("JerryRandom:"+userDaoImpl.generatePinNum(con));
		}
	}
}
