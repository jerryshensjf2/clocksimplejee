package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.LeaveLimitDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLimit;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class LeaveLimitDaoImpl implements LeaveLimitDao {

    @Override
    public List<LeaveLimit> listAllLeaveLimits(Connection connection) throws Exception{
        String query;

        query = "SELECT id,employeetypeid,annualLeaveLimit,sickLeaveLimit,privateLeaveLimit,otherLeaveLimit,description FROM leavelimits";
        
        ArrayList<LeaveLimit> leavelimitData = new ArrayList<LeaveLimit>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the leaveleft object
            LeaveLimit leavelimit = new LeaveLimit();
            leavelimit.setId(result.getLong("id"));
            leavelimit.setEmployeeTypeId(result.getLong("employeetypeid"));
            leavelimit.setAnnualLeaveLimit(result.getInt("annualLeaveLimit"));
            leavelimit.setSickLeaveLimit(result.getInt("sickLeaveLimit"));
            leavelimit.setPrivateLeaveLimit(result.getInt("privateLeaveLimit"));
            leavelimit.setOtherLeaveLimit(result.getInt("otherLeaveLimit"));
            leavelimit.setDescription(result.getString("description"));
            
            //Build the object list
            leavelimitData.add(leavelimit);
        }
        return leavelimitData;
    }    

    @Override
    public boolean updateLeaveLimit(Connection connection,LeaveLimit leavelimit) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(leavelimit != null) {
	            query0 = "SELECT count(id) as countNum FROM leavelimits where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, leavelimit.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "LeaveLimit not exists.";
	                return false;
	            }
	            else {
	            	query = "update leavelimits set employeetypeid=?, annualLeaveLimit=?,  sickLeaveLimit=?, privateLeaveLimit=? , otherLeaveLimit=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setLong(1, leavelimit.getEmployeeTypeId());
	            	ps.setInt(2, leavelimit.getAnnualLeaveLimit());
	            	ps.setInt(3, leavelimit.getSickLeaveLimit());
	            	ps.setInt(4, leavelimit.getPrivateLeaveLimit());
	            	ps.setInt(5, leavelimit.getOtherLeaveLimit());
	            	ps.setString(6, leavelimit.getDescription());
	            	ps.setLong(7, leavelimit.getId());
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
	public LeaveLimit findLeaveLimitById(Connection connection,long id) throws Exception {
        String query;

        query = "SELECT id,employeetypeid,annualLeaveLimit,sickLeaveLimit,privateLeaveLimit,otherLeaveLimit,description FROM leavelimits WHERE ID=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        LeaveLimit  leavelimit = new LeaveLimit();
        ResultSet result = ps.executeQuery();

        while(result.next()) {            
        	//Build the  leavelimit object 
             leavelimit.setId(result.getLong("id"));
             leavelimit.setEmployeeTypeId(result.getLong("employeetypeid"));
             leavelimit.setAnnualLeaveLimit(result.getInt("annualLeaveLimit"));
             leavelimit.setSickLeaveLimit(result.getInt("sickLeaveLimit"));
             leavelimit.setPrivateLeaveLimit(result.getInt("privateLeaveLimit"));
             leavelimit.setOtherLeaveLimit(result.getInt("otherLeaveLimit"));
             leavelimit.setDescription(result.getString("description"));
        }
        return  leavelimit;
	}
	

public List<LeaveLimit> getLeaveLimitListByEmployeeTypeId(Connection connection,long employeetypeid) throws Exception{
    String query;    
    try{
	    query = "SELECT id,employeetypeid,annualLeaveLimit,sickLeaveLimit,privateLeaveLimit,otherLeaveLimit,description FROM leavelimits WHERE employeetypeid=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setLong(1, employeetypeid);
	    ResultSet result = ps.executeQuery();	    
	    
	    ArrayList<LeaveLimit> leavelimitList = new ArrayList<LeaveLimit>();
	    while(result.next()) {            
	    	//Build the  leavelimit object
	    	 LeaveLimit  leavelimit = new LeaveLimit();	        
	    	 leavelimit.setId(result.getLong("id"));
	    	 leavelimit.setEmployeeTypeId(result.getLong("employeetypeid"));
	         leavelimit.setAnnualLeaveLimit(result.getInt("annualLeaveLimit"));
	         leavelimit.setSickLeaveLimit(result.getInt("sickLeaveLimit"));
	         leavelimit.setPrivateLeaveLimit(result.getInt("privateLeaveLimit"));
	         leavelimit.setOtherLeaveLimit(result.getInt("otherLeaveLimit"));
	         leavelimit.setDescription(result.getString("description"));
	        
	         leavelimitList.add(leavelimit);        
	    }
	    return leavelimitList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());
	    }finally{
	    }
	}

	@Override
	public boolean createLeaveLimit(Connection connection,LeaveLimit leavelimit) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = leavelimit.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM leavelimits where id=?;";	    		
	    			PreparedStatement ps0 = connection.prepareStatement(query0);
	    			ps0.setLong(1,id);
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
	                query = "insert into leavelimits (employeetypeid,annualleavelimit,sickleavelimit,privateleavelimit,otherleavelimit,description) values (?,?,?,?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setLong(1, leavelimit.getEmployeeTypeId());
	            	ps.setInt(2, leavelimit.getAnnualLeaveLimit());
	            	ps.setInt(3, leavelimit.getSickLeaveLimit());
	            	ps.setInt(4, leavelimit.getPrivateLeaveLimit());
	            	ps.setInt(5, leavelimit.getOtherLeaveLimit());
	            	ps.setString(6, leavelimit.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new Exception(e.getMessage());
	     }
	}

	@Override
	public boolean deleteLeaveLimit(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from leavelimits where id=?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            result = ps.executeUpdate();
            if (result > 0) {
            	retVal = true;
            } else {
            	retVal = false;
	    	}
	     } catch(SQLException e){
	         throw new Exception(e.getMessage());
	     } catch(Exception e){
	         throw new Exception(e.getMessage());
	     }
	     return retVal;
	}

}
