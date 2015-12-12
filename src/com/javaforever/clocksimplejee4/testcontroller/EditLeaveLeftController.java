package com.javaforever.clocksimplejee4.testcontroller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.LeaveLeft;
import com.javaforever.clocksimplejee4.service.LeaveLeftService;
import com.javaforever.clocksimplejee4.serviceimpl.LeaveLeftServiceImpl;

public class EditLeaveLeftController extends BaseTestController {

       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	LeaveLeft leaveLeft = new LeaveLeft();
        	leaveLeft.setId(Long.parseLong(request.getParameter("id")));
        	leaveLeft.setUserId(Long.parseLong(request.getParameter("userid")));
        	leaveLeft.setEmpId(Long.valueOf(request.getParameter("empid")));
        	leaveLeft.setAnnualLeaveLeft(Integer.valueOf(request.getParameter("annualleaveleft")));
        	leaveLeft.setSickLeaveLeft(Integer.valueOf(request.getParameter("sickleaveleft")));
        	leaveLeft.setPrivateLeaveLeft(Integer.valueOf(request.getParameter("privateleaveleft")));
        	leaveLeft.setOtherLeaveLeft(Integer.valueOf(request.getParameter("otherleaveleft")));
        	leaveLeft.setYear(Integer.valueOf(request.getParameter("year")));
        	leaveLeft.setDescription(request.getParameter("description"));

            LeaveLeftService leaveLeftService = new LeaveLeftServiceImpl();
            
            leaveLeftService.updateLeaveLeft(leaveLeft);
            
            response.sendRedirect("../test/leaveleftgpinterface.jsp");
             } catch (Exception e)
             {
            	 e.printStackTrace();
            	 response.sendRedirect("../test/leaveleftgpinterface.jsp");
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

