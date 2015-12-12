package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveLimit;

public interface LeaveLimitDao {
	public boolean createLeaveLimit(Connection connection,LeaveLimit leaveLimit) throws Exception;
	public boolean updateLeaveLimit(Connection connection,LeaveLimit leaveLimit) throws Exception;
	public boolean deleteLeaveLimit(Connection connection,long id) throws Exception;
	public List<LeaveLimit> listAllLeaveLimits(Connection connection) throws Exception; 
	public LeaveLimit findLeaveLimitById(Connection connection,long id) throws Exception;
	public List<LeaveLimit> getLeaveLimitListByEmployeeTypeId(Connection connection,long employeetypeid) throws Exception;
}
