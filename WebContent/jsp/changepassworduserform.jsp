<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.User"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl"%>
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
                        <form action="../controller/AdminChangeUserPasswordController" method="post">
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <%
                            	long id = 0L;
                                                                                	
                        	    id = Long.parseLong(request.getParameter("id"));                    	    
                                User user = new UserServiceImpl().getUser(id);

                                out.println("<tr><td colspan='2'><b>User Info</b><input type='hidden' name='id' value='"+user.getId()+"'></td></tr>");
                                out.println("<tr><td>EmpID:</td><td>"+user.getEmpid()+"</td></tr>");
                                out.println("<tr><td>User Name:</td><td>"+user.getUsername()+"</td></tr>");
                                out.println("<tr><td>First Name:</td><td>"+user.getFirstname()+"</td></tr>");
                                out.println("<tr><td>Last Name:</td><td>"+user.getLastname()+"</td></tr>");
                                out.println("<tr><td>Sex:</td><td>"+user.getSex()+"</td></tr>");
                                out.println("<tr><td>IsAdmin:</td><td>"+user.getIsadmin()+"</td></tr>");
                                out.println("<tr><td>New Password:</td><td><input type='password' name='newpassword' value=''/></td></tr>");
                                out.println("<tr><td>Confirm New Password:</td><td><input type='password' name='confirmnewpassword' value=''/></td></tr>");
                                out.println("<tr><td colspan='2'><input type='submit' value='Change Password'/>&nbsp;<input type='button' value='Cancel' /></td></tr>");
                            %>
                        </table>
                        </form>
                    </div>
                    <!-- end div#welcome -->

                </div>
                <!-- end div#content -->
                <div id="sidebar">
                    <ul>
                    	<%	if("Y".equals((String)request.getSession().getAttribute("isadmin"))){%>
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
