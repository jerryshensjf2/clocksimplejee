package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.LeaveLeft;

public interface LeaveLeftService {
	public boolean createLeaveLeft(LeaveLeft leaveLeft) throws Exception;
	public boolean updateLeaveLeft(LeaveLeft leaveLeft) throws Exception;
	public boolean deleteLeaveLeft(long id) throws Exception;
	public List<LeaveLeft> listAllLeaveLefts() throws Exception; 
	public LeaveLeft findLeaveLeftById(long id) throws Exception;
	public List<LeaveLeft> getLeaveLeftListByEmpid(long empid) throws Exception;
}
