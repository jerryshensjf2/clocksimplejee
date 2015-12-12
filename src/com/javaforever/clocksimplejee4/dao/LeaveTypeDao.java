package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveType;

public interface LeaveTypeDao {
	public boolean createLeaveType(Connection connection,LeaveType leaveType) throws Exception;
	public boolean updateLeaveType(Connection connection,LeaveType leaveType) throws Exception;
	public boolean deleteLeaveType(Connection connection,long id) throws Exception;
	public List<LeaveType> listAllLeaveTypes(Connection connection) throws Exception; 
	public LeaveType findLeaveTypeById(Connection connection,long id) throws Exception;
	public LeaveType findLeaveTypeByLeaveTypeName(Connection connection,String leaveTypeName) throws Exception;
}
