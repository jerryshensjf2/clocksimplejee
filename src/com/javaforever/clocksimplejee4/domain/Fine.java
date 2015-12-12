package com.javaforever.clocksimplejee4.domain;

import java.math.BigDecimal;

public class Fine {

	private long id;
	private long userId;
	private long empId;
	private String reason;
	private BigDecimal fineBalance;
	private String description;
	
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
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BigDecimal getFineBalance() {
		return fineBalance;
	}
	public void setFineBalance(BigDecimal fineBalance) {
		this.fineBalance = fineBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
