package com.javaforever.clocksimplejee4.dao;

import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.EmployeeType;

public interface EmployeeTypeDao {
	public boolean createEmployeeType(Connection connection,EmployeeType employeeType) throws Exception;
	public boolean updateEmployeeType(Connection connection,EmployeeType employeeType) throws Exception;
	public boolean deleteEmployeeType(Connection connection,long id) throws Exception;
	public List<EmployeeType> listAllEmployeeTypes(Connection connection) throws Exception; 
	public EmployeeType findEmployeeTypeById(Connection connection,long id) throws Exception;
	public EmployeeType findEmployeeTypeByEmployeeTypeName(Connection connection,String employeeTypeName) throws Exception;
}
