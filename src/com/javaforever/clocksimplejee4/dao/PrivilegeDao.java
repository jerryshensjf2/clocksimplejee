package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;

public interface PrivilegeDao {
	public boolean canAccess(Connection connection,User user,String url) throws Exception;
	public boolean adminAddPrivilege(Connection connection,Privilege privilege) throws Exception;
	public boolean adminDeletePrivilege(Connection connection,Privilege privilege) throws Exception;
	public boolean adminTogglePrivilegeCanDelete(Connection connection,Privilege privilege) throws Exception;
	public boolean adminTogglePrivilegeIsAdmin(Connection connection,Privilege privilege) throws Exception;
	public boolean adminEditPrivilege(Connection connection,Privilege priv) throws Exception;
	public List<Privilege> getPrivilegeList(Connection connection) throws Exception; 
	public Privilege getPrivilegeByUrl(Connection connection,Privilege privilege) throws Exception;
}
