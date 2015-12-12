<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
        <link href="../css/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp"/>
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome">
                        <form action="../testcontroller/TestChangePasswordController" method="post">
                            <h2><%
                            	if (session.getAttribute("errorMessage")!= null && !"".equals((String)session.getAttribute("errorMessage"))){
                                	out.print((String)session.getAttribute("errorMessage"));                            
                                }
                                session.setAttribute("errorMessage", null);
                            %></h2>
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <%
                          	long id = 0L;	                        	
	               		    id = ((User) request.getSession().getAttribute("userData")).getId();                		    
	                          	User user = new UserServiceImpl().getUser(id);
	                        
	                        out.println("<tr><td colspan='2'> <b>Change Password</b><input type='hidden' name='id' value='"+user.getId()+"'></td></tr>");
	                        out.println("<tr><td>Old Password:</td><td><input type='password' name='password' value=''/></td></tr>");
	                        out.println("<tr><td>New Password:</td><td><input type='password' name='newpassword' value=''/></td></tr>");
	                        out.println("<tr><td>Confirm New Password:</td><td><input type='password' name='confirmnewpassword' value=''/></td></tr>");
	                        out.println("<tr><td colspan='2'><input type='submit' value='Change'/>&nbsp;<input type='button' value='Cancel' onclic='window.location.href=../test/usergpinterface.jsp'/></td></tr>");
%>
                        </table>
                        </form>
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
                <jsp:include page="../include/footer.jsp" />
        </div>
        <!-- end div#wrapper -->
    </body>
</html>
