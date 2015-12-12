package com.javaforever.clocksimplejee4.serviceimpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.EmployeeTypeDao;
import com.javaforever.clocksimplejee4.daoimpl.EmployeeTypeDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.EmployeeType;
import com.javaforever.clocksimplejee4.service.EmployeeTypeService;


public class EmployeeTypeServiceImpl implements EmployeeTypeService{
	private static EmployeeTypeDao instance = new EmployeeTypeDaoImpl();

	@Override
	public boolean createEmployeeType(EmployeeType employeeType)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createEmployeeType(connection,employeeType);
		}
	}

	@Override
	public boolean deleteEmployeeType(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteEmployeeType(connection,id);
		}
	}

	@Override
	public EmployeeType findEmployeeTypeByEmployeeTypeName(
			String employeeTypeName) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findEmployeeTypeByEmployeeTypeName(connection,employeeTypeName);
		}
	}

	@Override
	public EmployeeType findEmployeeTypeById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findEmployeeTypeById(connection,id);
		}
	}

	@Override
	public List<EmployeeType> listAllEmployeeTypes() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllEmployeeTypes(connection);
		}
	}

	@Override
	public boolean updateEmployeeType(EmployeeType employeeType) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateEmployeeType(connection,employeeType);
		}
	}


}
