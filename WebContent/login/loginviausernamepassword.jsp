<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Welcome to Clock Online System</title>
        <link href="../css/default.css" rel="stylesheet" type="text/css" />
    </head>
   
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                 <h2><%
                            if (session.getAttribute("errorMessage")!= null && !"".equals((String)session.getAttribute("errorMessage"))){
                            	out.print((String)session.getAttribute("errorMessage"));                            
                            }
                            session.setAttribute("errorMessage", null);
                                %></h2>
                    <h1>Login</h1>
                <form action="../logincontroller/LoginController" method="post">
                <table>
                <tr><td  class="login">User Name:</td><td><input type="text" name="username" value="" style="width: 220px" /></td></tr>
                <tr><td  class="login">Password:</td><td><input type="password" name="password" value="" style="width: 220px" /></td></tr>
                <tr><td colspan="2"><input type="submit" value="login"/></td></tr>
                </table>
            </form>
                <a href="../login/index.jsp">Login via EmpID and Password</a><br />
                <a href="../login/register.jsp">Register</a>
                    
                </div>
                <!-- end div#content -->
                <div id="sidebar">
                </div>
                <!-- end div#sidebar -->
                <div style="clear: both; height: 1px"></div>
            </div>
                <jsp:include page="../include/footer.jsp" />
        </div>
        <!-- end div#wrapper -->
    </body>
</html>
