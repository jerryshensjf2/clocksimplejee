package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.LeaveTypeDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class LeaveTypeDaoImpl implements LeaveTypeDao {

    @Override
    public List<LeaveType> listAllLeaveTypes(Connection connection) throws Exception{
        String query;

        query = "SELECT id,leavetypename,unitFine,description FROM leavetypes";
        
        ArrayList<LeaveType> leavetypeData = new ArrayList<LeaveType>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the leaveleft object
            LeaveType leavetype = new LeaveType();
            leavetype.setId(result.getLong("id"));
            leavetype.setLeaveTypeName(result.getString("leavetypename"));
            leavetype.setUnitFine(result.getBigDecimal("unitFine"));
            leavetype.setDescription(result.getString("description"));
            
            //Build the object list
            leavetypeData.add(leavetype);
        }
        return leavetypeData;
    }    

    @Override
    public boolean updateLeaveType(Connection connection,LeaveType leavetype) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(leavetype != null) {
	            query0 = "SELECT count(id) as countNum FROM leavetypes where id=?;";
	            PreparedStatement ps0 =  connection.prepareStatement(query0);
	            ps0.setLong(1, leavetype.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "LeaveType not exists.";
	                return false;
	            }
	            else {
	            	query = "update leavetypes set leavetypename=?, unitfine=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setString(1, leavetype.getLeaveTypeName());
	            	ps.setBigDecimal(2, leavetype.getUnitFine());
	            	ps.setString(3, leavetype.getDescription());
	            	ps.setLong(4, leavetype.getId());
	            	System.out.println(query);
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
	public LeaveType findLeaveTypeById(Connection connection,long id) throws Exception {
        String query;

        query = "SELECT id,leavetypename,unitFine,description FROM leavetypes WHERE ID=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        LeaveType  leavetype = new LeaveType();
        ResultSet result = ps.executeQuery();

        while(result.next()) {            
        	//Build the  leavetype object 
            leavetype.setId(result.getLong("id"));
            leavetype.setLeaveTypeName(result.getString("leavetypename"));
            leavetype.setUnitFine(result.getBigDecimal("unitFine"));
            leavetype.setDescription(result.getString("description"));
        }
        return  leavetype;
	}
	

public LeaveType findLeaveTypeByLeaveTypeName(Connection connection,String leaveTypeName) throws Exception{
    String query;
    
    try{
	    query = "SELECT id,leavetypename,unitFine,description FROM leavetypes WHERE leaveTypeName=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, leaveTypeName);
	    ResultSet result = ps.executeQuery();
	    
	    LeaveType leavetype = new LeaveType();
	    if(result.next()) {            
	    	//Build the  leavetype object        
            leavetype.setId(result.getLong("id"));
            leavetype.setLeaveTypeName(result.getString("leavetypename"));
            leavetype.setUnitFine(result.getBigDecimal("unitfine"));
            leavetype.setDescription(result.getString("description"));
      
	    }
	    return leavetype;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());
	    }finally{
	    }
	}

	@Override
	public boolean createLeaveType(Connection connection,LeaveType leavetype) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = leavetype.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM leavetypes where id=?;";	    		
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
	                query = "insert into leavetypes (leavetypename,unitfine,description) values (?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setString(1, leavetype.getLeaveTypeName());
	            	ps.setBigDecimal(2, leavetype.getUnitFine());
	            	ps.setString(3, leavetype.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new Exception(e.getMessage());
	     }
	}

	@Override
	public boolean deleteLeaveType(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from leavetypes where id=?;";
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
