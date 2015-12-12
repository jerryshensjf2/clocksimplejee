package com.javaforever.clocksimplejee4.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaforever.clocksimplejee4.dao.UserDao;
import com.javaforever.clocksimplejee4.domain.User;

public class UserDaoImpl implements UserDao {

    public List<User> listAllUsers(Connection connection) throws Exception{
        String query;
        query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users;";

        List<User> userData = new ArrayList<User>();

        ResultSet result = connection.prepareStatement(query).executeQuery();

        while(result.next()) {            //Build the user object
            User user = new User();
            user.setId(result.getLong("id"));
            user.setEmpid(result.getLong("empId"));
            user.setUsername(result.getString("userName"));
            user.setFirstname(result.getString("firstName"));
            user.setLastname(result.getString("lastName"));
            user.setSex(result.getString("sex"));
            user.setPassword(result.getString("password"));
            user.setIsadmin(result.getString("isadmin"));
            user.setIsactive(result.getString("isactive"));
            user.setAddress(result.getString("address"));
            user.setAddress1(result.getString("address1"));
            user.setNamec(result.getString("name_c"));
            user.setNamej(result.getString("name_j"));
            user.setPhone(result.getString("phone"));
            user.setMobile(result.getString("mobile"));
            user.setLoginfailure(result.getInt("login_failure"));

            //Build the object list
            userData.add(user);
        }
        return userData;
    }    
    
    public User findUserById(Connection connection,long id) throws Exception{
        String query;

        query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users WHERE ID=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        ArrayList<User> userData = new ArrayList<User>();

        ResultSet result = ps.executeQuery();
        
        User user = new User();
        while(result.next()) {  //Build the user object         
            
            user.setId(result.getLong("id"));
            user.setEmpid(result.getLong("empId"));
            user.setUsername(result.getString("userName"));
            user.setFirstname(result.getString("firstName"));
            user.setLastname(result.getString("lastName"));
            user.setSex(result.getString("sex"));
            user.setPassword(result.getString("password"));
            user.setIsadmin(result.getString("isadmin"));
            user.setIsactive(result.getString("isactive"));
            user.setAddress(result.getString("address"));
            user.setAddress1(result.getString("address1"));
            user.setNamec(result.getString("name_c"));
            user.setNamej(result.getString("name_j"));
            user.setPhone(result.getString("phone"));
            user.setMobile(result.getString("mobile"));
            user.setLoginfailure(result.getInt("login_failure"));
        }
        return user;
    }    

