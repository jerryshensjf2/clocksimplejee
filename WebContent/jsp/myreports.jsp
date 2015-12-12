<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="com.javaforever.clocksimplejee4.domain.ClockRecord"%>
<%@page import="com.javaforever.clocksimplejee4.domain.User"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.ClockRecordServiceImpl"%>
<%!Timestamp timestamp = new Timestamp(System.currentTimeMillis());%>
<% %>
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
                    <ul>
                            <li><a href="../jsp/mythisweekworkstatussummary.jsp">This Week Summary</a></li>
                            <li><a href="../jsp/mylastweekworkstatussummary.jsp">Last Week Summary</a></li>
                            <li><a href="../jsp/mythismonthworkstatussummary.jsp">This Month Summary</a></li>
        					<li><a href="../jsp/mylastmonthworkstatussummary.jsp">Last Month Summary</a></li>
        					<li><a href="../jsp/thisyearsummary.jsp">This Year Summary</a></li>
        					<li><a href="../jsp/lastyearsummary.jsp">Last Year Summary</a></li>
        					<li><a href="../jsp/myclock.jsp">My Clock History</a></li>
                    </ul>                     
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
