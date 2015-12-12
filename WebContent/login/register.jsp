<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Register</title>
        <link href="../css/default.css" rel="stylesheet" type="text/css" />
    </head>
   
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome">
                        <form action="../controller/RegisterController" method="post">
                        <h2><%
                            if (session.getAttribute("errorMessage")!= null && !"".equals((String)session.getAttribute("errorMessage"))){
                            	out.print((String)session.getAttribute("errorMessage"));                            
                            }
                            session.setAttribute("errorMessage", null);
                                %></h2>
                        <!-- Fetch Rows -->
                        <table class="aatable">
                                <tr><td colspan='2'><b>Register</b></td></tr>
                                <tr><td>EmpID:</td><td><input type='text' name='empid' value=''/></td></tr>
                                <tr><td>User Name:</td><td><input type='text' name='username' value=''/></td></tr>
                                <tr><td>First Name:</td><td><input type='text' name='firstname' value=''/></td></tr>
                                <tr><td>Last Name:</td><td><input type='text' name='lastname' value=''/></td></tr>
                                <tr><td>Chinese Name:</td><td><input type='text' name='namec' value=''/></td></tr>
                                <tr><td>Japanese Name:</td><td><input type='text' name='namej' value=''/></td></tr>
                                <tr><td>Sex:</td><td><input type='radio' name='sex' value='male' checked='true'/>Male<input type='radio' name='sex' value='female'/>Female</td></tr>
                                <tr><td>Phone:</td><td><input type='text' name='phone' value=''/></td></tr>
                                <tr><td>Mobile:</td><td><input type='text' name='mobile' value=''/></td></tr>
                                <tr><td>Address:</td><td><textarea name='address' value=''></textarea></td></tr>
                                <tr><td>Address 2:</td><td><textarea name='address1' value=''></textarea></td></tr>
                                <tr><td>Password</td><td><input type='password' name='password' value=''/></td></tr>
                                <tr><td>Confirm Password</td><td><input type='password' name='confirmpassword' value=''/></td></tr>
                                <tr><td colspan='2'><input type='submit' value='Register'/>&nbsp;<input type='button' value='Cancel' onclick="location.href='../login/index.jsp'"/></td></tr>
                        </table>
                        </form>
                    </div>
                    <!-- end div#welcome -->			
                    
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
