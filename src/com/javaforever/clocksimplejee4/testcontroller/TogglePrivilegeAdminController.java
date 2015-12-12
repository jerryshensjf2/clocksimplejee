package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.Privilege;
import com.javaforever.clocksimplejee4.service.PrivilegeService;
import com.javaforever.clocksimplejee4.serviceimpl.PrivilegeServiceImpl;

public class TogglePrivilegeAdminController extends BaseTestController {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Privilege priv = new Privilege();
            priv.setId(Long.parseLong(request.getParameter("id")));
            
            String errorMessage = "";
            PrivilegeService privService = new PrivilegeServiceImpl();
            privService.adminTogglePrivilegeIsAdmin(priv);

            if (!"".equals(errorMessage)) {
            	request.getSession(true).setAttribute("errorMessage", errorMessage);
            }
            response.sendRedirect("../jsp/privilege.jsp");
         } catch (Exception e){
        	 e.printStackTrace();
        	 response.sendRedirect("../jsp/privilege.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}