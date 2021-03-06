package com.javaforever.clocksimplejee4.testcontroller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.daoimpl.UserDaoImpl;
import com.javaforever.clocksimplejee4.domain.User;
import com.javaforever.clocksimplejee4.service.UserService;
import com.javaforever.clocksimplejee4.serviceimpl.UserServiceImpl;

public class RegisterController extends BaseTestController {

       protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            User user = new User();
            user.setEmpid(Long.parseLong(request.getParameter("empid")));
            user.setUsername(request.getParameter("username"));
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setSex(request.getParameter("sex"));
            user.setPassword(request.getParameter("password"));
            user.setConfirmpassword(request.getParameter("confirmpassword"));
            user.setNamec(request.getParameter("namec"));
            user.setNamej(request.getParameter("namej"));
            user.setAddress(request.getParameter("address"));
            user.setAddress1(request.getParameter("address1"));
            user.setPhone(request.getParameter("phone"));
            user.setMobile(request.getParameter("mobile"));

            UserService clockService = new UserServiceImpl();

            if(clockService.registerUser(user)){
                if (clockService.login(user)) {
                    response.sendRedirect("../test/usergpinterface.jsp");
                }else {
                    response.sendRedirect("../test/usergpinterface.jsp");
                }
            }else {
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



