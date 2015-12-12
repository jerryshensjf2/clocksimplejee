package test.javaforever.clocksimplejee4.mock.service;

import java.sql.Connection;

import junit.framework.Assert;
import test.javaforever.clocksimplejee4.test.DataSafeTestCase;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;

public class UserServiceMockTest extends DataSafeTestCase {

	private static UserService userService = new UserMockServiceImpl();
	private static String token = "10898138";
     
	public void testGetUserInfo() throws Exception
	{
	}

	public void testGetUser() throws Exception
	{		
		User user = new User();
		user.setId(9999L);
        user.setEmpid(9999L);
        user.setUsername("jerry");
        user.setFirstname("jerry");
        user.setLastname("shen");
        user.setSex("male");
        user.setPassword("jerry");
        user.setConfirmpassword("jerry");
        user.setIsadmin("Y");
        user.setIsactive("Y");
        user.setAddress("Road 1");
        user.setAddress1("Road 2");
        user.setNamec("jerry");
        user.setNamej("jerry");
        user.setPhone("9999");
        user.setMobile("9998");
        user.setLoginfailure(0);	
        userService.adminAddUser(user);	
        
        // Test begin
        User user0 = userService.getUserByUsername(user);
        Assert.assertTrue("jerry".equals(user0.getUsername()));  
	}

	public void testEditUser() throws Exception
	{
	}

	public void testGetPinViaId() throws Exception
	{
	}

	public void testLogin() throws Exception 
	{	
	}

	public void testAddLoginFailure() throws Exception
	{
	}

	public void testAddLoginFailureViaUsername() throws Exception 
	{
	}

	public void testAddClearLoginFailure() throws Exception
	{
	}

	public void testLoginViaUserNamePassword() throws Exception
	{
	}

	public void testChangePassword() throws Exception
	{
	}

	public void testRegisterUser() throws Exception
	{
	}
	
	public void testAdminChangeUserPassword() throws Exception
	{
	}

	public void testGeneratePinNum() throws Exception
	{
	}

	public void testUniquepin() throws Exception
	{
	}
	
	public void testAdminDeleteUser() throws Exception
	{
	}
	
	public void  testToggleAdmin() throws Exception
	{
	}
	
	public void testToggleActive() throws Exception
	{
	}
	
	public void testAdminAddUser() throws Exception
	{
	}
	
	public void testGetPinbyUserID() throws Exception
	{
	}

}
