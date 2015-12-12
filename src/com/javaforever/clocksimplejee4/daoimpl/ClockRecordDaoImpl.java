package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.dao.ClockRecordDao;
import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.modeler.AttendanceStatusModeler;
import com.javaforever.clocksimplejee4.utils.Grid;
import com.javaforever.clocksimplejee4.utils.Row;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ClockRecordDaoImpl implements ClockRecordDao {
    
    @Override
    public List<ClockRecord> listAllClockRecord(Connection connection) throws Exception{
        String query;

        query = "SELECT id,userid,empId,timestamp,Description FROM clockRecord";
        
        ArrayList<ClockRecord> clockRecordData = new ArrayList<ClockRecord>();
        
        ResultSet result = connection.prepareStatement(query).executeQuery();
        
        while(result.next()) {            
        	//Build the clockRecord object
            ClockRecord clockRecord = new ClockRecord();
            clockRecord.setId(result.getLong("id"));
            clockRecord.setUserId(result.getLong("userid"));
            clockRecord.setEmpId(result.getLong("empId"));
            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
            clockRecord.setDescription(result.getString("Description"));
            //Build the object list
            clockRecordData.add(clockRecord);
        }
        return clockRecordData;
    }    

    @Override
    public boolean updateClockRecord(Connection connection,ClockRecord clockRecord) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(clockRecord!=null) {
	            query0 = "SELECT count(id) as countNum FROM clockRecord where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, clockRecord.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "ClockRecord not exists.";
	                return false;
	            }
	            else {
	            	query = "update clockRecord set userid=?,  empid=?, timestamp=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setLong(1,clockRecord.getUserId());
	            	ps.setLong(2,clockRecord.getEmpId());
	            	ps.setTimestamp(3, clockRecord.getTimeStamp());
	            	ps.setString(4,clockRecord.getDescription());
	            	ps.setLong(5, clockRecord.getId());
	                result = ps.executeUpdate();
	                return true;
	            }
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	    return false;
    }

	@Override
	public ClockRecord findClockRecordById(Connection connection,long id) throws Exception {
	        String query;

	        query = "SELECT id,userid,empId,timestamp,Description FROM clockrecord WHERE ID=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setLong(1, id);
	        ClockRecord clockRecord = new ClockRecord();
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	//Build the clockRecord object 
	            clockRecord.setId(result.getLong("id"));
	            clockRecord.setUserId(result.getLong("userid"));
	            clockRecord.setEmpId(result.getLong("empId"));
	            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
	            clockRecord.setDescription(result.getString("Description"));
	        }
	        return clockRecord;
	}
	

