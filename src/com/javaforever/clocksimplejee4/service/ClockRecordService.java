package com.javaforever.clocksimplejee4.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.domain.User;

public interface ClockRecordService {
	public boolean createClockRecord(ClockRecord clockRecord) throws Exception;
	public boolean updateClockRecord(ClockRecord clockRecord) throws Exception;
	public boolean deleteClockRecord(long id) throws Exception;
	public List<ClockRecord> listAllClockRecord() throws Exception; 
	public ClockRecord findClockRecordById(long id) throws Exception;
	public List<ClockRecord> getClockRecordListByEmpid(long empid) throws Exception;
	public void clock(User user, String description)throws Exception;
	public List<ClockRecord> listAllTodayClockRecord(User user) throws Exception; 
	public BigDecimal todayDuration(User user) throws Exception;
	public BigDecimal dayDuration(Timestamp day, User user) throws Exception;
	public List<ClockRecord> listAllSomeDayClockRecord(Timestamp timestamp ,User user) throws Exception;	
	public List<BigDecimal> getLastWeekSummary(Timestamp now, User user) throws Exception;
	public List<BigDecimal> getThisWeekSummary(Timestamp now, User user) throws Exception;
	public List<BigDecimal> getLastMonthSummary(Timestamp now, User user) throws Exception;
	public List<BigDecimal> getThisMonthSummary(Timestamp now, User user) throws Exception;
	public List<BigDecimal> getWholeThisYearSummary(Timestamp now, User user) throws Exception;
	public List<BigDecimal> getWholeLastYearSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getPeriodWorkStatusSummary(Timestamp fromDate,Timestamp toDate, User user) throws Exception;
	public List<AttendanceStatus> getWholeThisYearWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getWholeLastYearWorkStatusSummary(Timestamp now, User user) throws Exception;
	public BigDecimal getWholeLastYearWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getWholeThisYearWorkSum(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getThisMonthWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getLastMonthWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getThisWeekWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getLastWeekWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getYearWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getMonthWorkStatusSummary(Timestamp now, User user) throws Exception;
	public List<AttendanceStatus> getWeekWorkStatusSummary(Timestamp now, User user) throws Exception;
	public BigDecimal getThisMonthWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getLastMonthWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getMonthWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getThisWeekWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getLastWeekWorkSum(Timestamp now, User user) throws Exception;
	public BigDecimal getWeekWorkSum(Timestamp now, User user) throws Exception;
}
