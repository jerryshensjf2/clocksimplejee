package com.javaforever.clocksimplejee4.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Bonus;

public interface BonusDao {
	public boolean createBonus(Connection connection,Bonus bonus) throws Exception;
	public boolean updateBonus(Connection connection,Bonus bonus) throws Exception;
	public boolean deleteBonus(Connection connection,long id) throws Exception;
	public boolean addBonusBalance(Connection connection,Bonus bonus, BigDecimal quantity) throws Exception;
	public List<Bonus> listAllBonus(Connection connection) throws Exception; 
	public Bonus findBonusById(Connection connection,long id) throws Exception;
	public Bonus findBonusByReason(Connection connection,String reason) throws Exception;
	public List<Bonus> getBonusListByEmpid(Connection connection,long empid) throws Exception;
}
