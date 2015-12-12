package com.javaforever.clocksimplejee4.mockdaoimpl;

import java.sql.Connection;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;

public class UserMockDaoImpl implements UserDao {
    private Mockery context = new JUnit4Mockery();

    private UserDao userDao;
    
    /**
     * Test method for
     * {@link org.hook.jmock.firstcase.HelloServiceImpl#sayHelloToSomebody(java.lang.String)}.
     */
    @Override
    public User getUserByUsername(Connection connection,User user0) throws Exception {
    	// set up
        userDao = context.mock(UserDao.class);
       
        final User user = new User();
        user.setUsername("jerry");

        // expectations
        context.checking(new Expectations() {
            {
            	Connection connection = DBConf.initDB();            	
                oneOf(userDao).getUserByUsername(connection,user);
                will(returnValue(user));
            }
        });
        // execute
        String username = userDao.getUserByUsername(connection,user).getUsername();
        
        // verify
        context.assertIsSatisfied();
        Assert.assertEquals("jerry", username);
        System.out.println(username);
        
        return user;
    }

	@Override
	public void addLoginFailure(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLoginFailureViaUsername(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean adminAddUser(Connection connection,User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adminChangeUserPassword(Connection connection,User user,
			String confirmnewpassword) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adminDeleteUser(Connection connection,long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(Connection connection,User user, String password,String newpassword,
			String confirmnewpassword) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearLoginFailure(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean editUser(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getPinViaId(Connection connection,long userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPinbyUserID(Connection connection,long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Connection connection,long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginViaUserNamePassword(Connection connection,User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(Connection connection,User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleActive(Connection connection,User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleAdmin(Connection connection,User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uniquepin(Connection connection,String pin) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByEmpid(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsernameIncludeUnactive(Connection connection,User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generatePinNum(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(Connection connection, long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listAllUsers(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}