 public boolean editUser(Connection connection,User user) throws Exception {
    String query;
    String query0;
    int result = 0;
    int count = 0;

    try {
        if(user!=null) {
            query0 = "SELECT count(id) as countNum FROM users where id<>? and  empid=? or username=? and id<>?;";
            PreparedStatement ps0 = connection.prepareStatement(query0);
            ps0.setLong(1, user.getId());
            ps0.setLong(2, user.getEmpid());
            ps0.setString(3, user.getUsername());
            ps0.setLong(4,user.getId());
            ResultSet result0 = ps0.executeQuery();
            if (result0.next()){
            	count = result0.getInt("countNum");
            }
            
            if (count > 0) {
            	//errorMessage = "User already exists.";
                return false;
            }
            else {
                query = "update users set username=?,  firstname=?, lastname=?,sex=?,empid=?,name_c=?, name_j=?, address= ? ," + 
                		" address1=?, phone=?, mobile=?, isadmin=?, isactive=? where id=?;";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1,user.getUsername());
                ps.setString(2, user.getFirstname());
                ps.setString(3, user.getLastname());
                ps.setString(4, user.getSex());
                ps.setLong(5, user.getEmpid());
                ps.setString(6, user.getNamec());
                ps.setString(7, user.getNamej());
                ps.setString(8, user.getAddress());
                ps.setString(9, user.getAddress1());
                ps.setString(10, user.getPhone());
                ps.setString(11, user.getMobile());
                ps.setString(12, user.getIsadmin());
                ps.setString(13, user.getIsactive());
                ps.setLong(14, user.getId());
                result = ps.executeUpdate();
                return true;
            }
        }
     } catch(SQLException e){
         throw new Exception(e.getMessage());
     }
    return false;
}

 public  String getPinViaId(Connection connection,long userid) throws Exception{
    String query = "select pin from users where id=?;";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setLong(1, userid);
    ResultSet result = ps.executeQuery();    
    
    while (result.next()){
        return result.getString("pin");
    }
    return "";
}

 public  boolean login(Connection connection,User user)  throws Exception{
    String query0 = "";
    String query = "";
    String query2 = "";
    String pin="";
    ResultSet result0;
    
    System.out.println("Jerry UserDaoImpl.login()");
    PreparedStatement ps;
    PreparedStatement ps2; 
    try {
	    if(user!=null && user.getEmpid() > 0L) {
	        query0 = "select pin from users where empid=?;";
	        PreparedStatement ps0 = connection.prepareStatement(query0);
	        ps0.setLong(1,user.getEmpid());
	        result0 = ps0.executeQuery();
		    if (result0.next()) {
		        pin = result0.getString("pin");
		    }
			else {
		        //errorMessage = "Can not find correct user.";
		        return false;
			}
	        query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users where empid=? and password=sha1(?) and isactive='Y'"; 
	        ps = connection.prepareStatement(query);
	        ps.setLong(1, user.getEmpid());
	        ps.setString(2, user.getPassword()+ pin);
	        query2 = "SELECT count(*) as countNum FROM users where empid=? and password=sha1(?) and isactive='Y'";
	        ps2 = connection.prepareStatement(query2);
	        ps2.setLong(1, user.getEmpid());
	        ps2.setString(2, user.getPassword()+ pin);
	    }
	    else {
	    	//errorMessage = "user is empty.";
	        return false;
	    }

	    ResultSet result = ps.executeQuery();
	    ResultSet result2 = ps2.executeQuery();
	    
	    int count = 0;
	    while(result2.next()){
	    	count= result2.getInt("countNum");
	    }
    
	    if (result.next()&& count > 0) {
   	
	    	user.setId(result.getLong("id"));
            user.setEmpid(result.getLong("empid"));
            user.setUsername(result.getString("username"));
            user.setFirstname(result.getString("firstname"));
            user.setLastname(result.getString("lastname"));
            user.setSex(result.getString("sex"));
            user.setAddress(result.getString("address"));
            user.setAddress1(result.getString("address1"));
            user.setNamec(result.getString("name_c"));
            user.setNamej(result.getString("name_j"));
            user.setPhone(result.getString("phone"));
            user.setMobile(result.getString("mobile"));
            user.setIsadmin(result.getString("isadmin"));
            user.setIsactive(result.getString("isactive"));
	    	user.setIsadmin(result.getString("isadmin"));
	    	    	
	        clearLoginFailure(connection,user);
	        
	        System.out.println("Jerry login():isadmin:" + user.getIsadmin());
	        System.out.println("Jerry login():islogin:" + "Y");
	        System.out.println("Jerry login():islogin:" + user.getUsername());
	        
	        return true;
	    }else {
	        addLoginFailure(connection,user);
	        return false;
	    }
     } catch(Exception e){
    	 System.out.println(e.getStackTrace());
         throw new Exception(e.getMessage());
     } finally {
     }
}

 public  void addLoginFailure(Connection connection,User user) throws Exception{
        int loginfailure = 0;
        String query = "select login_failure from users where empid=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, user.getEmpid());
        ResultSet result = ps.executeQuery();
        if (result.next()){
 			loginfailure = result.getInt("login_failure");
		}

        String query1 = "update users set login_failure=? where empid=?;";
        PreparedStatement ps1 = connection.prepareStatement(query1);
        ps1.setInt(1, loginfailure+1);
        ps1.setLong(2, user.getEmpid());
        int result1 = ps1.executeUpdate();
        
        if (loginfailure>=5){
            String query2 = "update users set isactive='N', login_failure=? where empid=?;";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setInt(1,loginfailure+1);
            ps2.setLong(2,user.getEmpid());
            int result2 = ps2.executeUpdate();
        }
}

 public  void addLoginFailureViaUsername(Connection connection,User user) throws Exception {
        int loginfailure = 0;
        String query = "select login_failure from users where username=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ResultSet result = ps.executeQuery();
        while (result.next()){
 			loginfailure = result.getInt("login_failure");
		}

        String query1 = "update users set login_failure=? where username=?;";
        PreparedStatement ps1 = connection.prepareStatement(query1);
        ps1.setInt(1, loginfailure+1);
        ps1.setString(2, user.getUsername());
        int result1 = ps1.executeUpdate();
        if (loginfailure>=5){
            String query2 = "update users set isactive='N',login_failure=? where username=?;";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setInt(1,loginfailure+1);
            ps2.setString(2,user.getUsername());
            int result2 = ps2.executeUpdate();
        }
}

 public  void clearLoginFailure(Connection connection,User user) throws Exception {
    String query = "update users set login_failure='0' where empid=?;";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setLong(1,user.getEmpid());
    int result = ps.executeUpdate();
}

 public  boolean loginViaUserNamePassword(Connection connection,User user) throws Exception{
    String query0;
    String query;
    String query2;
    String pin="";
    int count=0;
    PreparedStatement ps0;
    PreparedStatement ps;
    PreparedStatement ps2;
    try {
        if(user!=null) {
            query0 = "select pin from users where username=?;";
            ps0 = connection.prepareStatement(query0);
            ps0.setString(1, user.getUsername());
            ResultSet result0 = ps0.executeQuery();
        while(result0.next()) {
            pin = result0.getString("pin");
        }

        if(user!=null) {
            query = "SELECT * FROM users where username=? and password=sha1(?);";
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()+pin);
            query2 = "SELECT count(*) as countNum FROM users where username=? and password=sha1(?);";
            ps2 = connection.prepareStatement(query2);
            ps2.setString(1, user.getUsername());
            ps2.setString(2, user.getPassword()+pin);
            ResultSet result = ps.executeQuery();
            ResultSet result2 = ps2.executeQuery();
            
            while(result2.next()){
            	count = result2.getInt("countNum");
            }

            if (count >0){
	            while(result.next()) {
	            	user.setId(result.getLong("id"));
	                user.setEmpid(result.getLong("empid"));
	                user.setUsername(result.getString("username"));
	                user.setFirstname(result.getString("firstname"));
	                user.setLastname(result.getString("lastname"));
	                user.setSex(result.getString("sex"));
	                user.setAddress(result.getString("address"));
	                user.setAddress1(result.getString("address1"));
	                user.setNamec(result.getString("name_c"));
	                user.setNamej(result.getString("name_j"));
	                user.setPhone(result.getString("phone"));
	                user.setMobile(result.getString("mobile"));
	                user.setIsadmin(result.getString("isadmin"));
	                user.setIsactive(result.getString("isactive"));   	    	
	    	        
	    	        System.out.println("Jerry loginViaUsernamePassword():isadmin:" + user.getIsadmin());
	    	        System.out.println("Jerry loginViaUsernamePassword():islogin:" + "Y");
	    	        System.out.println("Jerry loginViaUsernamePassword():islogin:" + user.getUsername());
	    	        
	                clearLoginFailure(connection,user);
	                return true;
	            }
            }
            else {
                addLoginFailureViaUsername(connection,user);
                return false;
            }
        }
        }

        else {
            //errorMessage = "Can not find correct user.";
            return false;
            }
         }
         catch(Exception e){
             throw new Exception(e.getMessage());
        }
        finally {
        }
        return false;
    }


 public  boolean changePassword(Connection connection,User user,String oldpassword,String newpassword,String confirmnewpassword) throws Exception{
    String query="";
    String query2="";
    String pin="";
    String dbPassword = "";
    String dbPassword2 = "";
    int count = 0;

	if (!newpassword.equals(confirmnewpassword)) {
        //errorMessage = "New password not match.";
        return false;
    }
    else if (oldpassword.equals(newpassword)){
        //errorMessage = "New password is same as old one.";
        return false;
    }
    
try {
    String query0 = "select pin,password from users where empid=?;";
    PreparedStatement ps0 = connection.prepareStatement(query0);
    ps0.setLong(1, user.getEmpid());
    ResultSet result0 = ps0.executeQuery();
    if (result0.next()) {
        pin = result0.getString("pin");
        dbPassword = result0.getString("password");
        String passQuery = "select sha1(?) as password1;";
        PreparedStatement psPass = connection.prepareStatement(passQuery);
        psPass.setString(1, oldpassword +pin);
        ResultSet resultDB1 = psPass.executeQuery();
        if (resultDB1.next()) {
        	dbPassword2 = resultDB1.getString("password1");
        	System.out.println(dbPassword2);
        	System.out.println(dbPassword);
        	if (!dbPassword2.equals(dbPassword)){
        		//errorMessage = "Old password not match.";
        		return false;
        	}
        }
 
    }
	else {
        //errorMessage = "Can not find correct user.";
        return false;
	}
    

    	if(user!=null) {
	            query = "SELECT count(id) as countNum FROM users where id=? and password=sha1(?);";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setLong(1, user.getId());
	            ps.setString(2, oldpassword + pin );
	            ResultSet result = ps.executeQuery();
	            
	            while (result.next()){
	            	count = result.getInt("countNum");
	            }
	
	            if ( count > 0 ) {
	                query2 = "update users set password=sha1(?) where id = ?;";
	                PreparedStatement ps2 = connection.prepareStatement(query2);
	                ps2.setString(1, newpassword + pin);
	                ps2.setLong(2, user.getId());
	                int result2 = ps2.executeUpdate();
	                System.out.println(query2);
	                System.out.println("Jerry::ClockManager::changePassword::"+result2);
	                if (result2 > 0) {
	                    return true;
	                }else {
	                    //errorMessage = "Update password error.";
	                    return false;
	                }
	
	        }else {
	                //errorMessage = "Old password not match.";
	                return false;
	        }
    }else {
	        //errorMessage = "Old password not match." ;
	        return false;
	    }
    } catch (Exception e){
    	e.printStackTrace();
    	return false;
    }finally {
    }
}


 public  boolean registerUser(Connection connection,User user) throws Exception{
    if (!(user.getPassword()).equals(user.getConfirmpassword())) {
        //errorMessage = "Password not match.";
        return false;
    }

    try {
    String query;
    int count = 0;
    ResultSet result;

    if(user!=null) {
        query = "SELECT count(*) as countNum FROM users where empid=? or username=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, user.getEmpid());
        ps.setString(2, user.getUsername());
        result =  ps.executeQuery();
        while(result.next()){	        
	        count = result.getInt("countNum");
        }
        if (count>1) {
            //errorMessage = "User already exists.";
            return false;
        }else {
        	String pin = generatePinNum(connection);
            String query2 = "insert into users (empid,username,firstname,lastname,sex,password,pin,isactive, isadmin, name_c, name_j,address, address1,phone, mobile,login_failure) values (?,?,?,?,?,sha1(?),?,'Y','N',?,?,?,?,?,?,'0');";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setLong(1, user.getEmpid());
            ps2.setString(2, user.getUsername());
            ps2.setString(3, user.getFirstname());
            ps2.setString(4, user.getLastname());
            ps2.setString(5, user.getSex());
            ps2.setString(6, user.getPassword()+pin);
            ps2.setString(7, pin);
            ps2.setString(8, user.getNamec());
            ps2.setString(9, user.getNamej());
            ps2.setString(10, user.getAddress());
            ps2.setString(11, user.getAddress1());
            ps2.setString(12, user.getPhone());
            ps2.setString(13, user.getMobile());
            int result2 = ps2.executeUpdate();
            if (result2 > 0) {
            	user = getUserByUsername(connection,user);
                return true;
            } else {
                //errorMessage = "Register error.";
                return false;
            }
        }
        }else{
            //errorMessage="User is not set.";
            return false;
    }
     }
    catch (Exception e){
        throw new Exception(e.getMessage());
    }
    finally {
    }
}





