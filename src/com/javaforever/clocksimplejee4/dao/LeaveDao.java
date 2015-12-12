package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Leave;

public interface LeaveDao {
	public boolean createLeave(Connection connection,Leave leave) throws Exception;
	public boolean updateLeave(Connection connection,Leave leave) throws Exception;
	public boolean deleteLeave(Connection connection,long id) throws Exception;
	public List<Leave> listAllLeaves(Connection connection) throws Exception; 
	public Leave findLeaveById(Connection connection,long id) throws Exception;
	public List<Leave> getLeaveListByEmpid(Connection connection,long empid) throws Exception;
}
