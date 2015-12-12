package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.User;

public interface UserDao {
	public User findUserById(Connection connection,long id) throws Exception;	
	public List<User> listAllUsers(Connection connection) throws Exception;
	public User getUser(Connection connection,long id) throws Exception;
	public boolean editUser(Connection connection,User user) throws Exception;
	public String getPinViaId(Connection connection,long userid) throws Exception;
	public boolean login(Connection connection,User user) throws Exception;
	public void addLoginFailure(Connection connection,User user) throws Exception;
	public void addLoginFailureViaUsername(Connection connection,User user) throws Exception;
	public void clearLoginFailure(Connection connection,User user) throws Exception;
	public boolean loginViaUserNamePassword(Connection connection,User user) throws Exception;
	public boolean changePassword(Connection connection,User user, String oldpasword, String newpassword,String confirmnewpassword) throws Exception;
	public boolean registerUser(Connection connection,User user) throws Exception;	
	public boolean adminChangeUserPassword(Connection connection,User user,String confirmnewpassword) throws Exception;
	public boolean uniquepin(Connection connection,String pin) throws Exception;	
	public boolean adminDeleteUser(Connection connection,long id) throws Exception;	
	public boolean toggleAdmin(Connection connection,User user) throws Exception;	
	public boolean toggleActive(Connection connection,User user) throws Exception;	
	public boolean adminAddUser(Connection connection,User user) throws Exception;	
	public String getPinbyUserID(Connection connection,long id) throws Exception;	
	public User getUserByUsername(Connection connection,User user) throws Exception;	
	public User getUserByUsernameIncludeUnactive(Connection connection,User user) throws Exception;	
	public User getUserByEmpid(Connection connection,User user) throws Exception;
	public String generatePinNum(Connection connection) throws Exception;
}
