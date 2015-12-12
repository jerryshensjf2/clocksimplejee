package com.javaforever.clocksimplejee4.serviceimpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.LeaveTypeDao;
import com.javaforever.clocksimplejee4.daoimpl.LeaveTypeDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveType;
import com.javaforever.clocksimplejee4.service.LeaveTypeService;


public class LeaveTypeServiceImpl implements LeaveTypeService{
	private static LeaveTypeDao instance = new LeaveTypeDaoImpl();

	@Override
	public boolean createLeaveType(LeaveType leaveType) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createLeaveType(connection, leaveType);
		}
	}

	@Override
	public boolean deleteLeaveType(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteLeaveType(connection,id);
		}
	}

	@Override
	public LeaveType findLeaveTypeById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findLeaveTypeById(connection,id);
		}
	}

	@Override
	public LeaveType findLeaveTypeByLeaveTypeName(String leaveTypeName)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findLeaveTypeByLeaveTypeName(connection,leaveTypeName);
		}
	}

	@Override
	public List<LeaveType> listAllLeaveTypes() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllLeaveTypes(connection);
		}
	}

	@Override
	public boolean updateLeaveType(LeaveType leaveType)	throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateLeaveType(connection,leaveType);
		}
	}
}
