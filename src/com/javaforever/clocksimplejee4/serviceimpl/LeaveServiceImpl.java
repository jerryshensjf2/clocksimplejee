package com.javaforever.clocksimplejee4.serviceimpl;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Leave;
import com.javaforever.clocksimplejee4.service.LeaveService;


public class LeaveServiceImpl implements LeaveService{
	private static LeaveDao instance = new LeaveDaoImpl();

	@Override
	public boolean createLeave(Leave leave) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createLeave(connection,leave);
		}
	}

	@Override
	public boolean deleteLeave(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteLeave(connection,id);
		}
	}

	@Override
	public Leave findLeaveById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findLeaveById(connection,id);
		}
	}

	@Override
	public List<Leave> getLeaveListByEmpid(long empid) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getLeaveListByEmpid(connection,empid);
		}
	}

	@Override
	public List<Leave> listAllLeaves() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllLeaves(connection);
		}
	}

	@Override
	public boolean updateLeave(Leave leave)	throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateLeave(connection,leave);
		}
	}

}
