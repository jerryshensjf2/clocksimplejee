<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <tr>
                                <th>ID</th>
                                <th>EmpID</th>
                                <th>User Name</th>
                                <th>Full Name</th>
                                <th>Chinese Name</th>
                                <th>Japanese Name</th>
                                <th>Sex</th>
                                <th>IsAdmin</th>
                                <th>IsActive</th>
                            </tr>
                            <%                                 
                                  List<User> userData = new UserServiceImpl().listAllUsers();
                                  
                                  for(int index=0;index < userData.size();index++){
                                      User user = userData.get(index);
                                      out.println("<tr>");
                                      out.println("<td>"+user.getId()+"</td>");
                                      out.println("<td>"+user.getEmpid()+"</td>");                            
                                      out.println("<td>"+user.getUsername()+"</td>");
                                      out.println("<td>"+user.getFirstname()+" "+user.getLastname()+"</td>");
                                      out.println("<td>"+user.getNamec()+"</td>");
                                      out.println("<td>"+user.getNamej()+"</td>");
                                      out.println("<td>"+user.getSex()+"</td>");
                                      out.println("<td>"+user.getIsadmin()+"</td>");
                                      out.println("<td>"+user.getIsactive()+"</td>");
                                      out.println("</tr>");
                                  }
                            %>
                        </table>
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
            	<jsp:include page="../include/footer.jsp"></jsp:include>
        </div>
        <!-- end div#wrapper -->
    </body>
</html>
