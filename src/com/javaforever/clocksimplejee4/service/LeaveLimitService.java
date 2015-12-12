package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveLimit;

public interface LeaveLimitService {
	public boolean createLeaveLimit(LeaveLimit leaveLimit) throws Exception;
	public boolean updateLeaveLimit(LeaveLimit leaveLimit) throws Exception;
	public boolean deleteLeaveLimit(long id) throws Exception;
	public List<LeaveLimit> listAllLeaveLimits() throws Exception; 
	public LeaveLimit findLeaveLimitById(long id) throws Exception;
	public List<LeaveLimit> getLeaveLimitListByEmployeeTypeId(long employeetypeid) throws Exception;
}
