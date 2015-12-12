package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.LeaveLimit;
import com.javaforever.clocksimplejee4.service.LeaveLimitService;
import com.javaforever.clocksimplejee4.serviceimpl.LeaveLimitServiceImpl;

public class AddLeaveLimitController extends BaseTestController {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	LeaveLimit leaveLimit = new LeaveLimit();        	
        	leaveLimit.setEmployeeTypeId(Long.valueOf(request.getParameter("employeetypeid")));
        	leaveLimit.setAnnualLeaveLimit(Integer.valueOf(request.getParameter("annualleavelimit")));
        	leaveLimit.setSickLeaveLimit(Integer.valueOf(request.getParameter("sickleavelimit")));
        	leaveLimit.setPrivateLeaveLimit(Integer.valueOf(request.getParameter("privateleavelimit")));
        	leaveLimit.setOtherLeaveLimit(Integer.valueOf(request.getParameter("otherleavelimit")));
        	leaveLimit.setDescription(request.getParameter("description"));

            String errorMessage = "";
            LeaveLimitService leaveLimitService = new LeaveLimitServiceImpl();
            leaveLimitService.createLeaveLimit(leaveLimit);
            
            if (!"".equals(errorMessage)) {
            	request.getSession(true).setAttribute("errorMessage", errorMessage);
            }
            
        	response.sendRedirect("../test/leavelimitgpinterface.jsp");
        } catch (Exception e){
        	e.printStackTrace();
        	response.sendRedirect("../test/leavelimitgpinterface.jsp");
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

