package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.LeaveDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Leave;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class LeaveDaoImpl implements LeaveDao {
    
    @Override
    public List<Leave> listAllLeaves(Connection connection) throws Exception{
        String query;

        query = "SELECT id,userid,empid,day,leavetypeid,description FROM leaves";
        
        ArrayList<Leave> leaveData = new ArrayList<Leave>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the leave object
            Leave leave = new Leave();
            leave.setId(result.getLong("id"));
            leave.setUserId(result.getLong("userid"));
            leave.setEmpId(result.getLong("empid"));
            leave.setDay(result.getDate("day"));
            leave.setLeaveTypeId(result.getLong("leavetypeid"));
            leave.setDescription(result.getString("description"));
            //Build the object list
            leaveData.add(leave);
        }
        return leaveData;
    }    
	
    @Override
    public boolean updateLeave(Connection connection,Leave leave) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(leave!=null) {
	            query0 = "SELECT count(id) as countNum FROM leaves where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, leave.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "Leave not exists.";
	                return false;
	            }
	            else {
	            	query = "update leaves set userid=?,  empid=?, day=?, leavetypeid=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setLong(1, leave.getUserId());
	            	ps.setLong(2, leave.getEmpId());
	            	ps.setDate(3, leave.getDay());
	            	ps.setLong(4, leave.getLeaveTypeId());
	            	ps.setString(5, leave.getDescription());
	            	ps.setLong(6, leave.getId());
	                result = ps.executeUpdate();
	                return true;
	            }
	        }
	     } catch(SQLException e){
	         throw new Exception(e.getMessage());
	     }
	    return false;
    }

	@Override
	public Leave findLeaveById(Connection connection,long id) throws Exception {
	        String query;

	        query = "SELECT id,userid,empId,day,leaveTypeId,description FROM leaves WHERE ID=?;";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setLong(1, id);
	        Leave leave = new Leave();
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	//Build the leave object 
	            leave.setId(result.getLong("id"));
	            leave.setUserId(result.getLong("userid"));
	            leave.setEmpId(result.getLong("empId"));
	            leave.setDay(result.getDate("day"));
	            leave.setLeaveTypeId(result.getLong("leaveTypeId"));
	            leave.setDescription(result.getString("description"));
	        }
	        return leave;
	}
	

public List<Leave> getLeaveListByEmpid(Connection connection,long empid) throws IOException{
    String query;
    
    try{
	    query = "SELECT id,userid,empId,day,leaveTypeId,description FROM leaves WHERE empid=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, empid);	
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<Leave> leaveList = new ArrayList<Leave>();
	    while(result.next()) {            
	    	//Build the leave object
	    	Leave leave = new Leave();
	        
	        leave.setId(result.getLong("id"));
            leave.setUserId(result.getLong("userid"));
            leave.setEmpId(result.getLong("empId"));
            leave.setDay(result.getDate("day"));
            leave.setLeaveTypeId(result.getLong("leavetypeid"));
            leave.setDescription(result.getString("description"));
	        
	        leaveList.add(leave);        
	    }
	    return leaveList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new IOException(e.getMessage());
	    }finally{
	    }
	}
	
	
	@Override
	public boolean createLeave(Connection connection,Leave leave) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = leave.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM leaves where id=?;";	
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
	                query = "insert into leaves (userid,empid,day,leavetypeid,description) values (?,?,?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	    	        ps.setLong(1, leave.getUserId());
	    	        ps.setLong(2, leave.getEmpId());
	    	        ps.setDate(3, leave.getDay());
	    	        ps.setLong(4, leave.getLeaveTypeId());
	    	        ps.setString(5, leave.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteLeave(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from leaves where id=?;";
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

}