public boolean adminChangeUserPassword(Connection connection,User user,String confirmnewpassword) throws Exception{
   if (!(user.getPassword()).equals(confirmnewpassword)) {
       //errorMessage = "New password not match.";
       return false;
   }       
   
   String query="";

   String pin = getPinbyUserID(connection,user.getId());            

   try {
	   query = "SELECT count(id) as countNum FROM users where id=?;";
	   PreparedStatement ps = connection.prepareStatement(query);
	   ps.setLong(1,user.getId());
       ResultSet result = ps.executeQuery();
       int count = 0;
       while (result.next()){
       	count = result.getInt("countNum");
       }
       
       if (count!=0 && count==1) {
           String query2 = "update users set password=sha1(?) where id=?;";
           PreparedStatement ps2 = connection.prepareStatement(query2);
           ps2.setString(1,user.getPassword()+pin);
           ps2.setLong(2, user.getId());
           int result2 = ps2.executeUpdate();
           if (result2 > 0) {
               return true;
           }else {
               //errorMessage = "Update password error.";
               return false;
           }

       }else {
           //errorMessage = "User did not exist.";
           return false;
       }
       
    }
   catch (SQLException e){
       throw new Exception(e.getMessage());
   }finally{
   }
}

public boolean adminDeleteUser(Connection connection,long id) throws Exception{
   String query;

   if(id != 0) {
       query = "delete from  users where id=?;";
   }
   else {
       System.out.println("The id is empty!");
       return false;
   }

   try {
	   PreparedStatement ps = connection.prepareStatement(query);
	   ps.setLong(1, id);
   int result = ps.executeUpdate();

   if (result > 0) {
       System.out.println("delete correct!");
       return true;
   }else {
       return false;
   }
    } catch (SQLException e){
        throw (new Exception(e.getMessage()));
    }
}

