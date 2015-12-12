<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.Privilege"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.PrivilegeServiceImpl"%>
<%@page import="com.javaforever.clocksimplejee4.database.DBConf"%>
<% if (DBConf.isGpinterfaceOffline()){
        	throw new ServletException("Test GP interface closed");
        }%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Privilege Info.</title>
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
                                <th>ID</th>
                                <th>Url</th>
                                <th>Isadmin</th>    
                                <th>CanDelete</th>                              
                                <th>Operation</th>
                            </tr>
                            <%
		                           long id = 0L;                                      	
		                   		   List<Privilege> privList = new PrivilegeServiceImpl().getPrivilegeList();
		                           
		                           for(int index=0;index < privList.size();index++){
		                        	   Privilege priv = privList.get(index);
		                               out.println("<tr>");
		                               out.println("<td><form action='../testcontroller/TestAdminEditPrivilegeController' method='post'><input type='text' name='id1' size='8' value='"+priv.getId()+"'/></td>");                         
		                               out.println("<td><input type='text' name='url' size='32' value='"+priv.getUrl()+"'/></td>");
		                               out.println("<td><input type='text' name='isadmin' size='6' value='"+priv.getIsadmin()+"'/></td>");
		                               out.println("<td><input type='text' name='canDelete' size='6' value='"+priv.getCanDelete()+"'/></td>");                              
		                               out.println("<td><input type='hidden' name='id' value='"+priv.getId()+"'/><input type='submit' value='Edit' /></form>"+
		                                           "<form action='../testcontroller/TestTogglePrivilegeAdminController' method='post'><input type='hidden' name='id' value='"+priv.getId()+"'/><input type='submit' value='Toggle Admin' /></form>"+
		                                           "<form action='../testcontroller/TestToggleCanDeleteController' method='post'><input type='hidden' name='id' value='"+priv.getId()+"'/><input type='submit' value='Toggle Can Delete'/></form>"+		                                         
		                                           "<form action='../testcontroller/TestAdminDeletePrivilegeController' method='post'><input type='hidden' name='id' value='"+priv.getId()+"'/><input type='submit' value='Delete' /></form> </td>");
		                               out.println("</tr>");
		                           }
                            %>
                            <tr><form action='../testcontroller/TestAdminAddPrivilegeController' method='post'>
                           	  <td><input type='text' name='id' size='8' value=''/></td>
                              <td><input type='text' name='url' size='32' value=''/></td>                            
                              <td><input type='text' name='isadmin' size='6' value=''/></td>
                              <td><input type='text' name='candelete' size='6' value=''/></td>                              
                              <td><input type='submit' value='Add Privilege' /></td></form>
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
