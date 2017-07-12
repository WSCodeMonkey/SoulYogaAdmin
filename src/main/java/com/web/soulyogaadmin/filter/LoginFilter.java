package com.web.soulyogaadmin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.web.soulyogaadmin.util.UtilProperties;
import com.web.soulyogaadmin.util.UtilValidate;

/**
 * Servlet Filter implementation class LoginFilter
 * @author Shawn xiao
 * @version 2017-06-15
 */

public class LoginFilter implements Filter {
	
	private Logger logger=Logger.getLogger(LoginFilter.class);
	private HttpServletResponse res;
	private HttpServletRequest req;
	private HttpSession session;
	//private String openId;
	private String encodedPath;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		res=(HttpServletResponse)response;
		req=(HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");  
		res.setCharacterEncoding("UTF-8");
		session=req.getSession();
		
		//get client from session at first,if there are no client in the session then check token
		String result=(String) session.getAttribute("admin");
		
		    String requestUri = req.getRequestURI();
		    String contextPath = req.getContextPath();
		    String url = requestUri.substring(contextPath.length());
		
		
		if(UtilValidate.isNotEmpty(result)&&"admin".equals(result)||"/adminLogin.jsp".equals(url)){
		chain.doFilter(request, response);
		}else{
			//String forwardUrl=req.getProtocol()+"://"+req.getLocalName()+"/adminLogin.jsp";
			
		    	UtilProperties p = UtilProperties.getUtilproperties();
			String forceHostPath=p.readProperty("forceHostPath");	
			String forceContextPath=p.readProperty("forceContextPath");	
			String forwardUrl=forceHostPath+forceContextPath+"/adminLogin.jsp";
			res.sendRedirect(forwardUrl);
		}
	}

	
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
