package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;

public interface PrivilegeService {
	public boolean canAccess(User user,String url) throws Exception;
	public boolean adminAddPrivilege(Privilege privilege) throws Exception;
	public boolean adminDeletePrivilege(Privilege privilege) throws Exception;
	public boolean adminTogglePrivilegeCanDelete(Privilege privilege) throws Exception;
	public boolean adminTogglePrivilegeIsAdmin(Privilege privilege) throws Exception;
	public boolean adminEditPrivilege(Privilege priv) throws Exception;
	public List<Privilege> getPrivilegeList() throws Exception; 
	public Privilege getPrivilegeByUrl(Privilege privilege) throws Exception;
}
