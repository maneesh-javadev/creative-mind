package in.nic.pes.lgd.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

public class IllegalExtension implements javax.servlet.Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException 
			{
			try {
					
					throw new Exception("Illegal URL extension detected");
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				expHandler.handleSaveRedirectException((HttpServletRequest)req,"","label.nad", 1, e);
				((HttpServletResponse)arg1).sendRedirect("infringement.htm");
			}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//TODO  to implement
	}

}
