package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.FineDao;
import com.javaforever.clocksimplejee4.domain.Fine;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class FineDaoImpl implements FineDao {

    @Override
    public List<Fine> listAllFine(Connection connection) throws Exception{
        String query;

        query = "SELECT id,userid,empId,reason,fine_balance,Description FROM fine";
        
        ArrayList<Fine> fineData = new ArrayList<Fine>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the fine object
            Fine fine = new Fine();
            fine.setId(result.getLong("id"));
            fine.setUserId(result.getLong("userid"));
            fine.setEmpId(result.getLong("empId"));
            fine.setReason(result.getString("reason"));
            fine.setFineBalance(result.getBigDecimal("fine_balance"));
            fine.setDescription(result.getString("Description"));
            //Build the object list
            fineData.add(fine);
        }
        return fineData;
    }    

    @Override
    public boolean updateFine(Connection connection,Fine fine) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(fine!=null) {
	            query0 = "SELECT count(id) as countNum FROM fine where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, fine.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "Fine not exists.";
	                return false;
	            }
	            else {
	                query = "update fine set userid=?,  empid=?, reason=?,fine_balance=?, description=? where id=?;";
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setLong(1,fine.getUserId());
	                ps.setLong(2, fine.getEmpId());
	                ps.setString(3, fine.getReason());
	                ps.setBigDecimal(4, fine.getFineBalance());
	                ps.setString(5,fine.getDescription());
	                ps.setLong(6, fine.getId());
	                result = ps.executeUpdate();
	                return true;
	            }
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	    return false;
    }

    public Fine findFineByReason(Connection connection,String reason) throws IOException{
	    String query = "";
	    
	    try{
	    query = "SELECT id,userid,empId,reason,fine_balance,description FROM fine WHERE reason=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1,reason);
	    ResultSet result = ps.executeQuery();
	    
	    Fine fine = new Fine();
	    while(result.next()) {            	
	    	//Build the fine object
	        fine.setId(result.getLong("id"));
	        fine.setUserId(result.getLong("userid"));
	        fine.setEmpId(result.getLong("empId"));   
	        fine.setReason(result.getString("reason"));
	        fine.setFineBalance(result.getBigDecimal("fine_balance"));
	        fine.setDescription(result.getString("description"));        
	    }
	    return fine;
		    }catch(Exception e){
		    	e.printStackTrace();
		    	throw new IOException(e.getMessage());
		    }finally{
	    }
	}


public List<Fine> getFineListByEmpid(Connection connection,long empid) throws IOException{
    String query;
    
    try{
	    query = "SELECT id,userid,empId,reason,fine_balance,description FROM fine WHERE empid=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setLong(1, empid);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<Fine> fineList = new ArrayList<Fine>();
	    while(result.next()) {            
	    	//Build the fine object
	    	Fine fine = new Fine();
	        fine.setId(result.getLong("id"));
	        fine.setUserId(result.getLong("userid"));
	        fine.setEmpId(result.getLong("empId"));
	        fine.setReason(result.getString("reason"));
	        fine.setFineBalance(result.getBigDecimal("fine_balance"));
	        fine.setDescription(result.getString("description"));
	        
	        fineList.add(fine);        
	    }
	    return fineList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new IOException(e.getMessage());
	    }finally{
	    }
	}
	
	@Override
	public boolean addFineBalance(Connection connection,Fine fine, BigDecimal quantity)
			throws Exception {
        String query;

        query = "SELECT id,userid,empId,reason,fine_balance,description FROM fine WHERE ID=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, fine.getId());
        Fine fine0 = new Fine();
        ResultSet result = ps.executeQuery();

        while(result.next()) {            
        	//Build the fine object            
            fine0.setId(result.getLong("id"));
            fine0.setUserId(result.getLong("userid"));
            fine0.setEmpId(result.getLong("empId"));
            fine0.setReason(result.getString("reason"));
            fine0.setFineBalance(result.getBigDecimal("fine_balance"));
            fine0.setDescription(result.getString("description"));
        }
        
        fine0.setFineBalance(fine0.getFineBalance().add(quantity));
        updateFine(connection,fine0);
		return false;
	}

	@Override
	public boolean createFine(Connection connection,Fine fine) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = fine.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM fine where id=?;";	    		
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
	                query = "insert into fine (userid,empid,reason,fine_balance,description) values (?,?,?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setLong(1, fine.getUserId());
	                ps.setLong(2, fine.getEmpId());
	                ps.setString(3,fine.getReason());
	                ps.setBigDecimal(4, fine.getFineBalance());
	                ps.setString(5, fine.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteFine(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from fine where id=?;";
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

	@Override
	public Fine findFineById(Connection connection,long id) throws Exception {
	        String query;

	        query = "SELECT id,userid,empId,reason,fine_balance,description FROM fine WHERE ID=?";
	        PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
	        Fine fine = new Fine();
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	//Build the fine object            
	            fine.setId(result.getLong("id"));
	            fine.setUserId(result.getLong("userid"));
	            fine.setEmpId(result.getLong("empId"));
	            fine.setReason(result.getString("reason"));
	            fine.setFineBalance(result.getBigDecimal("fine_balance"));
	            fine.setDescription(result.getString("description"));
	        }
	        return fine;
	}

}
