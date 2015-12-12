package com.javaforever.clocksimplejee4.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.PrivilegeDao;
import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.domain.User;
import java.sql.PreparedStatement;

public class PrivilegeDaoImpl implements PrivilegeDao {
    
    public boolean canAccess(Connection connection,User user,String url) throws Exception { 
        ResultSet result;
        boolean canAccess = false;
        String query = "SELECT isadmin,url,candelete,id FROM  privilege WHERE url=?;";
    	PreparedStatement ps = connection.prepareStatement(query);
    	ps.setString(1,url);
    	result = ps.executeQuery();
    	while (result.next()){
    		Privilege priv = new Privilege();
    		priv.setIsadmin(result.getString("isadmin"));
    		priv.setUrl(result.getString("url"));
    		priv.setCanDelete(result.getString("candelete"));
    		priv.setId(result.getLong("id"));
    		if ("Y".equalsIgnoreCase(priv.getIsadmin())){
    			if ("Y".equalsIgnoreCase(user.getIsadmin())){
    				canAccess = true;
    			} else {
    				canAccess = false;
    			}
    		} else if ("N".equalsIgnoreCase(priv.getIsadmin())) {
    			canAccess = true;
    		}
    	}

    	return canAccess;
    }
    
    public boolean adminAddPrivilege(Connection connection,Privilege privilege) throws Exception{
        String query = "";
        int result;
     	query = "insert into privilege (isadmin,candelete,url) values(?,?,?);";
     	PreparedStatement ps = connection.prepareStatement(query);
     	ps.setString(1, privilege.getIsadmin());
     	ps.setString(2, privilege.getCanDelete());
     	ps.setString(3, privilege.getUrl());
     	result = ps.executeUpdate();
     	
     	if (result > 0) {
     		return true;
     	}
     	return false;
    }
    
    public boolean adminDeletePrivilege(Connection connection,Privilege privilege) throws Exception{
        String query = "";
        int result;
     	query = "delete from privilege where id = ? and candelete='Y';";
     	PreparedStatement ps = connection.prepareStatement(query);
     	ps.setLong(1, privilege.getId());
     	result = ps.executeUpdate();
     	
     	System.out.println("Jerry Debug::" + result);
     	
     	if (result > 0) {
     		return true;
     	}
     	return false;
    }
    
    public boolean adminTogglePrivilegeCanDelete(Connection connection,Privilege privilege) throws Exception{
    	String query0 = "";
        String query = "";
        ResultSet result0;
        int result;
        
        query0 = "select isadmin,url,candelete,id from privilege where id=?;";
        PreparedStatement ps0 = connection.prepareStatement(query0);
        ps0.setLong(1, privilege.getId());
        result0 = ps0.executeQuery();
        while(result0.next()){
        	privilege.setIsadmin(result0.getString("isadmin"));
        	privilege.setCanDelete(result0.getString("candelete"));
        	privilege.setUrl(result0.getString("url")); 
        	if ("Y".equals(privilege.getCanDelete())){
        		privilege.setCanDelete("N");
        	}else if ("N".equals(privilege.getCanDelete())){
        		privilege.setCanDelete("Y");
        	}else {
        		privilege.setCanDelete("N");
        	}
        }
        
     	query = "update privilege set candelete='"+privilege.getCanDelete()+"' where id='"+privilege.getId()+"';";
     	result = connection.prepareStatement(query).executeUpdate();
     	
     	if (result > 0) {
     		return true;
     	}
     	return false;
    }
    
    public boolean adminTogglePrivilegeIsAdmin(Connection connection,Privilege privilege) throws Exception{
    	String query0 = "";
        String query = "";
        ResultSet result0;
        int result;
        
        query0 = "select isadmin,url,candelete,id from privilege where id=?;";
        PreparedStatement ps0 = connection.prepareStatement(query0);
        ps0.setLong(1, privilege.getId());
        result0 = ps0.executeQuery();
        while(result0.next()){
        	privilege.setIsadmin(result0.getString("isadmin"));
        	privilege.setCanDelete(result0.getString("candelete"));
        	privilege.setUrl(result0.getString("url")); 
        	if ("Y".equals(privilege.getIsadmin())){
        		privilege.setIsadmin("N");
        	}else if ("N".equals(privilege.getIsadmin())){
        		privilege.setIsadmin("Y");
        	}else {
        		privilege.setIsadmin("N");
        	}
        }
        
     	query = "update privilege set isadmin=? where id=?;";
     	PreparedStatement ps = connection.prepareStatement(query);
     	ps.setString(1, privilege.getIsadmin());
     	ps.setLong(2, privilege.getId());
     	result = ps.executeUpdate();
     	
     	if (result > 0) {
     		return true;
     	}
    	return false;
    }
    
    public List<Privilege> getPrivilegeList(Connection connection) throws Exception {   	
        String query = "select isadmin,url,candelete,id from privilege;";
        ResultSet result;
        ArrayList<Privilege> privilegeList = new ArrayList<Privilege>(); 
    	
    	result = connection.prepareStatement(query).executeQuery();
    	while (result.next()){
    		Privilege priv = new Privilege();
    		priv.setCanDelete(result.getString("candelete"));
    		priv.setId(result.getLong("id"));
    		priv.setIsadmin(result.getString("isadmin"));
    		priv.setUrl(result.getString("url"));
    		privilegeList.add(priv);
    	}
    	return privilegeList;
    }
    
    public Privilege getPrivilegeByUrl(Connection connection,Privilege privilege) throws Exception {
        String query = "select isadmin,url,candelete,id from privilege where url=?;";
        PreparedStatement ps = connection.prepareStatement(query);        
        ps.setString(1, privilege.getUrl());
        Privilege priv = new Privilege();
        
    	ResultSet result = ps.executeQuery();
    	while (result.next()){    	
    		priv.setId(result.getLong("id"));
    		priv.setCanDelete(result.getString("candelete"));    		
    		priv.setIsadmin(result.getString("isadmin"));
    		priv.setUrl(result.getString("url"));
    	}    	
    	return priv;
    }

	public boolean adminEditPrivilege(Connection connection,Privilege priv)
			throws Exception {
		    String query;
		    String query0;
		    int result = 0;
		    int count = 0;
		    boolean success = false;

		    try {
		        if(priv!=null) {
		            query0 = "SELECT count(id) as countNum FROM privilege where id=?;";
		            PreparedStatement ps0 = connection.prepareStatement(query0);  
		            ps0.setLong(1,priv.getId());
		            ResultSet result0 = ps0.executeQuery();
		            if (result0.next()){
		            	count = result0.getInt("countNum");
		            }
		            
		            if (count > 0) {
		            	query = "update privilege set url=?, isadmin =?, canDelete=? where id=?;";
		                //System.out.println(query);
		            	PreparedStatement ps = connection.prepareStatement(query); 
		            	ps.setString(1,priv.getUrl());
		            	ps.setString(2,priv.getIsadmin());
		            	ps.setString(3, priv.getCanDelete());
		            	ps.setLong(4, priv.getId());
		                result = ps.executeUpdate();
		                success = true;
		            }
		        }
		     } catch(SQLException e){
		         throw new Exception(e.getMessage());
		     }
		    return success;
		}

}
