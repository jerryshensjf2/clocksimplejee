package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveType;

public interface LeaveTypeService {
	public boolean createLeaveType(LeaveType leaveType) throws Exception;
	public boolean updateLeaveType(LeaveType leaveType) throws Exception;
	public boolean deleteLeaveType(long id) throws Exception;
	public List<LeaveType> listAllLeaveTypes() throws Exception; 
	public LeaveType findLeaveTypeById(long id) throws Exception;
	public LeaveType findLeaveTypeByLeaveTypeName(String leaveTypeName) throws Exception;
}
