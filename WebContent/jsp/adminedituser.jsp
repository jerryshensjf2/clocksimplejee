<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.User"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Admin Edit User</title>
        <link href="../css/default.css" rel="stylesheet" type="text/css" />
    </head>  
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp"></jsp:include>
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome"> 
                    <form action="../controller/AdminEditUserController" method="post">                       
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <%
                            	long id = 0L;                            
                                                                                    
                                id = Long.parseLong(request.getParameter("id"));
                        	    
                                User user = new UserServiceImpl().getUser(id);
                                
                                out.println("<tr><td colspan='2'><b>Edit User</b><input type='hidden' name='id' value='"+user.getId()+"'></td></tr>");
                                out.println("<tr><td>EmpID:</td><td><input type='text' name='empid' value='"+user.getEmpid()+"'/></td></tr>");
                                out.println("<tr><td>User Name:</td><td><input type='text' name='username' value='"+user.getUsername()+"'/></td></tr>");
                                out.println("<tr><td>First Name:</td><td><input type='text' name='firstname' value='"+user.getFirstname()+"'/></td></tr>");
                                out.println("<tr><td>Last Name:</td><td><input type='text' name='lastname' value='"+user.getLastname()+"'/></td></tr>");
                                out.println("<tr><td>Chinese Name:</td><td><input type='text' name='namec' value='"+user.getNamec()+"'/></td></tr>");
                                out.println("<tr><td>Japanese Name:</td><td><input type='text' name='namej' value='"+user.getNamej()+"'/></td></tr>");
                                out.print("<tr><td>Sex:</td><td><input type='radio' name='sex' value='male' "); if ("male".equalsIgnoreCase(user.getSex())) out.print("checked='true'");out.print("/>Male<input type='radio' name='sex' value='female'");if ("female".equalsIgnoreCase(user.getSex())) out.print("checked='true'"); out.print("/>Female</tr>");
                                out.println("<tr><td>Isadmin:</td><td><input type='radio' name='isadmin' value='Y' "); if ("Y".equalsIgnoreCase(user.getIsadmin())) out.print("checked='true'");out.print("/>Y<input type='radio' name='isadmin' value='N'");if ("N".equalsIgnoreCase(user.getIsadmin())) out.print("checked='true'"); out.println("/>N</tr>");
                                out.println("<tr><td>Isactive:</td><td><input type='radio' name='isactive' value='Y' "); if ("Y".equalsIgnoreCase(user.getIsactive())) out.print("checked='true'");out.print("/>Y<input type='radio' name='isactive' value='N'");if ("N".equalsIgnoreCase(user.getIsactive())) out.print("checked='true'"); out.print("/>N</tr>");
                                out.println("<tr><td>Address:</td><td><textarea name='address'>"+user.getAddress()+"</textarea></td></tr>");
                                out.println("<tr><td>Address 2:</td><td><textarea name='address1'>"+user.getAddress1()+"</textarea></td></tr>");
                                out.println("<tr><td>Phone:</td><td><input type='text' name='phone' value='"+user.getPhone()+"'/></td></tr>");
                                out.println("<tr><td>Mobile:</td><td><input type='text' name='mobile' value='"+user.getMobile()+"'/></td></tr>");
                                out.println("<tr><td>Login failure:</td><td>"+user.getLoginfailure()+"</td></tr>");
                                out.println("<tr><td colspan='2'><input type='submit' value='Edit'/>&nbsp;<input type='button' value='Cancel' onclick=window.location.href='../jsp/userfunctionadministration.jsp' /></td></tr>");
						%>
                        </table>
                        </form>
                    </div>
                    <!-- end div#welcome -->                    
                </div>
                <!-- end div#content -->
                <div id="sidebar">
                    <ul>
                    	<%	if("Y".equalsIgnoreCase((String)request.getSession().getAttribute("isadmin"))){%>
                    	<jsp:include page="../include/adminnav.jsp"/>
                    	<%} else { %>
                    	<jsp:include page="../include/usernav.jsp"/>
                    	<%} %>
                        <!-- end navigation -->
                        	<jsp:include page="../include/updates.jsp"/>
                        <!-- end updates -->
                    </ul>
                </div>
                <!-- end div#sidebar -->
                <div style="clear: both; height: 1px"></div>
            </div>
                <jsp:include page="../include/footer.jsp" />
        </div>
        <!-- end div#wrapper -->
    </body>
</html>
