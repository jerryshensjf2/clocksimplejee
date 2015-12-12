package com.javaforever.clocksimplejee4.serviceimpl;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveLeftDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveLeftDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLeft;
import com.javaforever.clocksimplejee4.service.LeaveLeftService;


public class LeaveLeftServiceImpl implements LeaveLeftService{
	private static LeaveLeftDao instance = new LeaveLeftDaoImpl();

	@Override
	public boolean createLeaveLeft(LeaveLeft leaveLeft) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createLeaveLeft(connection,leaveLeft);
		}
	}

	@Override
	public boolean deleteLeaveLeft(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteLeaveLeft(connection,id);
		}
	}

	@Override
	public LeaveLeft findLeaveLeftById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findLeaveLeftById(connection,id);
		}
	}

	@Override
	public List<LeaveLeft> getLeaveLeftListByEmpid(long empid)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getLeaveLeftListByEmpid(connection, empid);
		}
	}

	@Override
	public List<LeaveLeft> listAllLeaveLefts() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllLeaveLefts(connection);
		}
	}

	@Override
	public boolean updateLeaveLeft(LeaveLeft leaveLeft) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateLeaveLeft(connection,leaveLeft);
		}
	}
}
