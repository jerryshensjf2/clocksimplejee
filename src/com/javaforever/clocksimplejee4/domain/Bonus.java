package com.javaforever.clocksimplejee4.domain;

import java.math.BigDecimal;

public class Bonus {
	private long id = -1;
	private long userId = -1;
	private long empid = -1;
	private String reason ="";
	private BigDecimal bonusBalance = new BigDecimal("0");
	private String description = "";
	
	public Bonus(){
		id = -1L;
		userId = -1L;
		empid = -1L;
		reason = "";
		bonusBalance = new BigDecimal("0");
		description = "";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getEmpId() {
		return empid;
	}
	public void setEmpId(long empid) {
		this.empid = empid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BigDecimal getBonusBalance() {
		return bonusBalance;
	}
	public void setBonusBalance(BigDecimal bonusBalance) {
		this.bonusBalance = bonusBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
