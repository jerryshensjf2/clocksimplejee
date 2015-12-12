package com.javaforever.clocksimplejee4.serviceimpl;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.PrivilegeDao;
import com.javaforever.clocksimplejee4.daoimpl.PrivilegeDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService{
	private static PrivilegeDao privDao = new PrivilegeDaoImpl();
	
	public boolean canAccess(User user,String url) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.canAccess(connection,user, url);
		}
	}
	public boolean adminAddPrivilege(Privilege privilege) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.adminAddPrivilege(connection,privilege);
		}
	}
	public boolean adminDeletePrivilege(Privilege privilege) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.adminDeletePrivilege(connection,privilege);
		}
	}
	public boolean adminTogglePrivilegeCanDelete(Privilege privilege) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.adminTogglePrivilegeCanDelete(connection,privilege);
		}
	}
	public boolean adminTogglePrivilegeIsAdmin(Privilege privilege) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.adminTogglePrivilegeIsAdmin(connection,privilege);
		}
	}
	public List<Privilege> getPrivilegeList() throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.getPrivilegeList(connection);
		}
	}
	public Privilege getPrivilegeByUrl(Privilege privilege) throws Exception{
		try (Connection connection = DBConf.initDB()){
			return privDao.getPrivilegeByUrl(connection,privilege);
		}
	}
	public boolean adminEditPrivilege(Privilege priv) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return privDao.adminEditPrivilege(connection,priv);
		}
	}
}
