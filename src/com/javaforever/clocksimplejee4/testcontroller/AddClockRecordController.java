package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.ClockRecord;
import com.javaforever.clocksimplejee4.service.ClockRecordService;
import com.javaforever.clocksimplejee4.serviceimpl.ClockRecordServiceImpl;

public class AddClockRecordController extends BaseTestController {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	ClockRecord clockRecord = new ClockRecord();
        	clockRecord.setEmpId(Long.valueOf(request.getParameter("empid")));
        	clockRecord.setTimeStamp(Timestamp.valueOf(request.getParameter("timestamp")));
        	clockRecord.setUserId(Long.valueOf(request.getParameter("userid")));
        	clockRecord.setDescription(request.getParameter("description"));

            String errorMessage = "";
            ClockRecordService clockRecordService = new ClockRecordServiceImpl();
            clockRecordService.createClockRecord(clockRecord);
            
            if (!"".equals(errorMessage)) {
            	request.getSession(true).setAttribute("errorMessage", errorMessage);
            }
            
        	response.sendRedirect("../test/clockrecordgpinterface.jsp");
        } catch (Exception e){
        	e.printStackTrace();
        	response.sendRedirect("../test/clockrecordgpinterface.jsp");
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

