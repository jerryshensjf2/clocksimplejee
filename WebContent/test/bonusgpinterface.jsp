<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.javaforever.clocksimplejee4.domain.Bonus"%>
<%@page import="com.javaforever.clocksimplejee4.serviceimpl.BonusServiceImpl"%>
<%@page import="com.javaforever.clocksimplejee4.database.DBConf"%>
<% if (DBConf.isGpinterfaceOffline()){
        	throw new ServletException("Test GP interface closed");
        }%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Bonus Info.</title>
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
                                <th>UserId</th>
                                <th>EmpId</th>    
                                <th>Reason</th>  
                                <th>BonusBalance</th>    
                                <th>Description</th>                             
                                <th>Operation</th>
                            </tr>
                            <%
		                           long id = 0L;                                      	
		                   		   List<Bonus> bonusList = new BonusServiceImpl().listAllBonus();
		                           
		                           for(int index=0;index < bonusList.size();index++){
		                        	   Bonus bonus = bonusList.get(index);
		                               out.println("<tr>");
		                               out.println("<td><form action='../testcontroller/TestEditBonusController' method='post'><input type='hidden' name='id' size='6' value='"+bonus.getId()+"'/><input type='text' name='id1' size='6' value='"+bonus.getId()+"'/></td>");                         
		                               out.println("<td><input type='text' name='userid' size='6' value='"+bonus.getUserId()+"'/></td>");
		                               out.println("<td><input type='text' name='empid' size='6' value='"+bonus.getEmpId()+"'/></td>");
		                               out.println("<td><input type='text' name='reason' size='10' value='"+bonus.getReason()+"'/></td>");
		                               out.println("<td><input type='text' name='bonusbalance' size='6' value='"+bonus.getBonusBalance()+"'/></td>"); 
		                               out.println("<td><textarea type='text' name='description' size='6'/>"+bonus.getDescription()+"</textarea></td>"); 
		                               out.println("<td><input type='hidden' name='id' value='"+bonus.getId()+"'/><input type='submit' value='Edit' /></form>"+		                                           
		                                           "<form action='../testcontroller/TestDeleteBonusController' method='post'><input type='hidden' name='id' value='"+bonus.getId()+"'/><input type='submit' value='Delete' /></form> </td>");
		                               out.println("</tr>");
		                           }
                            %>
                            <tr><form action='../testcontroller/TestAddBonusController' method='post'>
                           	  <td><input type='text' name='id' size='6' value=''/></td>
                           	  <td><input type='text' name='userid' size='6' value=''/></td>
                              <td><input type='text' name='empid' size='6' value=''/></td>                            
                              <td><input type='text' name='reason' size='10' value=''/></td>
                              <td><input type='text' name='bonusbalance' size='6' value=''/></td>   
                              <td><textarea type='text' name='description' size='6'/></textarea></td>                                                         
                              <td><input type='submit' value='Add Bonus' /></td></form>
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
