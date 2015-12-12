package com.javaforever.clocksimplejee4.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.PrivilegeService;
import com.javaforever.clocksimplejee4.service.UserService;
import com.javaforever.clocksimplejee4.serviceimpl.PrivilegeServiceImpl;
import com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl;

public class LoginController extends HttpServlet {

       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        UserService userService = new UserServiceImpl();
        PrivilegeService privService = new PrivilegeServiceImpl();
        try {
	    	User user2 = new User();
	    	User user3 = new User();
	        user2.setEmpid(Long.parseLong(request.getParameter("empid")==null?"0":request.getParameter("empid")));
	        user2.setPassword(request.getParameter("password"));
	        user2.setUsername(request.getParameter("username"));
	        boolean islogin = false;
	        
	        if (user2.getEmpid() > 0L){
	        	islogin = userService.login(user2);
	        	user3 = userService.getUserByEmpid(user2);
	        	request.getSession(true).setAttribute("userData", user3);
	        	request.getSession(true).setAttribute("islogin", "Y");
	        	request.getSession(true).setAttribute("empid", user3.getEmpid());
	        } else if (user2.getUsername()!= null && !"".equalsIgnoreCase(user2.getUsername())){
	        	islogin = userService.loginViaUserNamePassword(user2);
	        	user3 = userService.getUserByUsername(user2);
	        	request.getSession(true).setAttribute("userData", user3);
	        	request.getSession(true).setAttribute("islogin", "Y");
	        	request.getSession(true).setAttribute("empid", user3.getEmpid());
	    	} else {
	        	request.getSession().setAttribute("isadmin", null);
	        	request.getSession().setAttribute("islogin", null);
	        	request.getSession().setAttribute("userData",null);
	        	request.getSession().setAttribute("canAccess",null);
	        	request.getSession(true).setAttribute("empid", null);
	        	response.sendRedirect("../login/index.jsp");
	    	}
	        boolean isadmin = "Y".equalsIgnoreCase(user3.getIsadmin()!=null?user3.getIsadmin():"")?true:false;
	        request.getSession(true).setAttribute("isadmin", user3.getIsadmin());
	        
	        System.out.println("Jerry Debug:"+user3.getEmpid()+":"+user3.getUsername()+":"+user3.getPassword());
	        String forwardUrl = request.getRequestURI();
	        System.out.println(forwardUrl);
	        String[] forwards = forwardUrl.split("/");
	        String forwardUrl0 = "";
	        if (forwards.length >= 2){
	        	forwardUrl0 = "../"+forwards[forwards.length-2]+"/"+forwards[forwards.length-1];
	        	System.out.println("Jerry Debug::"+forwardUrl0);
	        } else {
	        	request.getSession().setAttribute("canAccess", null);
	        	request.getSession().setAttribute("islogin", null);
	        	request.getSession().setAttribute("isadmin", null);
	        	request.getSession().setAttribute("userData", null);
	        	response.sendRedirect("../login/index.jsp");
	        }	     
	        boolean canAccess = privService.canAccess(user3, forwardUrl0);
	        
	    	request.getSession().setAttribute("canAccess", canAccess==true?"Y":"N");
	    	if (islogin && isadmin &&canAccess){
	    		request.getSession().setAttribute("forwardUrl","../jsp/adminhomepage.jsp");
	    	} else if (islogin){
	    		request.getSession().setAttribute("forwardUrl","../jsp/userhomepage.jsp");
	    	} else {
	    		request.getSession().setAttribute("forwardUrl","../login/index.jsp");
	    	}
	    	//response.sendRedirect((String)request.getSession().getAttribute("forwardUrl"));
	    	request.getRequestDispatcher((String)request.getSession().getAttribute("forwardUrl")).forward(request, response);
        } catch (Exception e){
        	e.printStackTrace();
        	response.sendRedirect("../login/index.jsp");
        }
    }




    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


