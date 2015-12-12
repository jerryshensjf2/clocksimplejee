package com.javaforever.clocksimplejee4.serviceimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.FineDao;
import com.javaforever.clocksimplejee4.daoimpl.FineDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Fine;
import com.javaforever.clocksimplejee4.service.FineService;


public class FineServiceImpl implements FineService{
	private static FineDao instance = new FineDaoImpl();

	@Override
	public boolean addFineBalance(Fine fine, BigDecimal quantity)
			throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.addFineBalance(connection,fine, quantity);
		}
	}

	@Override
	public boolean createFine(Fine fine) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.createFine(connection,fine);
		}
	}

	@Override
	public boolean deleteFine(long id) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.deleteFine(connection,id);
		}
	}

	@Override
	public Fine findFineById(long id) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.findFineById(connection,id);
		}
	}

	@Override
	public Fine findFineByReason(String reason) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.findFineByReason(connection,reason);
		}
	}

	@Override
	public List<Fine> getFineListByEmpid(long empid) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.getFineListByEmpid(connection,empid);
		}
	}

	@Override
	public List<Fine> listAllFine() throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.listAllFine(connection);
		}
	}

	@Override
	public boolean updateFine(Fine fine) throws Exception {
		try (Connection connection =DBConf.initDB()){
			return instance.updateFine(connection,fine);
		}
	}

}
