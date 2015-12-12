package com.javaforever.clocksimplejee4.bo;

import java.math.BigDecimal;
import java.sql.Date;

import com.javaforever.clocksimplejee4.utils.IModel;

/**
 * AttendanceStatus
 * 
 * A bo(business object) is a pojo without db storage
 * the pojos with storage is cataloged as domain
 * 
 * @author Jerry Shen
 *
 */
public class AttendanceStatus implements IModel{
	private long userId;
	private long empId;
	private String username;
	private String fullName;
	private Date date;
	private String attendanceStatus;
	private String description;
	private BigDecimal duration;
	private int attendanceCount;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getDuration() {
		return duration;
	}
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}
	public int getAttendanceCount() {
		return attendanceCount;
	}
	public void setAttendanceCount(int attendanceCount) {
		this.attendanceCount = attendanceCount;
	}
}
