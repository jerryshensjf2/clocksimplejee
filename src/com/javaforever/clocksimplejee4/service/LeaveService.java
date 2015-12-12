package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.Leave;

public interface LeaveService {
	public boolean createLeave(Leave leave) throws Exception;
	public boolean updateLeave(Leave leave) throws Exception;
	public boolean deleteLeave(long id) throws Exception;
	public List<Leave> listAllLeaves() throws Exception; 
	public Leave findLeaveById(long id) throws Exception;
	public List<Leave> getLeaveListByEmpid(long empid) throws Exception;
}
