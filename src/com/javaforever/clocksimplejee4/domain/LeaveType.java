package com.javaforever.clocksimplejee4.domain;

import java.math.BigDecimal;

public class LeaveType {
	
	private long id;
	private String leaveTypeName;
	private BigDecimal unitFine;
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public BigDecimal getUnitFine() {
		return unitFine;
	}
	public void setUnitFine(BigDecimal unitFine) {
		this.unitFine = unitFine;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
