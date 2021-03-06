package com.javaforever.clocksimplejee4.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;
import com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl;

public class AdminAddUserController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            User user = new User();
            user.setEmpid(Long.parseLong(request.getParameter("empid")));
            user.setUsername(request.getParameter("username"));
            user.setNamec(request.getParameter("namec"));
            user.setNamej(request.getParameter("namej"));
            user.setIsadmin(request.getParameter("isadmin"));
            user.setIsactive(request.getParameter("isactive"));
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setSex(request.getParameter("sex"));
            user.setPassword(request.getParameter("password"));
            user.setConfirmpassword(request.getParameter("confirmpassword"));

            UserService clockService = new UserServiceImpl();
            clockService.adminAddUser(user);
            
        	response.sendRedirect("../jsp/adminadduser.jsp");
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

