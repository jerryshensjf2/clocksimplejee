package com.javaforever.clocksimplejee4.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaforever.clocksimplejee4.domain.User;

public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			String canAccessStr = (String)req.getSession().getAttribute("canAccess");
			String isadminStr = (String)req.getSession().getAttribute("isadmin");
			String isloginStr = (String)req.getSession().getAttribute("islogin");
			String forwardUrl2 = (String)req.getSession().getAttribute("forwardUrl");
			User user = (User) req.getSession().getAttribute("userData");			
			

			String forwardUrl = req.getRequestURI();
			System.out.println(forwardUrl);
			String[] forwards = forwardUrl.split("/");
			String forwardUrl0 = "";
			if (forwards.length >= 3) {
				forwardUrl0 = "../" + forwards[forwards.length - 2] + "/"
						+ forwards[forwards.length - 1];
				System.out.println("Jerry Debug::LoginFilter" + forwardUrl0);
			}
			
			if ("../login/index.jsp".equalsIgnoreCase(forwardUrl0) || "../login/loginviausernamepassword.jsp".equalsIgnoreCase(forwardUrl0)){
				req.getRequestDispatcher(forwardUrl0).forward(req, res);
				//res.sendRedirect(forwardUrl0);
				return;
			}
			
			if (forwardUrl2!= null && !"".equalsIgnoreCase(forwardUrl2)){
				req.getSession().setAttribute("forwardUrl",null);	
				//res.sendRedirect(forwardUrl2);
				req.getRequestDispatcher(forwardUrl2).forward(request, response);
				
			}
			
			
			if (isloginStr == null ||"".equals(isloginStr)){
				req.getRequestDispatcher("../login/index.jsp").forward(req, res);
				//res.sendRedirect("../login/index.jsp");
				return;
			}		
			chain.doFilter(request, response);
		} catch (Exception e){
			e.printStackTrace();
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			req.getRequestDispatcher("../login/index.jsp").forward(req, res);
			//res.sendRedirect("../login/index.jsp");
			return;
		} finally {			
		}
	}

	public void destroy() {
	}

}
