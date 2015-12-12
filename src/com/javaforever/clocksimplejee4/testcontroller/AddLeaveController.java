package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.Leave;
import com.javaforever.clocksimplejee4.service.LeaveService;
import com.javaforever.clocksimplejee4.serviceimpl.LeaveServiceImpl;

public class AddLeaveController extends BaseTestController {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	Leave leave = new Leave();
        	leave.setUserId(Long.valueOf(request.getParameter("userid")));
        	leave.setEmpId(Long.valueOf(request.getParameter("empid")));
        	leave.setDay(Date.valueOf(request.getParameter("day")));
        	leave.setLeaveTypeId(Long.valueOf(request.getParameter("leavetypeid")));
        	leave.setDescription(request.getParameter("description"));

            String errorMessage = "";
            LeaveService leaveService = new LeaveServiceImpl();
            leaveService.createLeave(leave);
            
            if (!"".equals(errorMessage)) {
            	request.getSession(true).setAttribute("errorMessage", errorMessage);
            }
            
        	response.sendRedirect("../test/leavegpinterface.jsp");
        } catch (Exception e){
        	e.printStackTrace();
        	response.sendRedirect("../test/leavegpinterface.jsp");
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

