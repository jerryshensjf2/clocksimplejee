<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>User Home</title>
        <link href="../css/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome">
                         Welcome to the Clock Online System.
                    
                    </div>
                    <!-- end div#welcome -->

                </div>
                <!-- end div#content -->
                <div id="sidebar">
                    <ul>
                        <%	if("Y".equalsIgnoreCase((String)request.getSession(true).getAttribute("isadmin"))){%>
                    	<jsp:include page="../include/adminnav.jsp"/>
                    	<%} else { %>
                    	<jsp:include page="../include/usernav.jsp"/>
                    	<%} %>
                        <!-- end navigation -->
                        <jsp:include page="../include/updates.jsp"/>
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
