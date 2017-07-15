/**
 * 
 */
package org.sidao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.sidao.cm.common.CommonInterceptor;

/**
 * @author wxc
 *
 */
public class MonitoringFilter implements Filter {

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req=(HttpServletRequest)request;
//		HttpSession session = req.getSession();
//		Object o=session.getAttribute(CommonInterceptor.SESSION_NAME_USER);
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	

}