public List<ClockRecord> getClockRecordListByEmpid(Connection connection,long empid) throws IOException{
    String query;
    
    try{
	    query = "SELECT id,userid,empId,timestamp,description FROM clockRecord WHERE empid=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setLong(1, empid);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<ClockRecord> clockRecordList = new ArrayList<ClockRecord>();
	    while(result.next()) {            
	    	//Build the clockRecord object
	    	ClockRecord clockRecord = new ClockRecord();
	        
	        clockRecord.setId(result.getLong("id"));
            clockRecord.setUserId(result.getLong("userid"));
            clockRecord.setEmpId(result.getLong("empId"));
            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
            clockRecord.setDescription(result.getString("Description"));
	        
	        clockRecordList.add(clockRecord);        
	    }
	    return clockRecordList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new IOException(e.getMessage());
	    }finally{
	    }
	}
	
	
	@Override
	public boolean createClockRecord(Connection connection,ClockRecord clockRecord) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = clockRecord.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM clockRecord where id=?;";	    		
	    			PreparedStatement ps0 = connection.prepareStatement(query0);
	    			ps0.setLong(1, id);
		            ResultSet result0 = ps0.executeQuery();
		            if (result0.next()){
		            	count = result0.getInt("countNum");
		            }	            
	    		} else {
	    			count = 0;
	    		}
	            
	            if (count > 0) {
	                return false;
	            }
	            else {
	                query = "insert into clockrecord (userid,empid,timestamp,description) values (?,?,?,?);";
	                System.out.println(query);
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setLong(1,clockRecord.getUserId());
	                ps.setLong(2, clockRecord.getEmpId());
	                ps.setTimestamp(3, clockRecord.getTimeStamp());
	                ps.setString(4,clockRecord.getDescription() );
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteClockRecord(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from clockrecord where id=?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            result = ps.executeUpdate();
            if (result > 0) {
            	retVal = true;
            } else {
            	retVal = false;
	    	}
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     } catch(Exception e){
	         throw new IOException(e.getMessage());
	     }
	     return retVal;
	}

	@Override
	public void clock(Connection connection,User user, String description) throws Exception {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		ClockRecord clockRecord = new ClockRecord();
		clockRecord.setDescription(description);
		clockRecord.setEmpId(user.getEmpid());
		clockRecord.setUserId(user.getId());
		clockRecord.setTimeStamp(now);
		
		createClockRecord(connection,clockRecord);
	}

	@Override
	public BigDecimal todayDuration(Connection connection,User user) throws Exception { 	
	 	long nowMiniSeconds = System.currentTimeMillis();
	 	Timestamp now = new Timestamp(nowMiniSeconds);
	 	long thisDayBeginMiniSeconds =nowMiniSeconds - 3600L*1000L*now.getHours() + 3600L*1000L*7L -60L*1000L*now.getMinutes()-1000L*now.getSeconds()-nowMiniSeconds % 1000L;
	 	long thisDayEndMiniSeconds = thisDayBeginMiniSeconds + 3600L*1000L*24L;
	 	
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayBeginMiniSeconds);
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayEndMiniSeconds);
	 	
        String query;

        query = "SELECT id,userid,empId,timestamp,description FROM clockrecord where timestamp >= ? and timestamp < ? and  empid = ? order by timestamp desc";
        
        ArrayList<ClockRecord> clockRecordData = new ArrayList<ClockRecord>();
       
        java.sql.PreparedStatement statement = connection.prepareStatement(query);
        statement.setTimestamp(1, new Timestamp(thisDayBeginMiniSeconds ));
        statement.setTimestamp(2, new Timestamp(thisDayEndMiniSeconds));
        statement.setLong(3, user.getEmpid());
        
        System.out.println("++++++++++++++++++++Jerry Debug:"+statement);
        ResultSet result = statement.executeQuery();

        while(result.next()) {            
        	//Build the clockRecord object
            ClockRecord clockRecord = new ClockRecord();
            clockRecord.setId(result.getLong("id"));
            clockRecord.setUserId(result.getLong("userid"));
            clockRecord.setEmpId(result.getLong("empId"));
            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
            clockRecord.setDescription(result.getString("description"));
            //Build the object list
            clockRecordData.add(clockRecord);
        }
        BigDecimal bDuration = new BigDecimal(0);
        if (clockRecordData.size()>1){
        	long minisecondBegin = (clockRecordData.get(clockRecordData.size()-1)).getTimeStamp().getTime();
        	long minisecondEnd = (clockRecordData.get(0)).getTimeStamp().getTime();
        	bDuration= new BigDecimal((double)(minisecondEnd - minisecondBegin)/(3600.0d*1000.0d)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return bDuration;
	}

	@Override
	public List<ClockRecord> listAllTodayClockRecord(Connection connection,User user) throws Exception {
		 	
		 	long nowMiniSeconds = System.currentTimeMillis();
		 	Timestamp now = new Timestamp(nowMiniSeconds);
		 	long thisDayBeginMiniSeconds =nowMiniSeconds - 3600L*1000L*now.getHours() + 3600L*1000L*7L -60L*1000L*now.getMinutes()-1000L*now.getSeconds()-nowMiniSeconds % 1000L;
		 	long thisDayEndMiniSeconds = thisDayBeginMiniSeconds + 3600L*1000L*24L;
		 	
		 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayBeginMiniSeconds);
		 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayEndMiniSeconds);
		 	
	        String query;

	        query = "SELECT id,userid,empId,timestamp,description FROM clockrecord where timestamp >= ? and timestamp < ? and  empid = ? order by timestamp desc";
	        
	        ArrayList<ClockRecord> clockRecordData = new ArrayList<ClockRecord>();
	       
	        java.sql.PreparedStatement statement = connection.prepareStatement(query);
	        statement.setTimestamp(1, new Timestamp(thisDayBeginMiniSeconds ));
	        statement.setTimestamp(2, new Timestamp(thisDayEndMiniSeconds));
	        statement.setLong(3, user.getEmpid());
	        
	        System.out.println("++++++++++++++++++++Jerry Debug:"+statement);
	        ResultSet result = statement.executeQuery();

	        while(result.next()) {            
	        	//Build the clockRecord object
	            ClockRecord clockRecord = new ClockRecord();
	            clockRecord.setId(result.getLong("id"));
	            clockRecord.setUserId(result.getLong("userid"));
	            clockRecord.setEmpId(result.getLong("empId"));
	            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
	            clockRecord.setDescription(result.getString("description"));
	            //Build the object list
	            clockRecordData.add(clockRecord);
	        }
	        return clockRecordData;
	}
	
	@Override
	public List<ClockRecord> listAllSomeDayClockRecord(Connection connection,Timestamp timestamp, User user) throws Exception {	 	
		 	long nowMiniSeconds = timestamp.getTime();
		 	Timestamp now = new Timestamp(nowMiniSeconds);
		 	long thisDayBeginMiniSeconds =nowMiniSeconds - 3600L*1000L*now.getHours() + 3600L*1000L*7L -60L*1000L*now.getMinutes()-1000L*now.getSeconds()-nowMiniSeconds % 1000L;
		 	long thisDayEndMiniSeconds = thisDayBeginMiniSeconds + 3600L*1000L*24L;
		 	
		 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayBeginMiniSeconds);
		 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayEndMiniSeconds);
		 	
	        String query;

	        query = "SELECT id,userid,empId,timestamp,description FROM clockrecord where timestamp >= ? and timestamp < ? and  empid = ? order by timestamp desc";
	        
	        ArrayList<ClockRecord> clockRecordData = new ArrayList<ClockRecord>();
	       
	        java.sql.PreparedStatement statement = connection.prepareStatement(query);
	        statement.setTimestamp(1, new Timestamp(thisDayBeginMiniSeconds ));
	        statement.setTimestamp(2, new Timestamp(thisDayEndMiniSeconds));
	        statement.setLong(3, user.getEmpid());
	        
	        System.out.println("++++++++++++++++++++Jerry Debug:"+statement);
	        ResultSet result = statement.executeQuery();

	        while(result.next()) {            
	        	//Build the clockRecord object
	            ClockRecord clockRecord = new ClockRecord();
	            clockRecord.setId(result.getLong("id"));
	            clockRecord.setUserId(result.getLong("userid"));
	            clockRecord.setEmpId(result.getLong("empId"));
	            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
	            clockRecord.setDescription(result.getString("description"));
	            //Build the object list
	            clockRecordData.add(clockRecord);
	        }
	        return clockRecordData;
	}

	@Override
	public BigDecimal dayDuration(Connection connection,Timestamp day, User user) throws Exception {	
	 	long nowMiniSeconds = day.getTime();
	 	Timestamp now = new Timestamp(nowMiniSeconds);
	 	long thisDayBeginMiniSeconds = nowMiniSeconds - 3600L*1000L*now.getHours() + 3600L*1000L*7L -60L*1000L*now.getMinutes()-1000L*now.getSeconds()-nowMiniSeconds % 1000L;
	 	long thisDayEndMiniSeconds = thisDayBeginMiniSeconds + 3600L*1000L*24L;
	 	
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayBeginMiniSeconds);
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayEndMiniSeconds);
	 	
        String query;

        query = "SELECT id,userid,empId,timestamp,description FROM clockrecord where timestamp >= ? and timestamp < ? and  empid = ? order by timestamp desc";
        
        ArrayList<ClockRecord> clockRecordData = new ArrayList<ClockRecord>();
       
        java.sql.PreparedStatement statement = connection.prepareStatement(query);
        statement.setTimestamp(1, new Timestamp(thisDayBeginMiniSeconds ));
        statement.setTimestamp(2, new Timestamp(thisDayEndMiniSeconds));
        statement.setLong(3, user.getEmpid());
        
        System.out.println("++++++++++++++++++++Jerry Debug:"+statement);
        ResultSet result = statement.executeQuery();

        while(result.next()) {            
        	//Build the clockRecord object
            ClockRecord clockRecord = new ClockRecord();
            clockRecord.setId(result.getLong("id"));
            clockRecord.setUserId(result.getLong("userid"));
            clockRecord.setEmpId(result.getLong("empId"));
            clockRecord.setTimeStamp(result.getTimestamp("timestamp"));
            clockRecord.setDescription(result.getString("description"));
            //Build the object list
            clockRecordData.add(clockRecord);
        }
        BigDecimal bDuration = new BigDecimal(0);
        if (clockRecordData.size()>1){
        	long minisecondBegin = (clockRecordData.get(clockRecordData.size()-1)).getTimeStamp().getTime();
        	long minisecondEnd = (clockRecordData.get(0)).getTimeStamp().getTime();
        	bDuration= new BigDecimal((double)(minisecondEnd - minisecondBegin)/(3600.0d*1000.0d)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
        	bDuration = new BigDecimal("0.00");
        }
        return bDuration;
	}
	
	public List<BigDecimal> getPeriodSummary(Connection connection,Timestamp fromDate, Timestamp toDate,User user) throws Exception{
		long beginMills = fromDate.getTime();
		long endMills = toDate.getTime();
		List<BigDecimal> workTimeList = new ArrayList<BigDecimal>();
		for(long mills = beginMills; mills< endMills ; mills += 24*3600*1000L){
			workTimeList.add(dayDuration(connection,new Timestamp(mills), user));
		}
		return workTimeList;
	} 

	@Override
	public List<AttendanceStatus> getPeriodWorkStatusSummary(Connection connection,Timestamp fromDate,
			Timestamp toDate, User user) throws Exception {
	 	long fromDateMiniSeconds = fromDate.getTime();
	 	long toDateMiniSeconds = toDate.getTime();
	 	long thisDayBeginMiniSeconds = fromDateMiniSeconds - 3600L*1000L*fromDate.getHours() + 3600L*1000L*7L -60L*1000L*fromDate.getMinutes()-1000L*fromDate.getSeconds()-fromDateMiniSeconds % 1000L;
	 	long thisDayEndMiniSeconds = thisDayBeginMiniSeconds + 3600L*1000L*24L;
	 	long periodEndDateMills = toDateMiniSeconds - 3600L*1000L*toDate.getHours() + 3600L*1000L*7L -60L*1000L*toDate.getMinutes()-1000L*toDate.getSeconds()- toDateMiniSeconds % 1000L;
	 	
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayBeginMiniSeconds);
	 	System.out.println("++++++++++++++++++++Jerry Debug:"+thisDayEndMiniSeconds);
	 	
	 	List<AttendanceStatus> attendanceStatusData = new ArrayList<AttendanceStatus>();
	 	
	 	for(long mills = thisDayBeginMiniSeconds; mills < periodEndDateMills ; mills += 24*3600*1000L){	 		
	        String query;
	
	        query = "SELECT userid,users.empId as empId,username,firstname,lastname,`timestamp`,description, Max( `timestamp` ) , Min( `timestamp` ) , time_to_sec(timediff( Max( `timestamp` ) , Min( `timestamp` ) )) AS duration, " +
	        		" count(`timestamp`) as attendanceCount FROM users " +
	        		" LEFT JOIN clockrecord ON users.id = clockrecord.userid " +
	        		" WHERE `timestamp` >= ?" +
	        		" AND `timestamp` < ? " +
	        		" AND users.id = ? " +
	        		" GROUP BY users.id " +
	        		" ORDER BY `timestamp` DESC; ";
	       
	        java.sql.PreparedStatement statement = connection.prepareStatement(query);
	        statement.setTimestamp(1, new Timestamp(mills));
	        statement.setTimestamp(2, new Timestamp(mills + 24*3600*1000L));
	        statement.setLong(3, user.getId());
	        
	        System.out.println("++++++++++++++++++++Jerry Debug:"+statement);
	        ResultSet result = statement.executeQuery();
	        
	        AttendanceStatus attendanceStatus = new AttendanceStatus();
	        
	       	if (result.next()) {            	        	
	            //Build the clockRecord object
	        	
	            attendanceStatus.setUserId(result.getLong("userid"));
	            attendanceStatus.setEmpId(result.getLong("empId"));
	            attendanceStatus.setUsername(result.getString("username"));
	            attendanceStatus.setFullName(result.getString("firstname") + " " + result.getString("lastname"));
	            int attendanceCount = result.getInt("attendanceCount");
	            String status = "";
	            BigDecimal duration = new BigDecimal(""+(result.getLong("duration")/3600L)).setScale(2, BigDecimal.ROUND_HALF_UP);
	           if (attendanceCount >= 2){
	            	if (duration.compareTo(new BigDecimal(7.00)) >= 0) {
	            		status = "normal";
	            	} else {
	            		status = "lesstime";
	            	}
	            	
	            } else {
	            	status = "attend";
	            }
	            attendanceStatus.setAttendanceStatus(status);
	            attendanceStatus.setDuration(duration);
	            attendanceStatus.setAttendanceCount(attendanceCount);
	            attendanceStatus.setDescription(result.getString("description"));
	            //Build the object list
	            attendanceStatusData.add(attendanceStatus);
	        }
	       	else {
	       		UserDao userDao = new UserDaoImpl();
	        	User user0 = userDao.getUser(connection,user.getId());
	        	String status = "absent";
	        	attendanceStatus.setUserId(user0.getId());
	            attendanceStatus.setEmpId(user0.getEmpid());
	            attendanceStatus.setUsername(user0.getUsername());
	            attendanceStatus.setFullName(user0.getFullName());
	        	attendanceStatus.setAttendanceStatus(status);
	            attendanceStatus.setDuration(new BigDecimal("0.00"));
	            attendanceStatus.setDescription("no work clock record");
	            attendanceStatus.setAttendanceCount(0);
	            
	            attendanceStatusData.add(attendanceStatus);
	        }

        }
	 	return attendanceStatusData;	
	}

}
