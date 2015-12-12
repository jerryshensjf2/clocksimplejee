package com.javaforever.clocksimplejee4.serviceimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.BonusDao;
import com.javaforever.clocksimplejee4.daoimpl.BonusDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Bonus;
import com.javaforever.clocksimplejee4.service.BonusService;

public class BonusServiceImpl implements BonusService{
	private static BonusDao instance = new BonusDaoImpl();

	@Override
	public boolean addBonusBalance(Bonus bonus, BigDecimal quantity)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.addBonusBalance(connection,bonus, quantity);
		}
	}

	@Override
	public boolean createBonus(Bonus bonus) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createBonus(connection,bonus);
		}
	}

	@Override
	public boolean deleteBonus(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteBonus(connection,id);
		}
	}

	@Override
	public Bonus findBonusById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findBonusById(connection,id);
		}
	}

	@Override
	public Bonus findBonusByReason(String reason) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findBonusByReason(connection,reason);
		}
	}

	@Override
	public List<Bonus> getBonusListByEmpid(long empid) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getBonusListByEmpid(connection,empid);
		}
	}

	@Override
	public List<Bonus> listAllBonus() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllBonus(connection);
		}
	}

	@Override
	public boolean updateBonus(Bonus bonus)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.updateBonus(connection,bonus);
		}
	}
	
}
