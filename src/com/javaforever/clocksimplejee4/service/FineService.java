package com.javaforever.clocksimplejee4.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Fine;

public interface FineService {
	public boolean createFine(Fine fine) throws Exception;
	public boolean updateFine(Fine fine) throws Exception;
	public boolean deleteFine(long id) throws Exception;
	public boolean addFineBalance(Fine fine, BigDecimal quantity) throws Exception;
	public List<Fine> listAllFine() throws Exception; 
	public Fine findFineById(long id) throws Exception;
	public Fine findFineByReason(String reason) throws Exception;
	public List<Fine> getFineListByEmpid(long empid) throws Exception;
}
