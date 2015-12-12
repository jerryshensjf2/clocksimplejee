package com.javaforever.clocksimplejee4.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.domain.User;

public interface ClockRecordDao {
	public boolean createClockRecord(Connection connection,ClockRecord clockRecord) throws Exception;
	public boolean updateClockRecord(Connection connection,ClockRecord clockRecord) throws Exception;
	public boolean deleteClockRecord(Connection connection,long id) throws Exception;
	public List<ClockRecord> listAllClockRecord(Connection connection) throws Exception; 
	public ClockRecord findClockRecordById(Connection connection,long id) throws Exception;
	public List<ClockRecord> getClockRecordListByEmpid(Connection connection,long empid) throws IOException;
	public void clock(Connection connection,User user, String description)throws Exception;
	public List<ClockRecord> listAllTodayClockRecord(Connection connection,User user) throws Exception; 
	public BigDecimal todayDuration(Connection connection,User user) throws Exception;
	public BigDecimal dayDuration(Connection connection,Timestamp day, User user) throws Exception;
	public List<ClockRecord> listAllSomeDayClockRecord(Connection connection,Timestamp timestamp ,User user) throws Exception;
	public List<BigDecimal> getPeriodSummary(Connection connection,Timestamp fromDate, Timestamp toDate,User user) throws Exception;
	public List<AttendanceStatus> getPeriodWorkStatusSummary(Connection connection,Timestamp fromDate, Timestamp toDate,User user) throws Exception;
}
