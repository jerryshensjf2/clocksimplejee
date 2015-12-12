<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
request.getSession().setAttribute("islogin",null);
request.getSession().setAttribute("isadmin",null);
request.getSession().setAttribute("userData",null);
request.getSession().setAttribute("canDelete",null);
request.getSession().setAttribute("forwardUrl",null);
response.sendRedirect("../jsp/index.jsp");
%>
