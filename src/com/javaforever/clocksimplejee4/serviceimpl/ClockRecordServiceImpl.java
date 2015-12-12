package com.javaforever.clocksimplejee4.serviceimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.dao.ClockRecordDao;
import com.javaforever.clocksimplejee4.daoimpl.ClockRecordDaoImpl;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.ClockRecordService;


public class ClockRecordServiceImpl implements ClockRecordService{
	public static ClockRecordDao instance = new ClockRecordDaoImpl();

	@Override
	public void clock(User user, String description) throws Exception {
		try (Connection connection = DBConf.initDB()){
			instance.clock(connection,user, description);
		}
	}

	@Override
	public boolean createClockRecord(ClockRecord clockRecord) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.createClockRecord(connection,clockRecord);
		}
	}

	@Override
	public boolean deleteClockRecord(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.deleteClockRecord(connection,id);
		}
	}

	@Override
	public ClockRecord findClockRecordById(long id) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.findClockRecordById(connection,id);
		}
	}

	@Override
	public List<ClockRecord> getClockRecordListByEmpid(long empid)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getClockRecordListByEmpid(connection,empid);
		}
	}

	@Override
	public List<ClockRecord> listAllClockRecord() throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllClockRecord(connection);
		}
	}

	@Override
	public boolean updateClockRecord(ClockRecord clockRecord) throws Exception {
		try (Connection connection = DBConf.initDB()){	
			return instance.updateClockRecord(connection,clockRecord);
		}
	}

	@Override
	public BigDecimal todayDuration(User user) throws Exception {
		try (Connection connection = DBConf.initDB()){	
			return instance.todayDuration(connection,user);
		}
	}

	@Override
	public List<ClockRecord> listAllTodayClockRecord(User user) throws Exception {
		try (Connection connection = DBConf.initDB()){	
			return instance.listAllTodayClockRecord(connection,user);
		}
	}

	@Override
	public BigDecimal dayDuration(Timestamp day,User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.dayDuration(connection,day, user);
		}
	}

	@Override
	public List<ClockRecord> listAllSomeDayClockRecord(Timestamp timestamp, User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.listAllSomeDayClockRecord(connection,timestamp, user);
		}
	}
	
	@Override
	public List<BigDecimal> getLastMonthSummary(Timestamp now, User user) throws Exception {
		try (Connection connection = DBConf.initDB()){	
			int beginMonthInt = now.getMonth()-1;
			int beginYearInt = now.getYear();
			if (beginMonthInt < 0){
				beginMonthInt = (beginMonthInt + 12) % 12;
				beginYearInt -= 1;
			}
			int beginDateInt = 1;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = 1;
			// working day form hours 0700 to next day 0659
			return instance.getPeriodSummary(connection,new Timestamp(beginYearInt, beginMonthInt,beginDateInt,7,0,0,0), new Timestamp(endYearInt, endMonthInt,endDateInt,7,0,0,0), user);
		}
	}
	
	private int getEndDateOfMonth(int yearInt, int monthInt) throws Exception{
		int monthEndDateInt = 30;
		switch (monthInt) {
		case 0 : monthEndDateInt =31; break;
		case 1 : monthEndDateInt = getLeapMothEndDate(yearInt); break;
		case 2 : monthEndDateInt =31; break;
		case 3 : monthEndDateInt =30; break;
		case 4 : monthEndDateInt =31; break;
		case 5 : monthEndDateInt =30; break;
		case 6 : monthEndDateInt =31; break;
		case 7 : monthEndDateInt =31; break;
		case 8 : monthEndDateInt =30; break;
		case 9 : monthEndDateInt =31; break;
		case 10 : monthEndDateInt =30; break;
		case 11 : monthEndDateInt =31; break;
		default : throw new Exception("Invalid date");
		}
		return monthEndDateInt;
	}
	
	private int getLeapMothEndDate(int yearInt){
		int leapMonthEndDateInt = 28;
		if (yearInt % 4 != 0)
			leapMonthEndDateInt = 28;
		else if (yearInt % 400 == 0)
			leapMonthEndDateInt = 29;
		else if (yearInt % 4 == 0 && yearInt % 100 != 0)
			leapMonthEndDateInt = 29;
		else if (yearInt % 4 == 0 && yearInt % 100 ==0 && yearInt % 400 != 0)
			leapMonthEndDateInt =28;
		return leapMonthEndDateInt;
	}
	
	@Override
	public List<BigDecimal> getThisMonthSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginMonthInt = now.getMonth();
			int beginYearInt = now.getYear();		
			int beginDateInt = 1;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(beginYearInt,beginMonthInt)){
				endDateInt = 1;
				endMonthInt = endMonthInt + 1;
				if (endMonthInt >= 12){
					endMonthInt = 0;
					endYearInt += 1;
				}			
			}		
			
			// working day form hours 0700 to next day 0659
			return instance.getPeriodSummary(connection,new Timestamp(beginYearInt, beginMonthInt,beginDateInt,7,0,0,0), new Timestamp(endYearInt, endMonthInt,endDateInt,7,0,0,0), user);
		}	
	}

	@Override
	public List<BigDecimal> getLastWeekSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = now.getMonth();
			// date:1-31  moth:0-11 day:0-6
			int beginDateInt = now.getDate()-now.getDay()-7;
			if (beginDateInt <= 0){
				if (beginMonthInt - 1 >= 0){
					beginMonthInt = beginMonthInt - 1;
					beginDateInt = beginDateInt + getEndDateOfMonth(beginYearInt, beginMonthInt);
				} else {
					beginMonthInt = 11;
					beginDateInt = beginDateInt + 31;
					beginYearInt = beginYearInt -1;
				}
			}
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()-now.getDay();
			if (endDateInt <= 0){
				if (endMonthInt - 1 >= 0){
					endMonthInt = endMonthInt - 1;
					endDateInt = endDateInt + getEndDateOfMonth(endYearInt, endMonthInt);
				} else {
					endMonthInt = 11;
					endDateInt = endDateInt + 31;
					endYearInt = endYearInt -1;
				}
			}
			List<BigDecimal> resultList = instance.getPeriodSummary(connection,new Timestamp(beginYearInt,beginMonthInt,beginDateInt,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}
	
	@Override
	public List<BigDecimal> getThisWeekSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = now.getMonth();
			// date:1-31  moth:0-11 day:0-6
			int beginDateInt = now.getDate()-now.getDay();
			if (beginDateInt <= 0){
				if (beginMonthInt - 1 >= 0){
					beginMonthInt = beginMonthInt - 1;
					beginDateInt = beginDateInt + getEndDateOfMonth(beginYearInt, beginMonthInt);
				} else {
					beginMonthInt = 11;
					beginDateInt = beginDateInt + 31;
					beginYearInt = beginYearInt -1;
				}
			}
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(endYearInt, endMonthInt)){
				endDateInt = 1;
				endMonthInt ++;
				if (endMonthInt > 11){
					endMonthInt = 0;
					endYearInt ++;
				}
			}
	
			List<BigDecimal> resultList = instance.getPeriodSummary(connection,new Timestamp(beginYearInt,beginMonthInt,beginDateInt,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<BigDecimal> getWholeLastYearSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear() -1;
			int beginMonthInt = 0;
			int endYearInt = beginYearInt + 1;
			int endMonthInt = 0;
			List<BigDecimal> resultList = instance.getPeriodSummary(connection,new Timestamp(beginYearInt,beginMonthInt,1,7,0,0,0),new Timestamp(endYearInt,endMonthInt,1,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<BigDecimal> getWholeThisYearSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = 0;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(endYearInt, endMonthInt)){
				endDateInt = 1;
				endMonthInt ++;
				if (endMonthInt > 11){
					endMonthInt = 0;
					endYearInt ++;
				}
			}
			 List<BigDecimal> resultList = instance.getPeriodSummary(connection,new Timestamp(beginYearInt,beginMonthInt,1,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<AttendanceStatus> getWholeLastYearWorkStatusSummary(
			Timestamp now, User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear() -1;
			int beginMonthInt = 0;
			int endYearInt = beginYearInt + 1;
			int endMonthInt = 0;
			List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt,beginMonthInt,1,7,0,0,0),new Timestamp(endYearInt,endMonthInt,1,7,0,0,0), user);
			return resultList;
		}
	}
	
	@Override
	public List<AttendanceStatus> getWholeThisYearWorkStatusSummary(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = 0;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(endYearInt, endMonthInt)){
				endDateInt = 1;
				endMonthInt ++;
				if (endMonthInt > 11){
					endMonthInt = 0;
					endYearInt ++;
				}
			}
			 List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt,beginMonthInt,1,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<AttendanceStatus> getPeriodWorkStatusSummary(
			Timestamp fromDate, Timestamp toDate, User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			return instance.getPeriodWorkStatusSummary(connection,fromDate, toDate, user);
		}
	}

	@Override
	public BigDecimal getWholeLastYearWorkSum(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			BigDecimal sum = new BigDecimal("0.00");
			List<AttendanceStatus> workStatusList = getWholeLastYearWorkStatusSummary(now,user);
			for (AttendanceStatus status:workStatusList){
				sum = sum.add(status.getDuration());
			}
			return sum;
		}
	}
	
	@Override
	public BigDecimal getWholeThisYearWorkSum(Timestamp now, User user)
			throws Exception {
		BigDecimal sum = new BigDecimal("0.00");
		List<AttendanceStatus> workStatusList = getWholeThisYearWorkStatusSummary(now,user);
		for (AttendanceStatus status:workStatusList){
			sum = sum.add(status.getDuration());
		}
		return sum;
	}

	@Override
	public List<AttendanceStatus> getLastMonthWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginMonthInt = now.getMonth()-1;
			int beginYearInt = now.getYear();
			if (beginMonthInt < 0){
				beginMonthInt = (beginMonthInt + 12) % 12;
				beginYearInt -= 1;
			}
			int beginDateInt = 1;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = 1;
			// working day form hours 0700 to next day 0659
			return instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt, beginMonthInt,beginDateInt,7,0,0,0), new Timestamp(endYearInt, endMonthInt,endDateInt,7,0,0,0), user);
		}
	
	}

	@Override
	public List<AttendanceStatus> getLastWeekWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = now.getMonth();
			// date:1-31  moth:0-11 day:0-6
			int beginDateInt = now.getDate()-now.getDay()-7;
			if (beginDateInt <= 0){
				if (beginMonthInt - 1 >= 0){
					beginMonthInt = beginMonthInt - 1;
					beginDateInt = beginDateInt + getEndDateOfMonth(beginYearInt, beginMonthInt);
				} else {
					beginMonthInt = 11;
					beginDateInt = beginDateInt + 31;
					beginYearInt = beginYearInt -1;
				}
			}
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()-now.getDay();
			if (endDateInt <= 0){
				if (endMonthInt - 1 >= 0){
					endMonthInt = endMonthInt - 1;
					endDateInt = endDateInt + getEndDateOfMonth(endYearInt, endMonthInt);
				} else {
					endMonthInt = 11;
					endDateInt = endDateInt + 31;
					endYearInt = endYearInt -1;
				}
			}
			List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt,beginMonthInt,beginDateInt,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<AttendanceStatus> getThisMonthWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginMonthInt = now.getMonth();
			int beginYearInt = now.getYear();		
			int beginDateInt = 1;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(beginYearInt,beginMonthInt)){
				endDateInt = 1;
				endMonthInt = endMonthInt + 1;
				if (endMonthInt >= 12){
					endMonthInt = 0;
					endYearInt += 1;
				}			
			}		
			
			// working day form hours 0700 to next day 0659
			return instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt, beginMonthInt,beginDateInt,7,0,0,0), new Timestamp(endYearInt, endMonthInt,endDateInt,7,0,0,0), user);
		}
	}

	@Override
	public List<AttendanceStatus> getThisWeekWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = now.getMonth();
			// date:1-31  moth:0-11 day:0-6
			int beginDateInt = now.getDate()-now.getDay();
			if (beginDateInt <= 0){
				if (beginMonthInt - 1 >= 0){
					beginMonthInt = beginMonthInt - 1;
					beginDateInt = beginDateInt + getEndDateOfMonth(beginYearInt, beginMonthInt);
				} else {
					beginMonthInt = 11;
					beginDateInt = beginDateInt + 31;
					beginYearInt = beginYearInt -1;
				}
			}
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth();
			int endDateInt = now.getDate()+1;
			if (endDateInt > getEndDateOfMonth(endYearInt, endMonthInt)){
				endDateInt = 1;
				endMonthInt ++;
				if (endMonthInt > 11){
					endMonthInt = 0;
					endYearInt ++;
				}
			}
	
			List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt,beginMonthInt,beginDateInt,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public List<AttendanceStatus> getMonthWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginMonthInt = now.getMonth();
			int beginYearInt = now.getYear();		
			int beginDateInt = 1;
			int endYearInt = now.getYear();
			int endMonthInt = now.getMonth()+1;
			int endDateInt = 1;
			if (endMonthInt >= 12){
				endMonthInt = 0;
				endYearInt += 1;
			}			
			// working day form hours 0700 to next day 0659
			return instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt, beginMonthInt,beginDateInt,7,0,0,0), new Timestamp(endYearInt, endMonthInt,endDateInt,7,0,0,0), user);
		}
	}

	@Override
	public List<AttendanceStatus> getWeekWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = now.getMonth();
			// date:1-31  moth:0-11 day:0-6
			int beginDateInt = now.getDate()-now.getDay();
			if (beginDateInt <= 0){
				if (beginMonthInt - 1 >= 0){
					beginMonthInt = beginMonthInt - 1;
					beginDateInt = beginDateInt + getEndDateOfMonth(beginYearInt, beginMonthInt);
				} else {
					beginMonthInt = 11;
					beginDateInt = beginDateInt + 31;
					beginYearInt = beginYearInt -1;
				}
			}
			
			Timestamp beginStamp = new Timestamp(beginYearInt,beginMonthInt,beginDateInt,7,0,0,0);
			long beginMills = beginStamp.getTime();
			long endMills = beginMills + 7L*3600L*24L*1000L;
			Timestamp endStamp = new Timestamp(endMills);
	
			List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,beginStamp,endStamp, user);
			return resultList;
		}
	}

	@Override
	public List<AttendanceStatus> getYearWorkStatusSummary(Timestamp now,
			User user) throws Exception {
		try (Connection connection = DBConf.initDB()){
			int beginYearInt = now.getYear();
			int beginMonthInt = 0;
			int endYearInt = now.getYear()+1;
			int endMonthInt = 0;
			int endDateInt = 1;
	
			List<AttendanceStatus> resultList = instance.getPeriodWorkStatusSummary(connection,new Timestamp(beginYearInt,beginMonthInt,1,7,0,0,0),new Timestamp(endYearInt,endMonthInt,endDateInt,7,0,0,0), user);
			return resultList;
		}
	}

	@Override
	public BigDecimal getLastMonthWorkSum(Timestamp now, User user)
			throws Exception {
		BigDecimal sum = new BigDecimal("0.00");
		List<AttendanceStatus> workStatusList = getLastMonthWorkStatusSummary(now,user);
		for (AttendanceStatus status:workStatusList){
			sum = sum.add(status.getDuration());
		}
		return sum;
	}

	@Override
	public BigDecimal getLastWeekWorkSum(Timestamp now, User user)
			throws Exception {
		BigDecimal sum = new BigDecimal("0.00");
		List<AttendanceStatus> workStatusList = getLastWeekWorkStatusSummary(now,user);
		for (AttendanceStatus status:workStatusList){
			sum = sum.add(status.getDuration());
		}
		return sum;
	}

	@Override
	public BigDecimal getMonthWorkSum(Timestamp now, User user)
			throws Exception {
		BigDecimal sum = new BigDecimal("0.00");
		List<AttendanceStatus> workStatusList = getMonthWorkStatusSummary(now,user);
		for (AttendanceStatus status:workStatusList){
			sum = sum.add(status.getDuration());
		}
		return sum;
	}

	@Override
	public BigDecimal getThisMonthWorkSum(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			BigDecimal sum = new BigDecimal("0.00");
			List<AttendanceStatus> workStatusList = getThisMonthWorkStatusSummary(now,user);
			for (AttendanceStatus status:workStatusList){
				sum = sum.add(status.getDuration());
			}
			return sum;
		}
	}

	@Override
	public BigDecimal getThisWeekWorkSum(Timestamp now, User user)
			throws Exception {
		try (Connection connection = DBConf.initDB()){
			BigDecimal sum = new BigDecimal("0.00");
			List<AttendanceStatus> workStatusList = getThisWeekWorkStatusSummary(now,user);
			for (AttendanceStatus status:workStatusList){
				sum = sum.add(status.getDuration());
			}
			return sum;
		}
	}

	@Override
	public BigDecimal getWeekWorkSum(Timestamp now, User user) throws Exception {
		BigDecimal sum = new BigDecimal("0.00");
		List<AttendanceStatus> workStatusList = getWeekWorkStatusSummary(now,user);
		for (AttendanceStatus status:workStatusList){
			sum = sum.add(status.getDuration());
		}
		return sum;
	}
}
