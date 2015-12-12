package test.javaforever.clocksimplejee4.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.PrivilegeDao;
import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.daoimpl.PrivilegeDaoImpl;
import com.javaforever.clocksimplejee4.daoimpl.UserDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;

import junit.framework.Assert;

/**
 * PrivilegeDaoImplTest 
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
public class PrivilegeDaoImplTest extends DataSafeTestCase {

	private static PrivilegeDao privilegeDao = new PrivilegeDaoImpl();
	private static UserDao userDao = new UserDaoImpl();

	public static PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	public static void setPrivilegeDaoImpl(PrivilegeDao privilegeDao) {
		PrivilegeDaoImplTest.privilegeDao = privilegeDao;
	}

	public static UserDao getUserDao() {
		return PrivilegeDaoImplTest.userDao;
	}

	public static void setUserDaoImpl(UserDao userDao) {
		PrivilegeDaoImplTest.userDao = userDao;
	}
	    
	public void testCanAccess() throws Exception
	{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        
        getUserDao().adminAddUser(con,user);		
		// Test
        boolean canAccess = getPrivilegeDao().canAccess(con,user, "../jsp/index.jsp");
        Assert.assertTrue(canAccess);
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}


	public void testAdminAddPrivilege() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/userhomepage.jsp");
        
        
        getUserDao().adminAddUser(con,user);		
		// Test
        boolean canAccess = getPrivilegeDao().canAccess(con,user, "../jsp/index.jsp");
        Assert.assertTrue(canAccess);
        boolean addSuccess = getPrivilegeDao().adminAddPrivilege(con,privilege);
        Assert.assertTrue(addSuccess);
        
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	public void testAdminDeletePrivilege() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
	    // test    
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/userhomepage.jsp");
        boolean addSuccess = getPrivilegeDao().adminAddPrivilege(con,privilege);
        Assert.assertTrue(addSuccess);
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        Assert.assertTrue( 2 == getPrivilegeDao().getPrivilegeList(con).size());
        getPrivilegeDao().adminDeletePrivilege(con,priv);
        Assert.assertTrue( 1 == getPrivilegeDao().getPrivilegeList(con).size()); 
        
        Privilege priv2 = new Privilege();
        priv2.setUrl("../jsp/index.jsp");
        priv2=getPrivilegeDao().getPrivilegeByUrl(con,priv2);
        getPrivilegeDao().adminDeletePrivilege(con,priv2);
        Assert.assertTrue( 1 == getPrivilegeDao().getPrivilegeList(con).size()); 
        
		// Clean
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	
	public void testGetPrivilegeList() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/userhomepage.jsp");
         
        
        getUserDao().adminAddUser(con,user);	
        getPrivilegeDao().adminAddPrivilege(con,privilege);
		// Test
        List<Privilege> privilegeList = getPrivilegeDao().getPrivilegeList(con);
        Assert.assertEquals(2,privilegeList.size());
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        boolean deleteSuccess = getPrivilegeDao().adminDeletePrivilege(con,priv);
        Assert.assertTrue(deleteSuccess);
        privilegeList = getPrivilegeDao().getPrivilegeList(con);        
        Assert.assertEquals(1,privilegeList.size());
        
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	
	public void testGetPrivilegeByUrl() throws Exception {
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/userhomepage.jsp");
        
        
        getUserDao().adminAddUser(con,user);
        getPrivilegeDao().adminAddPrivilege(con,privilege);
        
		// Test
        List<Privilege> privilegeList = getPrivilegeDao().getPrivilegeList(con);
        Assert.assertEquals(2,privilegeList.size());
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        Assert.assertEquals("../jsp/userhomepage.jsp", priv.getUrl());
        boolean deleteSuccess = getPrivilegeDao().adminDeletePrivilege(con,priv);
        privilegeList = getPrivilegeDao().getPrivilegeList(con);
        Assert.assertTrue(deleteSuccess);
        Assert.assertEquals(1,privilegeList.size());
        
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	
	public void testAdminTogglePrivilegeCanDelete() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/userhomepage.jsp");
        
        
        getUserDao().adminAddUser(con,user);
        getPrivilegeDao().adminAddPrivilege(con,privilege);
        
		// Test
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        Assert.assertEquals("Y", priv.getCanDelete());
        boolean toggleSuccess = getPrivilegeDao().adminTogglePrivilegeCanDelete(con,priv);
        Assert.assertTrue(toggleSuccess);
        Privilege p = getPrivilegeDao().getPrivilegeByUrl(con,priv);
        Assert.assertEquals("N", p.getCanDelete());
        
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	
	public void testAdminTogglePrivilegeIsAdmin() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
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
        
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../controller/LoginController");
        
        
        getUserDao().adminAddUser(con,user);
        getPrivilegeDao().adminAddPrivilege(con,privilege);
        
		// Test
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        Assert.assertEquals("Y", priv.getIsadmin());
        boolean toggleSuccess = getPrivilegeDao().adminTogglePrivilegeIsAdmin(con,priv);
        Assert.assertTrue(toggleSuccess);
        Privilege p = getPrivilegeDao().getPrivilegeByUrl(con,priv);
        Assert.assertEquals("N", p.getIsadmin());
        
		// Clean
		con.prepareStatement("delete from users;").executeUpdate();
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
	
	public void testAdminEditPrivilege() throws Exception{
		// prepare data
		try(Connection con = DBConf.initDB()){
		con.prepareStatement("delete from privilege;").executeUpdate();
		con.prepareStatement("insert into privilege (isadmin, candelete, url) values ('Y','N','../jsp/index.jsp');").executeUpdate();
		
        Privilege privilege = new Privilege();
        privilege.setCanDelete("Y");
        privilege.setIsadmin("Y");
        privilege.setUrl("../jsp/index.jsp");
        
        
        
		// Test
        Privilege priv = getPrivilegeDao().getPrivilegeByUrl(con,privilege);
        Assert.assertEquals("Y", priv.getIsadmin());
        Assert.assertEquals("../jsp/index.jsp", priv.getUrl());
        priv.setIsadmin("N");
        priv.setUrl("../jsp/index1.jsp");
        getPrivilegeDao().adminEditPrivilege(con,priv);
        List<Privilege> privs = getPrivilegeDao().getPrivilegeList(con);
        System.out.println("Jerry Debug::"+priv.getIsadmin()+"::"+priv.getId());
        Assert.assertTrue(1==privs.size());
        Privilege priv1 = getPrivilegeDao().getPrivilegeList(con).get(0);
        Assert.assertTrue(priv.getId()>0);
        Assert.assertEquals("N",priv1.getIsadmin());
        Assert.assertEquals("../jsp/index1.jsp",priv1.getUrl());
        
		// Clean
		con.prepareStatement("delete from privilege;").executeUpdate();
		}
	}
}
