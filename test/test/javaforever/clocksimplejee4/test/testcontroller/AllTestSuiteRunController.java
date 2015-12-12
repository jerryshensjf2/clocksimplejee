package test.javaforever.clocksimplejee4.test.testcontroller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;

import com.javaforever.clocksimplejee4.testcontroller.BaseTestController;

/**
 * Servlet implementation class AllTestSuiteRunController
 */
public class AllTestSuiteRunController extends BaseTestController{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllTestSuiteRunController()  {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String className = "AllTest";
       response.setContentType("text/plain");
       OutputStream out = response.getOutputStream();
       final PrintStream pout = new PrintStream(out);
       new JUnitCore().runMain(new JUnitSystem() {
         public PrintStream out() { return pout; }
         public void exit(int arg0) {}
       }, className);
       out.close();
	}
}
