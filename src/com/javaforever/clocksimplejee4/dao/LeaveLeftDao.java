package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveLeft;

public interface LeaveLeftDao {
	public boolean createLeaveLeft(Connection connection,LeaveLeft leaveLeft) throws Exception;
	public boolean updateLeaveLeft(Connection connection,LeaveLeft leaveLeft) throws Exception;
	public boolean deleteLeaveLeft(Connection connection,long id) throws Exception;
	public List<LeaveLeft> listAllLeaveLefts(Connection connection) throws Exception; 
	public LeaveLeft findLeaveLeftById(Connection connection,long id) throws Exception;
	public List<LeaveLeft> getLeaveLeftListByEmpid(Connection connection,long empid) throws Exception;
}