public boolean toggleAdmin(Connection connection,User user) throws Exception{
   String query;
   String query1;

   try {
   if(user!=null) {
   query = "SELECT id, isadmin from users where id=?;";
   query1 = "SELECT count(id) as countNum from users where isadmin='Y'";
   
   ResultSet result1 = connection.prepareStatement(query1).executeQuery();

   int countNum = 0;
   while (result1.next()){
   	countNum = result1.getInt("countNum");
   }
   
   PreparedStatement ps = connection.prepareStatement(query);
   ps.setLong(1, user.getId());
   ResultSet result =  ps.executeQuery();

   String isAdmin= "N";
   
   while (result.next()){
       isAdmin = result.getString("isadmin");
   }
   
   String isNewAdmin="N";
   
   if ("Y".equalsIgnoreCase(isAdmin)&&countNum > 1) {
       isNewAdmin="N";
   }else
   {
       isNewAdmin= "Y";
   }


   String query2 = "update users set isadmin=? where id=?;";
   PreparedStatement ps2 = connection.prepareStatement(query2);
   ps2.setString(1,isNewAdmin);
   ps2.setLong(2, user.getId());
   int result2 =  ps2.executeUpdate();
   if (result2 > 0) {
       return true;
   }else {
       //errorMessage = "Update isAdmin error.";
       return false;
       
   }

   }else {
       //errorMessage = "User did not exist.";
       return false;             
   } 
    }catch (Exception e){
        throw new Exception(e.getMessage());
    }
}

	public boolean toggleActive(Connection connection,User user)
			throws Exception {
		try {
			String query;
			String query1;

			if (user != null) {
				query = "SELECT id, isactive from users where id=?;";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setLong(1,user.getId());
				query1 = "SELECT count(id) as countNum from users where isactive='Y'";
				
				ResultSet result1 = connection.prepareStatement(query1)
						.executeQuery();
				int countNum = 0;
				while (result1.next()) {
					countNum = result1.getInt("countNum");
				}

				ResultSet result = ps.executeQuery();
				String isActive = "N";
				while (result.next()) {
					isActive = result.getString("isactive");
				}
				String isNewActive;

				if (isActive.equals("Y") && countNum > 1) {
					isNewActive = "N";
				} else {
					isNewActive = "Y";
				}

				String query2;
				int result2;
				if (isNewActive != null) {
					query2 = "update users set isactive=? where id=?;";
					PreparedStatement ps2 = connection.prepareStatement(query2);
					ps2.setString(1, isNewActive);
					ps2.setLong(2,user.getId());
					result2 = ps2.executeUpdate();
					if (result2 > 0) {
						return true;
					} else {
						//errorMessage = "Update isActive error.";
						return false;
					}
				} else {
					//errorMessage = "User did not exist.";
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}
	}

public boolean adminAddUser(Connection connection,User user) throws Exception {
   if (!user.getPassword().equals(user.getConfirmpassword())) {
       //errorMessage = "Password not match.";
       return false;
   }
   String query;

   try {
       if(user != null) {
           query = "SELECT count(id) as countNum FROM users where empid=? or username=?;";
           PreparedStatement ps = connection.prepareStatement(query);
           ps.setLong(1, user.getEmpid());
           ps.setString(2, user.getUsername());

           ResultSet result = ps.executeQuery();
           int count = 0;
           while (result.next()){
               count = result.getInt("countNum");
           }

           if ( count > 0 ) {
                //errorMessage = "User already exists.";
               return false;
           }else {
        	   String pin = generatePinNum(connection);
               String query2 = "insert into users (empid,username,firstname,lastname,sex,password,pin, isadmin,isactive, name_c, name_j, " +
               					" address, address1,phone, mobile,login_failure) values (?,?,?,?,?,sha1(?),?,?,?,?,?,?,?,?,?,0);";
               PreparedStatement ps2 = connection.prepareStatement(query2);
               ps2.setLong(1, user.getEmpid());
               ps2.setString(2, user.getUsername());
               ps2.setString(3, user.getFirstname());
               ps2.setString(4, user.getLastname());
               ps2.setString(5, user.getSex());
               ps2.setString(6, user.getPassword()+pin);
               ps2.setString(7, pin);
               ps2.setString(8, user.getIsadmin());
               ps2.setString(9, user.getIsactive());
               ps2.setString(10, user.getNamec());
               ps2.setString(11, user.getNamej());
               ps2.setString(12, user.getAddress());
               ps2.setString(13, user.getAddress1());
               ps2.setString(14, user.getPhone());
               ps2.setString(15, user.getMobile());
               System.out.println(query2);
               System.out.println(query);
               int result2 = ps2.executeUpdate();
               if (result2 > 0) {
                   return true;
               } else {
                   //errorMessage = "Register error.";
                   return false;
               }
           }
           }else{
               //errorMessage = "User is not set.";
               return false;
       }
    } catch (SQLException e){
        throw new Exception(e.getMessage());
    } finally {
    }
}



public boolean uniquepin(Connection connection,String pin) throws Exception{
   String query;

   if(pin!=null&&!pin.equals("")) {
       query = "select count(id) as countNum from  users where pin=?;";
   }
   else {
       System.out.println( "The pin is empty!");
       return false;
   }
   PreparedStatement ps =  connection.prepareStatement(query);
   ps.setString(1, pin);
   ResultSet result = ps.executeQuery();
   
   int count = 0;
   while (result.next())
   {
	   count = result.getInt("countNum");
   }
   if (count>0) {
       return false;
   }else if (count==0) {
       return true;
   }else {
       return false;
   }
}

public String getPinbyUserID(Connection connection,long id) throws Exception {
    String pin = "";
    String query = "SELECT pin FROM users where id=?;";

   try {
	   PreparedStatement ps = connection.prepareStatement(query);
	   ps.setLong(1, id);
       ResultSet result = ps.executeQuery();
       while (result.next()){
           pin = result.getString("pin");
       }
    }catch (Exception e){
        throw new Exception(e.getMessage());
    }
    return pin;
}

public User getUser(Connection connection,long id) throws Exception{
    String query;
    
    try{
    query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users WHERE ID=?";

    User user= new User();
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setLong(1, id);
    ResultSet result = ps.executeQuery();

    while(result.next()) {            //Build the user object
        user.setId(result.getLong("id"));
        user.setEmpid(result.getLong("empId"));
        user.setUsername(result.getString("userName"));
        user.setFirstname(result.getString("firstName"));
        user.setLastname(result.getString("lastName"));
        user.setSex(result.getString("sex"));
        user.setPassword(result.getString("password"));
        user.setIsadmin(result.getString("isadmin"));
        user.setIsactive(result.getString("isactive"));
        user.setAddress(result.getString("address"));
        user.setAddress1(result.getString("address1"));
        user.setNamec(result.getString("name_c"));
        user.setNamej(result.getString("name_j"));
        user.setPhone(result.getString("phone"));
        user.setMobile(result.getString("mobile"));
        user.setLoginfailure(result.getInt("login_failure"));
    }
    return user;
    }catch(Exception e){
    	e.printStackTrace();
    	throw new Exception(e.getMessage());
    }finally{
    }
}

public User getUserByUsernameIncludeUnactive(Connection connection,User user) throws Exception{
    String query;
    
    try{
    query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users WHERE username=?;";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1, user.getUsername());
    ResultSet result = ps.executeQuery();

    while(result.next()) {            //Build the user object
        user.setId(result.getLong("id"));
        user.setEmpid(result.getLong("empId"));
        user.setUsername(result.getString("userName"));
        user.setFirstname(result.getString("firstName"));
        user.setLastname(result.getString("lastName"));
        user.setSex(result.getString("sex"));
        user.setPassword(result.getString("password"));
        user.setIsadmin(result.getString("isadmin"));
        user.setIsactive(result.getString("isactive"));
        user.setAddress(result.getString("address"));
        user.setAddress1(result.getString("address1"));
        user.setNamec(result.getString("name_c"));
        user.setNamej(result.getString("name_j"));
        user.setPhone(result.getString("phone"));
        user.setMobile(result.getString("mobile"));
        user.setLoginfailure(result.getInt("login_failure"));
    }
    return user;
    }catch(Exception e){
    	e.printStackTrace();
    	throw new Exception(e.getMessage());
    }finally{
    }
}


public User getUserByUsername(Connection connection,User user) throws Exception{
    String query;
    
    try{
    query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users WHERE username=? and isactive='Y';";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1, user.getUsername());
    ResultSet result = ps.executeQuery();

    while(result.next()) {            //Build the user object
        user.setId(result.getLong("id"));
        user.setEmpid(result.getLong("empId"));
        user.setUsername(result.getString("userName"));
        user.setFirstname(result.getString("firstName"));
        user.setLastname(result.getString("lastName"));
        user.setSex(result.getString("sex"));
        user.setPassword(result.getString("password"));
        user.setIsadmin(result.getString("isadmin"));
        user.setIsactive(result.getString("isactive"));
        user.setAddress(result.getString("address"));
        user.setAddress1(result.getString("address1"));
        user.setNamec(result.getString("name_c"));
        user.setNamej(result.getString("name_j"));
        user.setPhone(result.getString("phone"));
        user.setMobile(result.getString("mobile"));
        user.setLoginfailure(result.getInt("login_failure"));
    }
    return user;
    }catch(Exception e){
    	e.printStackTrace();
    	throw new Exception(e.getMessage());
    }finally{
    }
}

public User getUserByEmpid(Connection connection,User user) throws Exception{
    String query;
    
    try{
    query = "SELECT id,empId,userName,firstName,lastName,sex,password,isadmin,isactive,address,address1,name_c,name_j,phone,mobile,login_failure FROM users WHERE empid=? and isactive='Y';";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setLong(1, user.getEmpid());
    ResultSet result = ps.executeQuery();

    while(result.next()) {            //Build the user object
        user.setId(result.getLong("id"));
        user.setEmpid(result.getLong("empId"));
        user.setUsername(result.getString("userName"));
        user.setFirstname(result.getString("firstName"));
        user.setLastname(result.getString("lastName"));
        user.setSex(result.getString("sex"));
        user.setPassword(result.getString("password"));
        user.setIsadmin(result.getString("isadmin"));
        user.setIsactive(result.getString("isactive"));
        user.setAddress(result.getString("address"));
        user.setAddress1(result.getString("address1"));
        user.setNamec(result.getString("name_c"));
        user.setNamej(result.getString("name_j"));
        user.setPhone(result.getString("phone"));
        user.setMobile(result.getString("mobile"));
        user.setLoginfailure(result.getInt("login_failure"));
    }
    return user;
    }catch(Exception e){
    	e.printStackTrace();
    	throw new Exception(e.getMessage());
    }finally{
    }
}

	@Override
	public  String generatePinNum(Connection connection) throws Exception{
	   String pin = "";
	   char [] pinletters = {'0','1','2','3','4','5','6','7','8','9'};

	    do {
	    pin = "";
	    for(int i=0;i<8;i++)
	    {
	            pin += pinletters[(int)(Math.random()*10)];
	    }
	    }while (!uniquepin(connection,pin));
	    return pin;
	}	

}
