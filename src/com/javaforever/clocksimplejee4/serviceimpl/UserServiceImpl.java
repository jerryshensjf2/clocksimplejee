package com.javaforever.clocksimplejee4.serviceimpl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.daoimpl.UserDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;

public class UserServiceImpl implements UserService {
	static UserDao userDao = new UserDaoImpl();
	
	@Override
	public List<User> listAllUsers() throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.listAllUsers(connection);
		}
	}
	
	@Override
	public User findUserById(long id) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.findUserById(connection,id);
		}
	}

	@Override
	public User getUser(long id) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.getUser(connection,id);
		}
	}

	@Override
	public boolean editUser(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.editUser(connection,user);
		}
	}
	
	@Override
	public String getPinViaId(long id) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.getPinbyUserID(connection,id);
		}
	}

	@Override
	public boolean login(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.login(connection,user);
		}
	}	
	
	public void addLoginFailure(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			userDao.login(connection,user);
		}
	}

	public void addLoginFailureViaUsername(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			userDao.addLoginFailure(connection,user);
		}
	}

	public void clearLoginFailure(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			userDao.clearLoginFailure(connection,user);
		}
	}

	@Override
	public boolean loginViaUserNamePassword(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.loginViaUserNamePassword(connection,user);
		}
	}

	@Override
	public boolean changePassword(User user, String oldpassword,String newpassword,String confirmnewpassword) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.changePassword(connection,user, oldpassword,newpassword, confirmnewpassword);
		}
	}

	@Override
	public boolean registerUser(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.registerUser(connection,user);
		}
	}
	
	@Override
	public boolean adminChangeUserPassword(User user,String confirmnewpassword) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.adminChangeUserPassword(connection,user, confirmnewpassword);
		}
	}

	@Override
	public boolean adminDeleteUser(long id) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.adminDeleteUser(connection,id);
		}
	}
	
	@Override
	public boolean toggleAdmin(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.toggleAdmin(connection,user);
		}
	}
	
	@Override
	public boolean toggleActive(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.toggleActive(connection,user);
		}
	}
	
	@Override
	public boolean adminAddUser(User user) throws Exception
	{
		try(Connection connection = DBConf.initDB()){
			return userDao.adminAddUser(connection,user);
		}
	}

	@Override
	public User getUserByUsername(User user) throws Exception {
		try(Connection connection = DBConf.initDB()){
			return userDao.getUserByUsername(connection,user);
		}
	}
	
	@Override
	public User getUserByEmpid(User user) throws Exception {
		try(Connection connection = DBConf.initDB()){
			return userDao.getUserByEmpid(connection,user);
		}
	}
}