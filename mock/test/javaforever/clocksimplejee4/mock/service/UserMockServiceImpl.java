package test.javaforever.clocksimplejee4.mock.service;

import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;

public class UserMockServiceImpl implements UserService {
    private Mockery context = new JUnit4Mockery();

    private UserService userService;
    
    /**
     * Test method for
     * {@link org.hook.jmock.firstcase.HelloServiceImpl#sayHelloToSomebody(java.lang.String)}.
     */
    @Override
    public User getUserByUsername(User user0) throws Exception {
    	// set up
        userService = context.mock(UserService.class);
       
        final User user = new User();
        user.setUsername("jerry");

        // expectations
        context.checking(new Expectations() {
            {
         	
                oneOf(userService).getUserByUsername(user);
                will(returnValue(user));
            }
        });
        // execute
        String username = userService.getUserByUsername(user).getUsername();
        
        // verify
        context.assertIsSatisfied();
        Assert.assertEquals("jerry", username);
        System.out.println(username);
        
        return user;
    }


	@Override
	public boolean adminAddUser(User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adminChangeUserPassword(User user,
			String confirmnewpassword) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adminDeleteUser(long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(User user, String password,String newpassword,
			String confirmnewpassword) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean editUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getPinViaId(long userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginViaUserNamePassword(User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleActive(User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleAdmin(User user)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByEmpid(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> listAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User findUserById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}