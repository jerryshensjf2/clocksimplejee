<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.ClockRecord"%>
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
                        <form name="clockmenowform" action="../controller/ClockRecordClockController" method="post">
                        <textarea name="description" ></textarea>
						<input type="submit" name="clocknow" value="Clock Me Now!" />
						<br>
						<br>
						<input type="text" readonly="readonly" name = "duration" value="<%=new ClockRecordServiceImpl().todayDuration((User)session.getAttribute("userData"))%>"></input>
						</form>
						 <table class="aatable">
                            <tr>
                                <th>Id</th>
                                <th>UserId</th>
                                <th>EmpId</th>    
                                <th>Timestamp</th>    
                                <th>Description</th>                             
                            </tr>
                            <%
		                           long id = 0L;                                      	
		                   		   List<ClockRecord> clockRecordList = new ClockRecordServiceImpl().listAllTodayClockRecord((User)session.getAttribute("userData"));
		                           
		                           for(int index=0;index < clockRecordList.size();index++){
		                        	   ClockRecord clockRecord = clockRecordList.get(index);
		                               out.println("<tr>");
		                               out.println("<td>"+clockRecord.getId()+"</td>");                         
		                               out.println("<td>"+clockRecord.getUserId()+"</td>");
		                               out.println("<td>"+clockRecord.getEmpId()+"</td>");
		                               out.println("<td>"+clockRecord.getTimeStamp()+"</td>");
		                               out.println("<td>"+clockRecord.getDescription()+"</td>");                                    
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
