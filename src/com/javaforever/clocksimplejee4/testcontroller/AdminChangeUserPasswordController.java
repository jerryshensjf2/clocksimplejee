package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;
import com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl;

public class AdminChangeUserPasswordController extends BaseTestController {

       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            User user = new User();
            user.setId(Long.parseLong(request.getParameter("id")));
            user.setPassword(request.getParameter("newpassword"));
            String confirmnewpassword = request.getParameter("confirmnewpassword");            
           
            UserService clockService = new UserServiceImpl();            
            
            if (clockService.adminChangeUserPassword(user,confirmnewpassword)){
            	System.out.println("Jerry:AdminChangeUserPasswordController::fork1");
                response.sendRedirect("../test/usergpinterface.jsp");
            }else {
            	System.out.println("Jerry:AdminChangeUserPasswordController::fork2");
                response.sendRedirect("../test/usergpinterface.jsp");
            }
        } catch (Exception e){
        	e.printStackTrace();
         } finally {
            out.close();
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

