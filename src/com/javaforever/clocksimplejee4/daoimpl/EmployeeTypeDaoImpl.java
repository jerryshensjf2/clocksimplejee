package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.EmployeeTypeDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.EmployeeType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeTypeDaoImpl implements EmployeeTypeDao {

    @Override
    public List<EmployeeType> listAllEmployeeTypes(Connection connection) throws Exception{
        String query;

        query = "SELECT id,employeetypename,description FROM employeetypes";
        
        ArrayList<EmployeeType> employeetypeData = new ArrayList<EmployeeType>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the leaveleft object
            EmployeeType employeetype = new EmployeeType();
            employeetype.setId(result.getLong("id"));
            employeetype.setEmployeeTypeName(result.getString("employeetypename"));
            employeetype.setDescription(result.getString("description"));
            
            //Build the object list
            employeetypeData.add(employeetype);
        }
        return employeetypeData;
    }    

    @Override
    public boolean updateEmployeeType(Connection connection,EmployeeType employeetype) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(employeetype != null) {
	            query0 = "SELECT count(id) as countNum FROM employeetypes where id=?;";
	            PreparedStatement ps0 = connection.prepareStatement(query0);
	            ps0.setLong(1, employeetype.getId());
	            ResultSet result0 = ps0.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "EmployeeType not exists.";
	                return false;
	            }
	            else {
	            	query = "update employeetypes set employeetypename=?, description=? where id=?;";
	            	PreparedStatement ps = connection.prepareStatement(query);
	            	ps.setString(1, employeetype.getEmployeeTypeName());
	            	ps.setString(2, employeetype.getDescription());
	            	ps.setLong(3, employeetype.getId());
	            	System.out.println(query);
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
	public EmployeeType findEmployeeTypeById(Connection connection,long id) throws Exception {
	        String query;

	        query = "SELECT id,employeetypename,description FROM employeetypes WHERE ID=?;";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setLong(1, id);
	        EmployeeType  employeetype = new EmployeeType();
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	//Build the  employeetype object 
	            employeetype.setId(result.getLong("id"));
	            employeetype.setEmployeeTypeName(result.getString("employeetypename"));
	            employeetype.setDescription(result.getString("description"));
	        }
	        return  employeetype;
	}
	

public EmployeeType findEmployeeTypeByEmployeeTypeName(Connection connection,String employeeTypeName) throws IOException{
    String query;
    
    try{
	    query = "SELECT id,employeetypename,description FROM employeetypes WHERE employeeTypeName=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, employeeTypeName);
	    ResultSet result = ps.executeQuery();
	    
	    EmployeeType employeetype = new EmployeeType();
	    if(result.next()) {            
	    	//Build the  employeetype object        
            employeetype.setId(result.getLong("id"));
            employeetype.setEmployeeTypeName(result.getString("employeetypename"));
            employeetype.setDescription(result.getString("description"));
      
	    }
	    return employeetype;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new IOException(e.getMessage());
	    }finally{
	    }
	}

	@Override
	public boolean createEmployeeType(Connection connection,EmployeeType employeetype) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = employeetype.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM employeetypes where id=?;";	    		
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
	                query = "insert into employeetypes (employeetypename,description) values (?,?);";
	                System.out.println(query);
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setString(1,employeetype.getEmployeeTypeName());
	                ps.setString(2,employeetype.getDescription());
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteEmployeeType(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from employeetypes where id=?;";
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
