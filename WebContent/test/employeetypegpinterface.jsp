<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.EmployeeType"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.EmployeeTypeServiceImpl"%>
<%@page import="com.javaforever.clocksimplejee4.database.DBConf"%>
<% if (DBConf.isGpinterfaceOffline()){
        	throw new ServletException("Test GP interface closed");
        }%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>EmployeeType Info.</title>
        <link href="../css/testdefault.css" rel="stylesheet" type="text/css" />
    </head>   
    <body>
        <div id="wrapper">
        <jsp:include page="../include/header.jsp" />
            <!-- end div#header -->
            <div id="page">
                <div id="content">
                    <div id="welcome">
                        <h2><%
                        	if (session.getAttribute("errorMessage")!= null && !"".equals((String)session.getAttribute("errorMessage"))){
                            	out.print((String)session.getAttribute("errorMessage"));                            
                            }
                            session.setAttribute("errorMessage", null);
                        %></h2>
                        <!-- Fetch Rows -->
                        <table class="aatable">
                            <tr>
                                <th>Id</th>
                                <th>Employee Type Name</th>    
                                <th>Description</th>                             
                                <th>Operation</th>
                            </tr>
                            <%
		                           long id = 0L;                                      	
		                   		   List<EmployeeType> employeeTypeList = new EmployeeTypeServiceImpl().listAllEmployeeTypes();
		                           
		                           for(int index=0;index < employeeTypeList.size();index++){
		                        	   EmployeeType employeeType = employeeTypeList.get(index);
		                               out.println("<tr>");
		                               out.println("<td><form action='../testcontroller/TestEditEmployeeTypeController' method='post'><input type='hidden' name='id' size='6' value='"+employeeType.getId()+"'/><input type='text' name='id1' size='6' value='"+employeeType.getId()+"'/></td>");                         
		                               out.println("<td><input type='text' name='employeetypename' size='12' value='"+employeeType.getEmployeeTypeName()+"'/></td>");
		                               out.println("<td><textarea type='text' name='description' size='6'/>"+employeeType.getDescription()+"</textarea></td>"); 
		                               out.println("<td><input type='hidden' name='id' value='"+employeeType.getId()+"'/><input type='submit' value='Edit' /></form>"+		                                           
		                                           "<form action='../testcontroller/TestDeleteEmployeeTypeController' method='post'><input type='hidden' name='id' value='"+employeeType.getId()+"'/><input type='submit' value='Delete' /></form> </td>");
		                               out.println("</tr>");
		                           }
                            %>
                            <tr><form action='../testcontroller/TestAddEmployeeTypeController' method='post'>
                           	  <td><input type='text' name='id' size='6' value=''/></td>
                           	  <td><input type='text' name='employeetypename' size='12' value=''/></td>
                              <td><textarea type='text' name='description' size='6'/></textarea></td>                                                         
                              <td><input type='submit' value='Add EmployeeType' /></td></form>
                            </tr>
                        </table>
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
            <jsp:include page="../include/footer.jsp"/>
        </div>        
        <!-- end div#wrapper -->
    </body>
</html>
