package in.nic.pes.common.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AUthenticatUserFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request =  (HttpServletRequest) req;
		if(request.getRemoteUser()!=null){
			request.getSession(true);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	@Override
	public void destroy() {
		
	}

}
