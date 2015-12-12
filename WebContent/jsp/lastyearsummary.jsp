<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="com.javaforever.clocksimplejee4.domain.ClockRecord"%>
<%@page import="com.javaforever.clocksimplejee4.bo.AttendanceStatus"%>
<%@page import="com.javaforever.clocksimplejee4.domain.User"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.ClockRecordServiceImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Clock Record Info.</title>
        <link href="../css/testdefault.css" rel="stylesheet" type="text/css" />
    </head>   
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome">
                        <h2></h2>
                        <!-- Fetch Rows -->                     
						<br>
						<br>
						<h3>Year <%=(new Timestamp(System.currentTimeMillis()).getYear() + 1900 -1)%></h3><br>
						Yearly work time:
						<%=(new ClockRecordServiceImpl().getWholeLastYearWorkSum(new Timestamp(System.currentTimeMillis()),(User)session.getAttribute("userData"))).toString()%>
						 <table class="aatable">
                            <tr>
                                <th>UserId</th>
                                <th>EmpId</th>  
                                <th>Username</th>
                                <th>FullName</th>   
                                <th>date</th>    
                                <th>Attendance Status</th> 
                                <th>Duration</th> 
                                <th>Attendance Count</th>
                                <th>Description</th>                             
                            </tr>
                            <%                                     	
		                   		   List<AttendanceStatus> attendanceStatusList = new ClockRecordServiceImpl().getWholeLastYearWorkStatusSummary(new Timestamp(System.currentTimeMillis()),(User)session.getAttribute("userData"));
		                           
		                           for(int index=0;index < attendanceStatusList.size();index++){
		                        	   AttendanceStatus attendanceStatus = attendanceStatusList.get(index);
		                               out.println("<tr>");                       
		                               out.println("<td>"+attendanceStatus.getUserId()+"</td>");
		                               out.println("<td>"+attendanceStatus.getEmpId()+"</td>");
		                               out.println("<td>"+attendanceStatus.getUsername()+"</td>");
		                               out.println("<td>"+attendanceStatus.getFullName()+"</td>");
		                               out.println("<td>"+attendanceStatus.getDate()+"</td>");
		                               out.println("<td>"+attendanceStatus.getAttendanceStatus()+"</td>");
		                               out.println("<td>"+attendanceStatus.getDuration()+"</td>");
		                               out.println("<td>"+attendanceStatus.getAttendanceCount()+"</td>");
		                               out.println("<td>"+attendanceStatus.getDescription()+"</td>");                                    
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
            <jsp:include page="../include/footer.jsp"/>
        </div>        
        <!-- end div#wrapper -->
    </body>
</html>
