package com.javaforever.clocksimplejee4.testcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.database.DBConf;

/**
 * Servlet implementation class BaseTestController
 */
public class BaseTestController extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseTestController() {
        super();
    }

    @Override
    public final void init() throws ServletException {
    	super.init();
        if (DBConf.isGpinterfaceOffline()){
        	throw new ServletException("Test GP interface closed");
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
