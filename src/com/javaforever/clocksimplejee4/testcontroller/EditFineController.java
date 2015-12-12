package com.javaforever.clocksimplejee4.testcontroller;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.Fine;
import com.javaforever.clocksimplejee4.service.FineService;
import com.javaforever.clocksimplejee4.serviceimpl.FineServiceImpl;

public class EditFineController extends BaseTestController{

       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	Fine fine = new Fine();
        	fine.setId(Long.parseLong(request.getParameter("id")));
        	fine.setFineBalance(new BigDecimal(request.getParameter("finebalance")));
        	fine.setEmpId(Long.valueOf(request.getParameter("empid")));
        	fine.setReason(request.getParameter("reason"));
        	fine.setUserId(Long.valueOf(request.getParameter("userid")));
        	fine.setDescription(request.getParameter("description"));

            FineService fineService = new FineServiceImpl();
            
            fineService.updateFine(fine);
            
            response.sendRedirect("../test/finegpinterface.jsp");
             } catch (Exception e)
             {
            	 e.printStackTrace();
            	 response.sendRedirect("../test/finegpinterface.jsp");
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

