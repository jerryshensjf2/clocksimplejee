package com.javaforever.clocksimplejee4.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Fine;

public interface FineDao {
	public boolean createFine(Connection connection,Fine fine) throws Exception;
	public boolean updateFine(Connection connection,Fine fine) throws Exception;
	public boolean deleteFine(Connection connection,long id) throws Exception;
	public boolean addFineBalance(Connection connection,Fine fine, BigDecimal quantity) throws Exception;
	public List<Fine> listAllFine(Connection connection) throws Exception; 
	public Fine findFineById(Connection connection,long id) throws Exception;
	public Fine findFineByReason(Connection connection,String reason) throws Exception;
	public List<Fine> getFineListByEmpid(Connection connection,long empid) throws Exception;
}
