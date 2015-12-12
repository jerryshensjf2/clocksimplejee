package com.javaforever.clocksimplejee4.mock.dao;

import java.sql.Connection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;

public class ClockDaoMock extends TestCase {
    private Mockery context = new JUnit4Mockery();

    private UserDao userDao;
    
    @Before
    public void setUp() throws Exception {
        // set up
        userDao = context.mock(UserDao.class);
    }

    /**
     * Test method for
     * {@link org.hook.jmock.firstcase.HelloServiceImpl#sayHelloToSomebody(java.lang.String)}.
     */
    @Test
    public void testGetUserByUsername() throws Exception {
    	
        final User user = new User();
        user.setUsername("jerry");

        // expectations
        context.checking(new Expectations() {
            {
            	Connection connection = (java.sql.Connection)DBConf.initDB();
                oneOf(userDao).getUserByUsername(connection,user);
                will(returnValue(user));
            }
        });
        // execute
        Connection connection = DBConf.initDB();
        String username = userDao.getUserByUsername((java.sql.Connection)connection,user).getUsername();
        
        // verify
        context.assertIsSatisfied();
        Assert.assertEquals("jerry", username);
        System.out.println(username);
    }
}



