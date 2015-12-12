package com.javaforever.clocksimplejee4.domain;

public class LeaveLimit {
	private long id;
	private long employeeTypeId;
	private int annualLeaveLimit;
	private int sickLeaveLimit;
	private int privateLeaveLimit;
	private int otherLeaveLimit;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(long employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public int getAnnualLeaveLimit() {
		return annualLeaveLimit;
	}
	public void setAnnualLeaveLimit(int annualLeaveLimit) {
		this.annualLeaveLimit = annualLeaveLimit;
	}
	public int getSickLeaveLimit() {
		return sickLeaveLimit;
	}
	public void setSickLeaveLimit(int sickLeaveLimit) {
		this.sickLeaveLimit = sickLeaveLimit;
	}
	public int getPrivateLeaveLimit() {
		return privateLeaveLimit;
	}
	public void setPrivateLeaveLimit(int privateLeaveLimit) {
		this.privateLeaveLimit = privateLeaveLimit;
	}
	public int getOtherLeaveLimit() {
		return otherLeaveLimit;
	}
	public void setOtherLeaveLimit(int otherLeaveLimit) {
		this.otherLeaveLimit = otherLeaveLimit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
