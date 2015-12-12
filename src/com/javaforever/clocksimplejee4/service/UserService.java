package com.javaforever.clocksimplejee4.service;

import com.javaforever.clocksimplejee4.domain.User;

import java.util.List;

public interface UserService {
	public List<User> listAllUsers() throws Exception;	
	public User findUserById(long id) throws Exception;
	public User getUser( long id) throws Exception;
	public boolean editUser(User user) throws Exception;
	public String getPinViaId(long userid) throws Exception;
	public boolean login(User user) throws Exception;
	public boolean loginViaUserNamePassword(User user) throws Exception;
	public boolean changePassword(User user,String oldpassword, String newpassword,String confirmnewpassword) throws Exception;
	public boolean registerUser(User user) throws Exception;	
	public boolean adminChangeUserPassword(User user,String confirmnewpassword) throws Exception;
	public boolean adminDeleteUser(long id) throws Exception;	
	public boolean toggleAdmin(User user) throws Exception;	
	public boolean toggleActive(User user) throws Exception;	
	public boolean adminAddUser(User user) throws Exception;	
	public User getUserByUsername(User user) throws Exception;	
	public User getUserByEmpid(User user) throws Exception;
}
