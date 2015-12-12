<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.User"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl"%>
<%@page import="com.javaforever.clocksimplejee4.database.DBConf"%>
<% if (DBConf.isGpinterfaceOffline()){
        	throw new ServletException("Test GP interface closed");
        }%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>User Info.</title>
        <link href="../css/testdefault.css" rel="stylesheet" type="text/css" />
    </head>   
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content1">
                    <div id="welcome">
                        <h2><%
                        	if (session.getAttribute("errorMessage")!= null && !"".equals((String)session.getAttribute("errorMessage"))){
                            	out.print((String)session.getAttribute("errorMessage"));                            
                            }
                            session.setAttribute("errorMessage", null);
                        %></h2>
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <tr>
                                <th>ID</th>
                                <th>EmpID</th>
                                <th>User Name</th>
                                <th>Full Name</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Password</th>
                                <th>Sex</th>
                                <th>IsAdmin</th>
                                <th>IsActive</th>
                                <th>Pin</th>
                                <th>Login failure</th>
                                <th>Chinese Name</th>
                                <th>Japanese Name</th>
                                <th>Address</th>
                                <th>Address1</th>
                                <th>Phone</th>
                                <th>Mobile</th>
                                <th>Confirmpassword</th>
                                <th>Operation</th>
                            </tr>
                            <%                                   	
		                   		   List<User> userData = new UserServiceImpl().listAllUsers();
		                           
		                           for(int index=0;index < userData.size();index++){
		                               User user = userData.get(index);
		                               out.println("<tr>");
		                               out.println("<td><form action='../testcontroller/TestAdminEditUserController' method='post'><input type='text' name='id1' size='8' value='"+user.getId()+"'/></td>");
		                               out.println("<td><input type='text' name='empid' size='8' value='"+user.getEmpid()+"'/></td>");                            
		                               out.println("<td><input type='text' name='username' size='8' value='"+user.getUsername()+"'/></td>");
		                               out.println("<td><input type='text' name='fullname' size='8' value='"+user.getFirstname()+" "+user.getLastname()+"'/></td>");
		                               out.println("<td><input type='text' name='firstname' size='8' value='"+user.getFirstname()+"'/></td>");
		                               out.println("<td><input type='text' name='lastname' size='8' value='"+user.getLastname()+"'/></td>");
		                               out.println("<td><input type='text' name='password' size='8' value='"+user.getPassword()+"'/></td>");
		                               out.println("<td><input type='text' name='sex' size='8' value='"+user.getSex()+"'/></td>");
		                               out.println("<td><input type='text' name='isadmin' size='8' value='"+user.getIsadmin()+"'/></td>");		                             
		                               out.println("<td><input type='text' name='isactive' size='8' value='"+user.getIsactive()+"'/></td>");
		                               out.println("<td><input type='text' name='pin' size='8' value='"+user.getPin()+"'/></td>");
		                               out.println("<td><input type='text' name='loginfailure' size='8' value='"+user.getLoginfailure()+"'/></td>");
		                               out.println("<td><input type='text' name='namec' size='8' value='"+user.getNamec()+"'/></td>");
		                               out.println("<td><input type='text' name='namej' size='8' value='"+user.getNamej()+"'/></td>");
		                               out.println("<td><input type='text' name='address' size='8' value='"+user.getAddress()+"'/></td>");
		                               out.println("<td><input type='text' name='address1' size='8' value='"+user.getAddress1()+"'/></td>");
		                               out.println("<td><input type='text' name='phone' size='8' value='"+user.getPhone()+"'/></td>");
		                               out.println("<td><input type='text' name='mobile' size='8' value='"+user.getMobile()+"'/></td>");
		                               out.println("<td><input type='text' name='confirmpassword' size='8' value='"+user.getConfirmpassword()+"'/></td>");
		                               out.println("<td><input type='hidden' name='id' value='"+user.getId()+"'/><input type='submit' value='Edit' /></form>"+
		                            		   	   "<form action='../test/changepassworduserform.jsp' method='post'><input type='hidden' name='id' value='"+user.getId()+"'/><input type='submit' value='Change Password' /></form>"+
		                                           "<form action='../testcontroller/TestToggleAdminController' method='post'><input type='hidden' name='id' value='"+user.getId()+"'/><input type='submit' value='Toggle Admin' /></form>"+
		                                           "<form action='../testcontroller/TestToggleActiveController' method='post'><input type='hidden' name='id' value='"+user.getId()+"'/><input type='submit' value='Toggle Active' /></form>"+		                                         
		                                           "<form action='../testcontroller/TestAdminDeleteUserController' method='post'><input type='hidden' name='id' value='"+user.getId()+"'/><input type='submit' value='Delete' /></form> </td>");
		                               out.println("</tr>");
		                           }
                            %>
                            <tr><form action='../testcontroller/TestAdminAddUserController' method='post'>
                           	  <td><input type='text' name='id' size='8' value=''/></td>
                              <td><input type='text' name='empid' size='8' value=''/></td>                            
                              <td><input type='text' name='username' size='8' value=''/></td>
                              <td><input type='text' name='fullname' size='8' value=''/></td>
                              <td><input type='text' name='firstname' size='8' value=''/></td>
                              <td><input type='text' name='lastname' size='8' value=''/></td>
                              <td><input type='text' name='password' size='8' value=''/></td>
                              <td><input type='text' name='sex' size='8' value=''/></td>
                              <td><input type='text' name='isadmin' size='8' value=''/></td>
                              <td><input type='text' name='isactive' size='8' value=''/></td>		                             
                              <td><input type='text' name='pin' size='8' value=''/></td>
                              <td><input type='text' name='loginfailure' size='8' value=''/></td>
                              <td><input type='text' name='namec' size='8' value=''/></td>
                              <td><input type='text' name='namej' size='8' value=''/></td>
                              <td><input type='text' name='address' size='8' value=''/></td>
                              <td><input type='text' name='address1' size='8' value=''/></td>
                              <td><input type='text' name='phone' size='8' value=''/></td>
                              <td><input type='text' name='mobile' size='8' value=''/></td>
                              <td><input type='text' name='confirmpassword' size='8' value=''/></td>
                              <td><input type='submit' value='Add User' /></td></form>
                            </tr>
                        </table>
                    </div>
                    <!-- end div#welcome -->
                </div>
                <!-- end div#content -->
                <div id="sidebar">   
                <ul>
                	<jsp:include page="../include/testnav.jsp"></jsp:include>   
                </ul>              
                </div>
                <!-- end div#sidebar -->
                <div style="clear: both; height: 1px"></div>
            </div>
             <jsp:include page="../include/footer.jsp"/>
        </div>       
        <!-- end div#wrapper -->
    </body>
</html>
