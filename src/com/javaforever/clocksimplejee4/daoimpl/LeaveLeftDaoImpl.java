package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.LeaveLeftDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.LeaveLeft;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class LeaveLeftDaoImpl implements LeaveLeftDao {

    @Override
    public List<LeaveLeft> listAllLeaveLefts(Connection connection) throws Exception{
        String query;

        query = "SELECT id,userid,empid,annualLeaveLeft,sickLeaveLeft,privateLeaveLeft,otherLeaveLeft,year,description FROM leavelefts";
        
        ArrayList<LeaveLeft> leaveleftData = new ArrayList<LeaveLeft>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the leaveleft object
            LeaveLeft leaveleft = new LeaveLeft();
            leaveleft.setId(result.getLong("id"));
            leaveleft.setUserId(result.getLong("userid"));
            leaveleft.setEmpId(result.getLong("empid"));
            leaveleft.setAnnualLeaveLeft(result.getInt("annualLeaveLeft"));
            leaveleft.setSickLeaveLeft(result.getInt("sickLeaveLeft"));
            leaveleft.setPrivateLeaveLeft(result.getInt("privateLeaveLeft"));
            leaveleft.setOtherLeaveLeft(result.getInt("otherLeaveLeft"));
            leaveleft.setYear(result.getInt("year"));
            leaveleft.setDescription(result.getString("description"));
            
            //Build the object list
            leaveleftData.add(leaveleft);
        }
        return leaveleftData;
    }    

    @Override
    public boolean updateLeaveLeft(Connection connection,LeaveLeft leaveleft) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(leaveleft != null) {
	            query0 = "SELECT count(id) as countNum FROM leavelefts where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, leaveleft.getId());
	
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "LeaveLeft not exists.";
	                return false;
	            }
	            else {
	            	query = "update leavelefts set userid=?,  empid=?, annualLeaveLeft=?,  sickLeaveLeft=?, privateLeaveLeft=? , otherLeaveLeft=?, year=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setLong(1, leaveleft.getUserId());
	            	ps.setLong(2, leaveleft.getEmpId());
	            	ps.setInt(3, leaveleft.getAnnualLeaveLeft());
	            	ps.setInt(4, leaveleft.getSickLeaveLeft());
	            	ps.setInt(5, leaveleft.getPrivateLeaveLeft());
	            	ps.setInt(6, leaveleft.getOtherLeaveLeft());
	            	ps.setInt(7,leaveleft.getYear());
	            	ps.setString(8,leaveleft.getDescription());
	            	ps.setLong(9, leaveleft.getId());
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
	public LeaveLeft findLeaveLeftById(Connection connection,long id) throws Exception {
        String query;

        query = "SELECT id,userid,empid,annualLeaveLeft,sickLeaveLeft,privateLeaveLeft,otherLeaveLeft,year,description FROM leavelefts WHERE ID=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        LeaveLeft  leaveleft = new LeaveLeft();
        ResultSet result = ps.executeQuery();

        while(result.next()) {            
        	//Build the  leaveleft object 
             leaveleft.setId(result.getLong("id"));
             leaveleft.setUserId(result.getLong("userid"));
             leaveleft.setEmpId(result.getLong("empid"));
             leaveleft.setAnnualLeaveLeft(result.getInt("annualLeaveLeft"));
             leaveleft.setSickLeaveLeft(result.getInt("sickLeaveLeft"));
             leaveleft.setPrivateLeaveLeft(result.getInt("privateLeaveLeft"));
             leaveleft.setOtherLeaveLeft(result.getInt("otherLeaveLeft"));
             leaveleft.setYear(result.getInt("year"));
             leaveleft.setDescription(result.getString("description"));
        }
        return  leaveleft;
	}
	

public List<LeaveLeft> getLeaveLeftListByEmpid(Connection connection,long empid) throws Exception{
    String query;
    
    try{
	    query = "SELECT id,userid,empid,annualLeaveLeft,sickLeaveLeft,privateLeaveLeft,otherLeaveLeft,year,description FROM leavelefts WHERE empid=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setLong(1,empid);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<LeaveLeft> leaveleftList = new ArrayList<LeaveLeft>();
	    while(result.next()) {            
	    	//Build the  leaveleft object
	    	 LeaveLeft  leaveleft = new LeaveLeft();
	        
	    	 leaveleft.setId(result.getLong("id"));
	         leaveleft.setUserId(result.getLong("userid"));
	         leaveleft.setEmpId(result.getLong("empid"));
	         leaveleft.setAnnualLeaveLeft(result.getInt("annualLeaveLeft"));
	         leaveleft.setSickLeaveLeft(result.getInt("sickLeaveLeft"));
	         leaveleft.setPrivateLeaveLeft(result.getInt("privateLeaveLeft"));
	         leaveleft.setOtherLeaveLeft(result.getInt("otherLeaveLeft"));
	         leaveleft.setYear(result.getInt("year"));
	         leaveleft.setDescription(result.getString("description"));
	        
	         leaveleftList.add(leaveleft);        
	    }
	    return leaveleftList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());
	    }finally{
	    }
	}

	@Override
	public boolean createLeaveLeft(Connection connection,LeaveLeft leaveleft) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = leaveleft.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(*) as countNum FROM leavelefts where id='"+ id +"';";	    		
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
	                query = "insert into leavelefts (userid,empid,annualleaveleft,sickleaveleft,privateleaveleft,otherleaveleft,year,description) values (?,?,?,?,?,?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setLong(1, leaveleft.getUserId());
	                ps.setLong(2, leaveleft.getEmpId());
	                ps.setInt(3, leaveleft.getAnnualLeaveLeft());
	                ps.setInt(4, leaveleft.getSickLeaveLeft());
	                ps.setInt(5, leaveleft.getPrivateLeaveLeft());
	                ps.setInt(6, leaveleft.getOtherLeaveLeft());
	                ps.setInt(7, leaveleft.getYear());
	                ps.setString(8, leaveleft.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteLeaveLeft(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from leavelefts where id=?;";
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
