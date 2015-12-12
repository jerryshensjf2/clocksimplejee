package com.javaforever.clocksimplejee4.domain;

public class LeaveLeft {
	private long id;
	private long userId;
	private long empId;
	private int annualLeaveLeft;
	private int sickLeaveLeft;
	private int privateLeaveLeft;
	private int otherLeaveLeft;
	private int year;
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
	public int getAnnualLeaveLeft() {
		return annualLeaveLeft;
	}
	public void setAnnualLeaveLeft(int annualLeaveLeft) {
		this.annualLeaveLeft = annualLeaveLeft;
	}
	public int getSickLeaveLeft() {
		return sickLeaveLeft;
	}
	public void setSickLeaveLeft(int sickLeaveLeft) {
		this.sickLeaveLeft = sickLeaveLeft;
	}
	public int getPrivateLeaveLeft() {
		return privateLeaveLeft;
	}
	public void setPrivateLeaveLeft(int privateLeaveLeft) {
		this.privateLeaveLeft = privateLeaveLeft;
	}
	public int getOtherLeaveLeft() {
		return otherLeaveLeft;
	}
	public void setOtherLeaveLeft(int otherLeaveLeft) {
		this.otherLeaveLeft = otherLeaveLeft;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
