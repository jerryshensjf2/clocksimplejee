package com.javaforever.clocksimplejee4.daoimpl;

import com.javaforever.clocksimplejee4.dao.BonusDao;
import com.javaforever.clocksimplejee4.database.DBConf;
import com.javaforever.clocksimplejee4.domain.Bonus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class BonusDaoImpl implements BonusDao {
    
    @Override
    public List<Bonus> listAllBonus(Connection connection) throws Exception{
        String query;

        query = "SELECT id,userid,empId,reason,bonus_balance,Description FROM bonus";
        
        ArrayList<Bonus> bonusData = new ArrayList<Bonus>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            
        	//Build the bonus object
            Bonus bonus = new Bonus();
            bonus.setId(result.getLong("id"));
            bonus.setUserId(result.getLong("userid"));
            bonus.setEmpId(result.getLong("empId"));
            bonus.setReason(result.getString("reason"));
            bonus.setBonusBalance(result.getBigDecimal("bonus_balance"));
            bonus.setDescription(result.getString("Description"));
            //Build the object list
            bonusData.add(bonus);
        }
        return bonusData;
    }    

    @Override
    public boolean updateBonus(Connection connection,Bonus bonus) throws Exception {
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	
	    try {
	        if(bonus!=null) {
	            query0 = "SELECT count(id) as countNum FROM bonus where id=?;";
	            PreparedStatement ps = connection.prepareStatement(query0);
	            ps.setLong(1,bonus.getId() );
	
	            ResultSet result0 = ps.executeQuery();
	            if (result0.next()){
	            	count = result0.getInt("countNum");
	            }
	            
	            if (count == 0) {
	            	//errorMessage = "Bonus not exists.";
	                return false;
	            }
	            else {
	                query = "update bonus set userid=?,  empid=?, reason=?,bonus_balance=?, description=? where id=?;";
	                PreparedStatement ps1 = connection.prepareStatement(query);
	                ps1.setLong(1, bonus.getUserId());
	                ps1.setLong(2, bonus.getEmpId());
	                ps1.setString(3, bonus.getReason());
	                ps1.setBigDecimal(4,bonus.getBonusBalance());
	                ps1.setString(5,bonus.getDescription());
	                ps1.setLong(6,bonus.getId());
	                result = ps1.executeUpdate();
	                return true;
	            }
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	    return false;
    }

    public Bonus findBonusByReason(Connection connection,String reason) throws IOException{
	    String query = "";
	    
	    try{
	    query = "SELECT id,userid,empId,reason,bonus_balance,description FROM bonus WHERE reason=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setString(1, reason);
	    ResultSet result = ps.executeQuery();
	    
	    Bonus bonus = new Bonus();
	    while(result.next()) {            	
	    	//Build the bonus object
	        bonus.setId(result.getLong("id"));
	        bonus.setUserId(result.getLong("userid"));
	        bonus.setEmpId(result.getLong("empId"));   
	        bonus.setReason(result.getString("reason"));
	        bonus.setBonusBalance(result.getBigDecimal("bonus_balance"));
	        bonus.setDescription(result.getString("description"));        
	    }
	    return bonus;
		    }catch(Exception e){
		    	e.printStackTrace();
		    	throw new IOException(e.getMessage());
		    }finally{
	    }
	}


public List<Bonus> getBonusListByEmpid(Connection connection,long empid) throws IOException{
    String query;
    
    try{
	    query = "SELECT id,userid,empId,reason,bonus_balance,description FROM bonus WHERE empid=?;";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setLong(1, empid);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
	    while(result.next()) {            
	    	//Build the bonus object
	    	Bonus bonus = new Bonus();
	        bonus.setId(result.getLong("id"));
	        bonus.setUserId(result.getLong("userid"));
	        bonus.setEmpId(result.getLong("empId"));
	        bonus.setReason(result.getString("reason"));
	        bonus.setBonusBalance(result.getBigDecimal("bonus_balance"));
	        bonus.setDescription(result.getString("description"));
	        
	        bonusList.add(bonus);        
	    }
	    return bonusList;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw new IOException(e.getMessage());
	    }finally{
	    }
	}
	
	@Override
	public boolean addBonusBalance(Connection connection,Bonus bonus, BigDecimal quantity)
			throws Exception {
        String query;

        query = "SELECT id,userid,empId,reason,bonus_balance,description FROM bonus WHERE ID=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1,bonus.getId());
        Bonus bonus0 = new Bonus();
        ResultSet result = ps.executeQuery();

        while(result.next()) {            
        	//Build the bonus object            
            bonus0.setId(result.getLong("id"));
            bonus0.setUserId(result.getLong("userid"));
            bonus0.setEmpId(result.getLong("empId"));
            bonus0.setReason(result.getString("reason"));
            bonus0.setBonusBalance(result.getBigDecimal("bonus_balance"));
            bonus0.setDescription(result.getString("description"));
        }
        
        bonus0.setBonusBalance(bonus0.getBonusBalance().add(quantity));
        String errorMessage = "";
        updateBonus(connection,bonus0);

		return false;
	}

	@Override
	public boolean createBonus(Connection connection,Bonus bonus) throws Exception {		
	    String query = "";
	    String query0 = "";
	    int result = 0;
	    int count = 0;
	    long id = bonus.getId();
	
	    try {
	    		if (id > 0){
	    			query0 = "SELECT count(id) as countNum FROM bonus where id=?;";	    		
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
	                query = "insert into bonus (userid,empid,reason,bonus_balance,description) values (?,?,?,?,?);";
	                PreparedStatement ps = connection.prepareStatement(query);
	                ps.setLong(1, bonus.getUserId());
	                ps.setLong(2,bonus.getEmpId());
	                ps.setString(3, bonus.getReason());
	                ps.setBigDecimal(4, bonus.getBonusBalance());
	                ps.setString(5, bonus.getDescription());
	                System.out.println(query);
	                result = ps.executeUpdate();
	                return true;
	        }
	     } catch(SQLException e){
	         throw new IOException(e.getMessage());
	     }
	}

	@Override
	public boolean deleteBonus(Connection connection,long id) throws Exception {
	    String query = "";
	    int result = 0;
	    boolean retVal = false;
	
	    try {
            query = "delete from bonus where id=?;";
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
	public Bonus findBonusById(Connection connection,long id) throws Exception {
	        String query;

	        query = "SELECT id,userid,empId,reason,bonus_balance,description FROM bonus WHERE ID=?;";

	        Bonus bonus = new Bonus();
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setLong(1, id);
	        ResultSet result = ps.executeQuery();

	        while(result.next()) {            
	        	//Build the bonus object            
	            bonus.setId(result.getLong("id"));
	            bonus.setUserId(result.getLong("userid"));
	            bonus.setEmpId(result.getLong("empId"));
	            bonus.setReason(result.getString("reason"));
	            bonus.setBonusBalance(result.getBigDecimal("bonus_balance"));
	            bonus.setDescription(result.getString("description"));
	        }
	        return bonus;
	}

}
