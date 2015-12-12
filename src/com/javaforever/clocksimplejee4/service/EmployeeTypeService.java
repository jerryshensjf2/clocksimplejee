package com.javaforever.clocksimplejee4.service;

import java.util.List;

import com.javaforever.clocksimplejee4.domain.EmployeeType;

public interface EmployeeTypeService {
	public boolean createEmployeeType(EmployeeType employeeType) throws Exception;
	public boolean updateEmployeeType(EmployeeType employeeType) throws Exception;
	public boolean deleteEmployeeType(long id) throws Exception;
	public List<EmployeeType> listAllEmployeeTypes() throws Exception; 
	public EmployeeType findEmployeeTypeById(long id) throws Exception;
	public EmployeeType findEmployeeTypeByEmployeeTypeName(String employeeTypeName) throws Exception;
}
