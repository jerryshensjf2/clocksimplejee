package com.javaforever.clocksimplejee4.serviceimpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveLimitDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveLimitDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLimit;
import com.javaforever.clocksimplejee4.service.LeaveLimitService;


public class LeaveLimitServiceImpl implements LeaveLimitService{
	private static LeaveLimitDao instance = new LeaveLimitDaoImpl();

	@Override
	public boolean createLeaveLimit(LeaveLimit leaveLimit) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createLeaveLimit(connection,leaveLimit);
		}
	}

	@Override
	public boolean deleteLeaveLimit(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteLeaveLimit(connection,id);
		}
	}

	@Override
	public LeaveLimit findLeaveLimitById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findLeaveLimitById(connection,id);
		}
	}

	@Override
	public List<LeaveLimit> getLeaveLimitListByEmployeeTypeId(long employeetypeid)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getLeaveLimitListByEmployeeTypeId(connection,employeetypeid);
		}
	}

	@Override
	public List<LeaveLimit> listAllLeaveLimits() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllLeaveLimits(connection);
		}
	}

	@Override
	public boolean updateLeaveLimit(LeaveLimit leaveLimit) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateLeaveLimit(connection,leaveLimit);
		}
	}

}
