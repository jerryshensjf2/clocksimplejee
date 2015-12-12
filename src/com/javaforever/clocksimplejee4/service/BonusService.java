package com.javaforever.clocksimplejee4.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.javaforever.clocksimplejee4.domain.Bonus;
import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;

public interface BonusService {
	public boolean createBonus(Bonus bonus) throws Exception;
	public boolean updateBonus(Bonus bonus) throws Exception;
	public boolean deleteBonus(long id) throws Exception;
	public boolean addBonusBalance(Bonus bonus, BigDecimal quantity) throws Exception;
	public List<Bonus> listAllBonus() throws Exception; 
	public Bonus findBonusById(long id) throws Exception;
	public Bonus findBonusByReason(String reason) throws Exception;
	public List<Bonus> getBonusListByEmpid(long empid) throws Exception;
}
